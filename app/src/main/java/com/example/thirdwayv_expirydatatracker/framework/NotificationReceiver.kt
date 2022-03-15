package com.example.thirdwayv_expirydatatracker.framework

import android.R
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import com.example.thirdwayv_expirydatatracker.domain.Item
import com.example.thirdwayv_expirydatatracker.framework.db.ExpiredEntity
import com.example.thirdwayv_expirydatatracker.framework.db.ExpiryTrackerDatabase
import com.example.thirdwayv_expirydatatracker.framework.db.ItemEntity
import com.example.thirdwayv_expirydatatracker.presentation.MainActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class NotificationReceiver : BroadcastReceiver() {
    var itemList: List<Item>? = null
    var context: Context? = null
    var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    override fun onReceive(context: Context?, intent: Intent?) {
        this.context = context
        val notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val repeatingIntent = Intent(context, MainActivity::class.java)
        repeatingIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(
            context,
            100,
            repeatingIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val gson = Gson()
        val json: String = intent?.getStringExtra("itemList")!!
        val type: Type = object : TypeToken<ArrayList<Item?>?>() {}.type
        itemList = gson.fromJson(json, type)

        val expiredNotification: NotificationItem = getExpired()!!
        if (expiredNotification != null) {
            val notificationBuilder: Notification = Notification.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.sym_def_app_icon)
                .setContentTitle(expiredNotification.title)
                .setContentText(expiredNotification.message)
                .setNumber(expiredNotification.number)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.resources,
                        expiredNotification.notificationImage
                    )
                )
                .setAutoCancel(true)
                .build()
            notificationManager.notify(100, notificationBuilder)
            val targetItem: Item = itemList!![expiredNotification.number]
            GlobalScope.launch {
                ExpiryTrackerDatabase.getInstance(context).itemsDao().removeItem(
                    ItemEntity(
                        targetItem.id,
                        targetItem.itemName,
                        targetItem.itemCategory,
                        targetItem.itemExpireDate
                    )
                )
                ExpiryTrackerDatabase.getInstance(context).expiredItemsDao().addExpiredItem(
                    ExpiredEntity(
                        targetItem.id!!,
                        targetItem.itemName,
                        targetItem.itemCategory,
                        targetItem.itemExpireDate
                    )
                )
            }
        }
    }


    class NotificationItem(
        var title: String,
        var message: String,
        var number: Int,
        var notificationImage: Int
    )

    private fun getExpired(): NotificationItem? {
        val expiredItems: MutableList<String> = ArrayList()
        var expiredTitle = ""
        var expiredMessage = ""
        var numberExpired = 0
        var expiredImage = 0
        if (itemList != null) {
            for (item in itemList!!) {
                val itemExpiry: Date = format.parse(item.itemExpireDate)
                if (itemExpiry.before(Calendar.getInstance().time)) {
                    numberExpired++
                    expiredItems.add(item.itemName!!)
                }
            }
        }
        expiredTitle = "$numberExpired Items have expired!"
        if (numberExpired <= 0) {
            return null
        } else if (numberExpired == 1) {
            expiredTitle = "1 Item has expired!"
            expiredMessage = expiredItems[0] + " has expired."
        } else if (numberExpired == 2) {
            expiredMessage = expiredItems[0] + " and " + expiredItems[1] + " have expired."
        } else if (numberExpired > 2) {
            for (index in 0 until numberExpired) {
                if (index == numberExpired - 1) {
                    expiredMessage += " and " + expiredItems[index] + " have expired."
                    break
                }
                if (index == 0) {
                    expiredMessage = expiredItems[index]
                } else {
                    expiredMessage += ", " + expiredItems[index]
                }
            }
        }
        return NotificationItem(expiredTitle, expiredMessage, numberExpired, expiredImage)
    }

}
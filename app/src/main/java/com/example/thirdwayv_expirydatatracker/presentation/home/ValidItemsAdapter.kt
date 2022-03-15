package com.example.thirdwayv_expirydatatracker.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thirdwayv_expirydatatracker.R
import com.example.thirdwayv_expirydatatracker.domain.Item
import java.text.SimpleDateFormat
import java.util.*

class ValidItemsAdapter(
    private val items: MutableList<Item> = mutableListOf(),
) : RecyclerView.Adapter<ValidItemsAdapter.ViewHolder>() {
    var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.itemName)
        val category: TextView = view.findViewById(R.id.itemCategory)
        val expiry: TextView = view.findViewById(R.id.itemExpiryDate)
        val timeLeft: TextView = view.findViewById(R.id.timeLeft)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].itemName
        holder.category.text = items[position].itemCategory
        val expiryDate: Date = format.parse(items[position].itemExpireDate)
        val timeString: String = format.format(Calendar.getInstance().time)
        val timeNow: Date = format.parse(timeString)
        val diff: Long = expiryDate.time - timeNow.time
        val hours = (((diff / 1000) / 60) / 60).toInt()

        holder.expiry.text = format.format(expiryDate)
        holder.timeLeft.text = "${hours}h left"
    }

    fun update(newBookmarks: List<Item>) {
        items.clear()
        items.addAll(newBookmarks)
        notifyDataSetChanged()
    }
}
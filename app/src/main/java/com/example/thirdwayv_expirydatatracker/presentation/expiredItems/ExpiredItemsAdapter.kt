package com.example.thirdwayv_expirydatatracker.presentation.expiredItems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thirdwayv_expirydatatracker.R
import com.example.thirdwayv_expirydatatracker.domain.ExpiredItem
import java.text.SimpleDateFormat
import java.util.*

class ExpiredItemsAdapter(
    private val items: MutableList<ExpiredItem> = mutableListOf(),
) : RecyclerView.Adapter<ExpiredItemsAdapter.ViewHolder>() {
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
        holder.name.text = items[position].item?.itemName
        holder.category.text = items[position].item?.itemCategory
        val expiryDate: Date = format.parse(items[position].item?.itemExpireDate)
        holder.expiry.text = format.format(expiryDate)
        holder.timeLeft.text = "Expired"
        holder.timeLeft.setBackgroundResource(R.drawable.rounded_red)
    }

    fun update(expiredItems: List<ExpiredItem>) {
        items.clear()
        items.addAll(expiredItems)
        notifyDataSetChanged()
    }
}
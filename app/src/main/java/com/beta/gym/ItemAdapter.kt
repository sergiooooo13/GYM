package com.beta.gym

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crono_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = itemList.size

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val weekTextView: TextView = itemView.findViewById(R.id.weekTextView)
        private val runTextView: TextView = itemView.findViewById(R.id.runTextView)
        private val restTextView: TextView = itemView.findViewById(R.id.restTextView)

        fun bind(item: Item) {
            nameTextView.text = item.name
            dateTextView.text = item.date
            weekTextView.text = item.week.toString()
            runTextView.text = item.runTime
            restTextView.text = item.restTime
        }
    }
}

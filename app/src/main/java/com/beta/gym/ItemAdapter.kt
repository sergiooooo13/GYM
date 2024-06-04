package com.beta.gym

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beta.gym.BBDD.Crono
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ItemAdapter(private val itemList: List<Crono>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

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
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val weekTextView: TextView = itemView.findViewById(R.id.weekTextView)
        private val runTextView: TextView = itemView.findViewById(R.id.runTextView)
        private val restTextView: TextView = itemView.findViewById(R.id.restTextView)

        fun bind(item: Crono) {
            dateTextView.text = convertirLongAFecha(item.date)
            weekTextView.text = item.week.toString()
            runTextView.text = convertirLongAMinutosSegundos(item.runTime)
            restTextView.text = convertirLongAMinutosSegundos(item.restTime)
        }
    }
    fun convertirLongAFecha(time: Long): String {
        val date = Date(time)
        val formato = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        return formato.format(date)
    }

    fun convertirLongAMinutosSegundos(time: Long): String {
        val date = Date(time)
        val formato = SimpleDateFormat("mm:ss", Locale.US)
        return formato.format(date)
    }
}

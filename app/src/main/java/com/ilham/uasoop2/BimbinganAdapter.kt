package com.ilham.uasoop2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilham.uasoop2.model.Bimbingan

class BimbinganAdapter(
    private val listItems: ArrayList<Bimbingan>,
    private val listener: BimbinganListener
) : RecyclerView.Adapter<BimbinganAdapter.BimbinganViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BimbinganViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_bimbingan, parent, false)
        return BimbinganViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: BimbinganViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewTitle.text = item.title
        holder.textViewBody.text = item.body
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class BimbinganViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle = itemView.findViewById<TextView>(R.id.text_view_title)
        var textViewBody = itemView.findViewById<TextView>(R.id.text_view_body)
    }

    interface BimbinganListener{
        fun OnItemClicked(Bimbingan: Bimbingan)
    }
}
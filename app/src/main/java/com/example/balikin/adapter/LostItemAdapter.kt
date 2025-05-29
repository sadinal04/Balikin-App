package com.example.balikin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.balikin.R
import com.example.balikin.model.LostItem
import com.bumptech.glide.Glide  // Untuk menampilkan gambar dari base64

class LostItemsAdapter(private var items: List<LostItem>) : RecyclerView.Adapter<LostItemsAdapter.LostItemViewHolder>() {

    // Update data untuk adapter
    fun updateItems(newItems: List<LostItem>) {
        items = newItems
        notifyDataSetChanged()  // Memberitahukan adapter bahwa data telah berubah
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LostItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lost_item, parent, false)
        return LostItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: LostItemViewHolder, position: Int) {
        val lostItem = items[position]
        holder.nameTextView.text = lostItem.name
        holder.descriptionTextView.text = lostItem.description
        holder.locationTextView.text = lostItem.location

        // Menggunakan Glide untuk menampilkan gambar dari base64
        Glide.with(holder.itemView.context)
            .load(lostItem.image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class LostItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.item_name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.item_description)
        val locationTextView: TextView = itemView.findViewById(R.id.item_location)
        val imageView: ImageView = itemView.findViewById(R.id.item_image)
    }
}

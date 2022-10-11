package com.gusoft.trakyabilmuh.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gusoft.trakyabilmuh.databinding.ChannelListItemBinding
import com.gusoft.trakyabilmuh.model.ChannelModel
import com.squareup.picasso.Picasso

class ChannelAdapter(private var data: ArrayList<ChannelModel>) :
    RecyclerView.Adapter<ChannelAdapter.ViewHolder>() {

    var onItemClick: (ChannelModel, Int) -> Unit = { _, _ -> }

    class ViewHolder(val binding: ChannelListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ChannelListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: ChannelModel = data[position]

        holder.binding.apply {
            channelName.text = item.channelName
            username.text = "${item.user.name} ${item.user.surname}"

            if (item.channelImageUrl != null && item.channelImageUrl != "") {
                Picasso.get().load(item.channelImageUrl).into(channelImage)
            }

            isSubscribed.isChecked = item.isSubscribed
//
//            announcementSummary.setOnClickListener {
//                item.expandable = !item.expandable
//                notifyItemChanged(position)
//            }
            channelListItemRootLayout.setOnClickListener { onItemClick(item, position) }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}
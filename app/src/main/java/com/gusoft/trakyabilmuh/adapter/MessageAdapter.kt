package com.gusoft.trakyabilmuh.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gusoft.trakyabilmuh.databinding.MessageItemBinding
import com.gusoft.trakyabilmuh.model.MessageModel


class MessageAdapter(private var data: ArrayList<MessageModel>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    var onItemClick: (MessageModel) -> Unit = {}

    class ViewHolder(val binding: MessageItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MessageItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: MessageModel = data[position]

        holder.binding.apply {
            messageContent.text = item.content
            senderName.text = "${item.user.name} ${item.user.surname}"

            dateTime.text = item.createdDateTime

            if (item.link.isNullOrBlank()) {
                messageLinkButton.visibility = View.GONE
            }

//            val isExpandable: Boolean = item.expandable
//            announcementDetailsHolder.visibility = if (isExpandable) View.VISIBLE else View.GONE
//
//            announcementSummary.setOnClickListener {
//                item.expandable = !item.expandable
//                notifyItemChanged(position)
//            }
            messageLinkButton.setOnClickListener { onItemClick(item) }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}
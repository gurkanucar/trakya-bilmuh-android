package com.gusoft.trakyabilmuh.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gusoft.trakyabilmuh.databinding.AnnouncementItemBinding
import com.gusoft.trakyabilmuh.model.Announcement
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class AnnouncementAdapter(private var data: ArrayList<Announcement>) :
    RecyclerView.Adapter<AnnouncementAdapter.ViewHolder>() {

    var onItemClick: (Announcement) -> Unit = {}

    class ViewHolder(val binding: AnnouncementItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AnnouncementItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Announcement = data[position]

        holder.binding.apply {
            announcementTitleText.text = item.title
            announcementContentText.text = item.content
            val tempDate = item.createdDateTime.split("T")[0]
            dayMonthText.text =
                "${tempDate.split("-")[2]}/${tempDate.split("-")[1]}"
            yearText.text = tempDate.split("-")[0].toString()
            announcementSummary.setOnClickListener {
                if (announcementDetailsHolder.visibility.equals(View.VISIBLE)) {
                    announcementDetailsHolder.visibility = View.GONE
                } else {
                    announcementDetailsHolder.visibility = View.VISIBLE
                }
            }
            announcementOpenButton.setOnClickListener { onItemClick(item) }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}
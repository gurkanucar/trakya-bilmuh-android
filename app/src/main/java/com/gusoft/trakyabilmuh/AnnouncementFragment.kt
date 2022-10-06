package com.gusoft.trakyabilmuh

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gusoft.trakyabilmuh.adapter.AnnouncementAdapter
import com.gusoft.trakyabilmuh.databinding.FragmentAnnouncementBinding
import com.gusoft.trakyabilmuh.model.Announcement
import com.gusoft.trakyabilmuh.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AnnouncementFragment : Fragment() {

    private var _binding: FragmentAnnouncementBinding? = null
    private val binding get() = _binding!!

    var announcementList: ArrayList<Announcement> = arrayListOf()

    private lateinit var adapter: AnnouncementAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnnouncementBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBooks()
    }


    private fun getBooks() {
        ApiUtils.getAnnouncementsDAO().getAnnouncements().enqueue(
            object : Callback<List<Announcement>> {
                override fun onResponse(
                    call: Call<List<Announcement>>,
                    response: Response<List<Announcement>>
                ) {
                    response.body()?.let {
                        announcementList = it as ArrayList<Announcement>
                    }
                    Log.i("Announcement", announcementList.toString())

                    announcementList.reverse()


                    binding.apply {
                        val manager = LinearLayoutManager(requireContext())
                        announcementsRcView.layoutManager = manager
                        announcementsRcView.setHasFixedSize(true)
                        adapter = AnnouncementAdapter(announcementList)
                        announcementsRcView.adapter = adapter
                        adapter.onItemClick = ::onItemClick
                    }
                }

                override fun onFailure(call: Call<List<Announcement>>, t: Throwable) {
                    Log.e("ERROR", "onFailure: " + t.toString())
                }

            }
        )
    }

    fun onItemClick(announcement: Announcement) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(announcement.link))
        startActivity(browserIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
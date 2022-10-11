package com.gusoft.trakyabilmuh

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gusoft.trakyabilmuh.adapter.ChannelAdapter
import com.gusoft.trakyabilmuh.databinding.FragmentChannelSettingsBinding
import com.gusoft.trakyabilmuh.model.ChannelModel
import com.gusoft.trakyabilmuh.network.ApiUtils
import com.gusoft.trakyabilmuh.util.getSubscribeList
import com.gusoft.trakyabilmuh.util.saveSubscribeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChannelSettings : Fragment() {

    private var _binding: FragmentChannelSettingsBinding? = null
    private val binding get() = _binding!!

    var channelList: ArrayList<ChannelModel> = arrayListOf()
    private lateinit var adapter: ChannelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChannelSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData(getSubscribeList(requireContext(), "subscribedChannels"))
    }

    private fun fetchData(subscribeList: List<String>) {
        ApiUtils.getChannelDAO().getChannels().enqueue(
            object : Callback<List<ChannelModel>> {
                override fun onResponse(
                    call: Call<List<ChannelModel>>,
                    response: Response<List<ChannelModel>>
                ) {
                    response.body()?.let {
                        channelList = it as ArrayList<ChannelModel>
                    }
                    Log.i("Channel", channelList.toString())

                    channelList.forEach {
                        it.isSubscribed =
                            subscribeList.contains(it.channelTopic)
                    }

                    // messageList.reverse()


                    binding.apply {
                        if (channelList.size > 0) {
                            noDataFoundText.visibility = View.GONE
                        }
                        channelsRcView.layoutManager = LinearLayoutManager(requireContext())
                        channelsRcView.setHasFixedSize(true)
                        adapter = ChannelAdapter(channelList)
                        adapter.onItemClick = ::onItemClick
                        channelsRcView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<List<ChannelModel>>, t: Throwable) {
                    Log.e("ERROR", "onFailure: " + t.toString())
                }

            }
        )
    }

    fun onItemClick(channel: ChannelModel, position: Int) {
        channelList.forEach {
            if (it.id == channel.id) {
                it.isSubscribed = !it.isSubscribed
            }
        }
        saveSubscribeList(
            requireContext(),
            channelList.filter { it.isSubscribed }.map { it.channelTopic },
            "subscribedChannels"
        )
        adapter.notifyItemChanged(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
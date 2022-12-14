package com.gusoft.trakyabilmuh

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.messaging.FirebaseMessaging
import com.gusoft.trakyabilmuh.adapter.SubscribedChannelsAdapter
import com.gusoft.trakyabilmuh.databinding.FragmentHomeBinding
import com.gusoft.trakyabilmuh.model.ChannelModel
import com.gusoft.trakyabilmuh.network.ApiUtils
import com.gusoft.trakyabilmuh.util.getSubscribeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var channelListFiltered: ArrayList<ChannelModel> = arrayListOf()
    var channelListAll: ArrayList<ChannelModel> = arrayListOf()
    private lateinit var adapter: SubscribedChannelsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*   val foods = FoodScraper.getFoodList()
           if (foods == null || foods.size == 0) {
               binding.foodTitle.text = "Tatil"
           } else {
               binding.apply {
                   food1.text = foods[0]
                   food2.text = foods[1]
                   food3.text = foods[2]
                   food4.text = foods[3]
               }

           }*/


        binding.channelSettings.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_channelSettings)
        }

        binding.announcementButton.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_announcementFragment)
        }


        fetchData(getSubscribeList(requireContext(), "subscribedChannels"))

        subscribeToTopics(FirebaseMessaging.getInstance())

    }

    private fun fetchData(subscribeList: List<String>) {
        ApiUtils.getChannelDAO().getChannels().enqueue(
            object : Callback<List<ChannelModel>> {
                override fun onResponse(
                    call: Call<List<ChannelModel>>,
                    response: Response<List<ChannelModel>>
                ) {
                    response.body()?.let {
                        channelListAll = it as ArrayList<ChannelModel>
                    }
                    Log.i("Channel", channelListAll.toString())

                    channelListFiltered = channelListAll.filter {
                        subscribeList.contains(it.channelTopic)
                    } as ArrayList<ChannelModel>

                    // messageList.reverse()


                    binding.apply {

                        subscribedChannelsRcView.layoutManager =
                            LinearLayoutManager(requireContext())
                        subscribedChannelsRcView.isNestedScrollingEnabled = false
                        subscribedChannelsRcView.setHasFixedSize(true)
                        adapter = SubscribedChannelsAdapter(channelListFiltered)
                        adapter.onItemClick = ::onItemClick
                        subscribedChannelsRcView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<List<ChannelModel>>, t: Throwable) {
                    Log.e("ERROR", "onFailure: " + t.toString())
                }

            }
        )
    }

    fun onItemClick(channel: ChannelModel, position: Int) {
        Toast.makeText(requireContext(), channel.channelName, Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putLong("channelId", channel.id)
        bundle.putString("channelName", channel.channelName)
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_homeFragment_to_messageFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun subscribeToTopics(firebaseMessaging: FirebaseMessaging) {
        val list = getSubscribeList(requireContext(), "subscribedChannels")

        channelListAll.forEach {
            firebaseMessaging.unsubscribeFromTopic(it.channelTopic)
        }
        firebaseMessaging.subscribeToTopic("announcements")
        list.forEach {
            Log.i("SUB TOPICS", "subscribeToTopics: $it")
            firebaseMessaging.subscribeToTopic(it)
        }
    }
}
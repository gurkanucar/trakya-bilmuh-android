package com.gusoft.trakyabilmuh

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gusoft.trakyabilmuh.adapter.MessageAdapter
import com.gusoft.trakyabilmuh.databinding.FragmentMessageBinding
import com.gusoft.trakyabilmuh.model.MessageModel
import com.gusoft.trakyabilmuh.model.MessageTypes
import com.gusoft.trakyabilmuh.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    var messageList: ArrayList<MessageModel> = arrayListOf()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.noDataFoundText.visibility = View.VISIBLE
        val channelId: Long = arguments?.get("channelId") as Long
        fetchData(channelId)

    }

    private fun fetchData(channelId: Long) {
        ApiUtils.getMessageDAO().getMessages(channelId).enqueue(
            object : Callback<List<MessageModel>> {
                override fun onResponse(
                    call: Call<List<MessageModel>>,
                    response: Response<List<MessageModel>>
                ) {
                    response.body()?.let {
                        messageList = it as ArrayList<MessageModel>
                    }
                    Log.i("Announcement", messageList.toString())

                    // messageList.reverse()


                    binding.apply {
                        if (messageList.size > 0) {
                            noDataFoundText.visibility = View.GONE
                        }
                        messagesRcView.layoutManager = LinearLayoutManager(requireContext())
                        messagesRcView.setHasFixedSize(true)
                        adapter = MessageAdapter(messageList)
                        adapter.onItemClick = ::onItemClick
                        messagesRcView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<List<MessageModel>>, t: Throwable) {
                    Log.e("ERROR", "onFailure: " + t.toString())
                }

            }
        )
    }

    fun onItemClick(message: MessageModel) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(message.link))
        startActivity(browserIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
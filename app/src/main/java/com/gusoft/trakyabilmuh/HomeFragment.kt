package com.gusoft.trakyabilmuh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.gusoft.trakyabilmuh.databinding.FragmentHomeBinding
import com.gusoft.trakyabilmuh.model.MessageTypes


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.announcementButton.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_announcementFragment)
        }

        binding.internShipButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("messageType", MessageTypes.INTERNSHIP)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_messageFragment, bundle)

        }

        binding.jobButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("messageType", MessageTypes.JOB)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_messageFragment, bundle)

        }

        binding.firstGrade.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("messageType", MessageTypes.FIRST_GRADE)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_messageFragment, bundle)

        }

        binding.secondGrade.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("messageType", MessageTypes.SECOND_GRADE)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_messageFragment, bundle)

        }

        binding.thirdGrade.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("messageType", MessageTypes.THIRD_GRADE)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_messageFragment, bundle)

        }

        binding.fourthGrade.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("messageType", MessageTypes.FOURTH_GRADE)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_messageFragment, bundle)

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
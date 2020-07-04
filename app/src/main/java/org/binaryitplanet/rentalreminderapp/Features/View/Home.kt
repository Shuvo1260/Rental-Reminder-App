package org.binaryitplanet.rentalreminderapp.Features.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.databinding.FragmentHomeBinding


class Home : Fragment() {

    private val TAG = "Home"

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.add.setOnClickListener {
            //
        }

        return binding.root
    }

}
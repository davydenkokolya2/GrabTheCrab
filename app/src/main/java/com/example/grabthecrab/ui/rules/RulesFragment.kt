package com.example.grabthecrab.ui.rules

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.grabthecrab.databinding.FragmentRulesBinding
import com.example.grabthecrab.ui.RemoteScreenViewModel
import com.example.grabthecrab.util.RemoteScreen

class RulesFragment : DialogFragment() {

    private lateinit var binding: FragmentRulesBinding
    private val remoteScreenViewModel: RemoteScreenViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding = FragmentRulesBinding.inflate(inflater, container, false)

        binding.imageView.setOnClickListener {
            remoteScreenViewModel.loadState(RemoteScreen.PLAY)
            onStop()
        }
        return binding.root
    }

}
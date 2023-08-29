package com.example.grabthecrab.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.grabthecrab.databinding.FragmentOnboardingBinding
import com.example.grabthecrab.ui.RemoteScreenViewModel
import com.example.grabthecrab.util.RemoteScreen

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private val remoteScreenViewModel: RemoteScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        binding.textView.setOnClickListener {
            remoteScreenViewModel.loadState(RemoteScreen.MENU)
        }
        return binding.root
    }
}
package com.example.grabthecrab.ui.game_over

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.grabthecrab.databinding.FragmentGameOverBinding
import com.example.grabthecrab.ui.RemoteScreenViewModel
import com.example.grabthecrab.ui.play.PlayViewModel
import com.example.grabthecrab.util.RemoteScreen

class GameOverFragment : DialogFragment() {

    private lateinit var binding: FragmentGameOverBinding
    private val remoteScreenViewModel: RemoteScreenViewModel by activityViewModels()
    private val playViewModel: PlayViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = FragmentGameOverBinding.inflate(inflater, container, false)

        binding.tvGameOverCoins.text = playViewModel.stateCoins.value.toString()
        binding.imageView2.setOnClickListener {
            remoteScreenViewModel.loadState(RemoteScreen.MENU)
            onStop()
        }
        return binding.root
    }
}
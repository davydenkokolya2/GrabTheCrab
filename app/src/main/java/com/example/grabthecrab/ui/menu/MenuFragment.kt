package com.example.grabthecrab.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.grabthecrab.R
import com.example.grabthecrab.databinding.FragmentMenuBinding
import com.example.grabthecrab.ui.rules.RulesFragment
import com.example.grabthecrab.util.Constants
import kotlinx.coroutines.launch

class MenuFragment : Fragment() {


    private lateinit var binding: FragmentMenuBinding
    private val menuViewModel: MenuViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        binding.btnPlay.setOnClickListener {
            showPopUp()
        }

        binding.btnSetting.setOnClickListener {
            menuViewModel.loadStateSetting(!menuViewModel.stateSetting.value)
        }

        binding.btnMusic.setOnClickListener {
            menuViewModel.loadStateMusic(!menuViewModel.stateMusic.value)
        }

        binding.btnSound.setOnClickListener {
            menuViewModel.loadStateSound(!menuViewModel.stateSound.value)
        }

        lifecycleScope.launch {
            menuViewModel.stateSetting.collect {
                when (it) {
                    true -> {
                        binding.btnSound.visibility = View.VISIBLE
                        binding.btnMusic.visibility = View.VISIBLE
                    }

                    false -> {
                        binding.btnSound.visibility = View.INVISIBLE
                        binding.btnMusic.visibility = View.INVISIBLE
                    }
                }
            }
        }

        lifecycleScope.launch {
            menuViewModel.stateMusic.collect {
                when (it) {
                    true -> binding.btnMusic.setImageResource(R.drawable.icon_music_on)
                    false -> binding.btnMusic.setImageResource(R.drawable.icon_music_off)
                }
            }
        }
        lifecycleScope.launch {
            menuViewModel.stateSound.collect {
                when (it) {
                    true -> binding.btnSound.setImageResource(R.drawable.icon_sound_on)
                    false -> binding.btnSound.setImageResource(R.drawable.icon_sound_off)
                }
            }
        }
        for (i in 0..2)
            for (j in 0..4) {
                Constants.crabsList[i][j] = 0
                Constants.openCloseList[i][j] = 0
            }
        return binding.root
    }

    private fun showPopUp() {
        RulesFragment().show(
            (activity as AppCompatActivity).supportFragmentManager,
            "showPopUp"
        )
    }
}
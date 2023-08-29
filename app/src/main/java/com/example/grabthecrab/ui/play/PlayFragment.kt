package com.example.grabthecrab.ui.play

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.grabthecrab.databinding.FragmentPlayBinding
import com.example.grabthecrab.ui.game_over.GameOverFragment
import com.example.grabthecrab.ui.good_job.GoodJobFragment
import com.example.grabthecrab.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random


class PlayFragment : Fragment() {

    private lateinit var binding: FragmentPlayBinding
    private var coordinate: Pair<Int, Int> = -1 to -1
    private val playViewModel: PlayViewModel by activityViewModels()
    private var countCrabs = 0
    private var flag = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayBinding.inflate(inflater, container, false)

        generateField()
        updateAllField()

        binding.iv00.setOnClickListener { checkClick(0, 0) }
        binding.iv01.setOnClickListener { checkClick(0, 1) }
        binding.iv02.setOnClickListener { checkClick(0, 2) }
        binding.iv03.setOnClickListener { checkClick(0, 3) }
        binding.iv04.setOnClickListener { checkClick(0, 4) }
        binding.iv10.setOnClickListener { checkClick(1, 0) }
        binding.iv11.setOnClickListener { checkClick(1, 1) }
        binding.iv12.setOnClickListener { checkClick(1, 2) }
        binding.iv13.setOnClickListener { checkClick(1, 3) }
        binding.iv14.setOnClickListener { checkClick(1, 4) }
        binding.iv20.setOnClickListener { checkClick(2, 0) }
        binding.iv21.setOnClickListener { checkClick(2, 1) }
        binding.iv22.setOnClickListener { checkClick(2, 2) }
        binding.iv23.setOnClickListener { checkClick(2, 3) }
        binding.iv24.setOnClickListener { checkClick(2, 4) }


        lifecycleScope.launch {
            for (i in 9 downTo 0) {
                binding.tvTime.text = i.toString()
                delay(1000)
            }
            if (!flag)
                showPopUpGameOver()
        }
        lifecycleScope.launch {
            playViewModel.stateScore.collect {
                if (countCrabs == 10) {
                    playViewModel.loadStateCoins(playViewModel.stateCoins.value + 100)
                    flag = true
                    showPopUpGoodJob()
                }
                binding.tvScore.text = it.toString()
            }

        }
        lifecycleScope.launch {
            playViewModel.stateCoins.collect {
                binding.tvCoins.text = it.toString()
            }
        }
        return binding.root
    }

    private fun updateAllField() {
        binding.iv00.setImageResource(setImage(0, 0))
        binding.iv01.setImageResource(setImage(0, 1))
        binding.iv02.setImageResource(setImage(0, 2))
        binding.iv03.setImageResource(setImage(0, 3))
        binding.iv04.setImageResource(setImage(0, 4))
        binding.iv10.setImageResource(setImage(1, 0))
        binding.iv11.setImageResource(setImage(1, 1))
        binding.iv12.setImageResource(setImage(1, 2))
        binding.iv13.setImageResource(setImage(1, 3))
        binding.iv14.setImageResource(setImage(1, 4))
        binding.iv20.setImageResource(setImage(2, 0))
        binding.iv21.setImageResource(setImage(2, 1))
        binding.iv22.setImageResource(setImage(2, 2))
        binding.iv23.setImageResource(setImage(2, 3))
        binding.iv24.setImageResource(setImage(2, 4))
    }

    private fun checkClick(i: Int, j: Int) {
        if (coordinate == -1 to -1) {
            coordinate = i to j
            Constants.openCloseList[i][j] = 1
            updateAllField()
        } else {
            if (Constants.openCloseList[i][j] == 0) {
                if (Constants.crabsList[coordinate.first][coordinate.second] == 1 && Constants.crabsList[i][j] == 1) {
                    Constants.openCloseList[i][j] = 1
                    coordinate = -1 to -1
                    playViewModel.loadStateScore(playViewModel.stateScore.value + 2)
                    countCrabs += 2
                    updateAllField()
                } else {
                    Constants.openCloseList[i][j] = 1
                    updateAllField()
                    lifecycleScope.launch {
                        delay(500)
                        Constants.openCloseList[coordinate.first][coordinate.second] = 0
                        Constants.openCloseList[i][j] = 0
                        coordinate = -1 to -1
                        updateAllField()
                    }

                }
            }
        }

    }

    private fun setImage(i: Int, j: Int): Int {
        return if (Constants.openCloseList[i][j] == 1)
            Constants.imagesList[Constants.crabsList[i][j]]
        else
            Constants.imagesList[9]
    }

    private fun generateField() {
        var countCrabs = 0
        while (countCrabs < 10) {
            val i = Random().nextInt(3)
            val j = Random().nextInt(5)
            if (Constants.crabsList[i][j] != 1) {
                Constants.crabsList[i][j] = 1
                countCrabs++
                Log.d("test", countCrabs.toString())
            }
        }

        for (i in 0..2)
            for (j in 0..4)
                if (Constants.crabsList[i][j] != 1)
                    Constants.crabsList[i][j] = Random().nextInt(4) + 4
    }

    private fun showPopUpGameOver() {
        val dialog = GameOverFragment().show(
            (activity as AppCompatActivity).supportFragmentManager,
            "showPopUp"
        )
    }

    private fun showPopUpGoodJob() {
        GoodJobFragment().show(
            (activity as AppCompatActivity).supportFragmentManager,
            "showPopUp"
        )
    }
}
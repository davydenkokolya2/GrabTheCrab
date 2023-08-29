package com.example.grabthecrab.ui

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.grabthecrab.R
import com.example.grabthecrab.ui.menu.MenuFragment
import com.example.grabthecrab.ui.menu.MenuViewModel
import com.example.grabthecrab.ui.onboarding.OnboardingFragment
import com.example.grabthecrab.ui.play.PlayFragment
import com.example.grabthecrab.util.RemoteScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private val remoteScreenViewModel: RemoteScreenViewModel by viewModels()
    private val menuViewModel: MenuViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            remoteScreenViewModel.stateRemoteScreen.collect {
                when (it) {
                    RemoteScreen.MENU -> replaceFragment(MenuFragment())
                    RemoteScreen.PLAY -> replaceFragment(PlayFragment())
                    RemoteScreen.ONBOARDING -> replaceFragment(OnboardingFragment())
                    else -> {}
                }
            }
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true
        lifecycleScope.launch {
            menuViewModel.stateMusic.collect {
                when (it) {
                    true -> mediaPlayer.start()
                    false -> mediaPlayer.pause()
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}
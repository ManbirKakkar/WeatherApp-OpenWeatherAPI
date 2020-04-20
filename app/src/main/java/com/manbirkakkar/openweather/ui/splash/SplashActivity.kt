package com.manbirkakkar.openweather.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.manbirkakkar.openweather.R
import com.manbirkakkar.openweather.base.BaseActivity
import com.manbirkakkar.openweather.ui.home.MainActivity
import com.manbirkakkar.openweather.utils.IntentUtil.Companion.moveScreenIntent
import com.manbirkakkar.openweather.utils.extensions.ScreenState
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var actionBar = supportActionBar
        actionBar!!.hide()

        viewModel = ViewModelProvider(
            this,
            SplashViewModelFactory(SplashInterceptor(this))
        )[SplashViewModel::class.java]
        viewModel.splashState.observe(::getLifecycle, ::updateUI)

        onLoginCheck()
    }

    private fun updateUI(screenState: ScreenState<SplashState>?) {
        when (screenState) {
            ScreenState.Loading -> progress.visibility = View.VISIBLE
            is ScreenState.Render -> processLoginState(screenState.renderState)
        }
    }

    private fun processLoginState(renderState: SplashState) {
        progress.visibility = View.GONE
        when (renderState) {
            SplashState.LoggedIn ->  moveScreenIntent(this, MainActivity::class.java, true)

        }
    }

    private fun onLoginCheck() {
        viewModel.onSplashCheck()
    }
}
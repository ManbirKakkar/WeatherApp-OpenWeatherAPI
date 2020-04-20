package com.manbirkakkar.openweather.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manbirkakkar.openweather.utils.extensions.ScreenState

class SplashViewModel(private val splashInterceptor: SplashInterceptor) : ViewModel(),
    SplashInterceptor.OnSplashFinishedListener {

    private val _splashState: MutableLiveData<ScreenState<SplashState>> = MutableLiveData()

    val splashState: LiveData<ScreenState<SplashState>>
        get() = _splashState

    fun onSplashCheck() {
        _splashState.value = ScreenState.Loading
        splashInterceptor.checkLoggedIn(this)
    }


    override fun onLoggedIn() {
        _splashState.value = ScreenState.Render(SplashState.LoggedIn)
    }

}

class SplashViewModelFactory(private val splashInterceptor: SplashInterceptor) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(splashInterceptor) as T
    }
}
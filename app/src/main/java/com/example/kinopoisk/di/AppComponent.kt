package com.example.kinopoisk.di

import android.content.Context
import com.example.kinopoisk.screen.cinema.viewModel.CinemaViewModel
import com.example.kinopoisk.screen.filmInfo.FilmInfoViewModel
import com.example.kinopoisk.screen.filmTop.viewModel.FilmTopViewModel
import com.example.kinopoisk.screen.login.LoginViewModel
import com.example.kinopoisk.screen.main.viewModel.MainViewModel
import com.example.kinopoisk.screen.review.ReviewViewModel
import com.example.kinopoisk.screen.setting.viewModel.SettingViewModel
import com.example.kinopoisk.screen.shop.shopViewModel.ShopViewModel
import com.example.kinopoisk.screen.staffInfo.StaffInfoViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        UserApiModule::class
    ]
)
@Singleton
interface AppComponent {
    fun cinemaViewModel():CinemaViewModel
    fun filmInfoViewModel():FilmInfoViewModel
    fun filmTopViewModel():FilmTopViewModel
    fun loginViewModel():LoginViewModel
    fun mainViewModel():MainViewModel
    fun reviewViewModel():ReviewViewModel
    fun settingViewModel():SettingViewModel
    fun shopViewModel():ShopViewModel
    fun staffInfoViewModel():StaffInfoViewModel

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context):Builder

        fun build():AppComponent
    }
}
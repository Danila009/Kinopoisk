package com.example.kinopoisk.di

import android.content.Context
import com.example.feature_authorization.viewModel.AuthorizationViewModel
import com.example.feature_cinema_add_review.viewModel.CinemaAddReviewViewModel
import com.example.feature_cinema_info.viewModel.CinemaInfoViewModel
import com.example.feature_cinema_map.viewModel.CinemaMapViewModel
import com.example.feature_comics.viewModel.ComicsViewModel
import com.example.feature_countries.viewModel.CountriesViewModel
import com.example.feature_film_images.viewModel.FilmImagesViewModel
import com.example.feature_film_info.viewModel.FilmInfoViewModel
import com.example.feature_films.viewModel.FilmsViewModel
import com.example.feature_genre.viewModel.GenreViewModel
import com.example.feature_home.viewModel.HomeViewModel
import com.example.feature_persons.viewModel.SearchViewModel
import com.example.feature_profile.viewModel.ProfileViewModel
import com.example.feature_registration.viewModel.RegistrationViewModel
import com.example.feature_serial_info.viewModel.SerialInfoViewModel
import com.example.feature_settings.viewModel.SettingViewModel
import com.example.feature_sorting.viewModel.SortingViewModel
import com.example.feature_update_user_password.viewModel.UpdateUserPasswordViewModel
import com.example.kinopoisk.di.modules.api.IMDbApiModule
import com.example.kinopoisk.di.modules.api.MarvelApiModule
import com.example.kinopoisk.di.modules.api.MyKinopoiskApiModule
import com.example.kinopoisk.di.modules.api.RickAndMortyApiModule
import com.example.kinopoisk.di.modules.database.UserDatabaseModule
import com.example.kinopoisk.di.modules.firebase.AuthFirebaseModule
import com.example.kinopoisk.screen.filmTop.viewModel.FilmTopViewModel
import com.example.kinopoisk.screen.review.ReviewViewModel
import com.example.kinopoisk.screen.shop.shopViewModel.ShopViewModel
import com.example.kinopoisk.screen.staffInfo.StaffInfoViewModel
import com.google.firebase.FirebaseOptions
import dagger.BindsInstance
import dagger.Component
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        UserApiModule::class,
        UserDatabaseModule::class,
        MyKinopoiskApiModule::class,
        RickAndMortyApiModule::class,
        MarvelApiModule::class,
        IMDbApiModule::class,
        AuthFirebaseModule::class
    ]
)
@Singleton
interface AppComponent {

    fun filmTopViewModel():FilmTopViewModel
    fun reviewViewModel():ReviewViewModel
    fun shopViewModel():ShopViewModel
    fun staffInfoViewModel():StaffInfoViewModel

    fun authorizationViewModel():AuthorizationViewModel
    fun registrationViewModel():RegistrationViewModel
    @ExperimentalSerializationApi
    fun homeViewModel():HomeViewModel
    fun filmsViewModel(): FilmsViewModel
    @ExperimentalSerializationApi
    fun searchViewModel():SearchViewModel
    fun profileViewModel():ProfileViewModel
    fun settingViewModel():SettingViewModel
    fun sortingViewModel():SortingViewModel
    fun genreViewModel():GenreViewModel
    fun countriesViewModel():CountriesViewModel
    fun updatePasswordCViewModel():UpdateUserPasswordViewModel
    fun serialInfoViewModel(): SerialInfoViewModel
    fun comicsViewModel():ComicsViewModel
    fun filmInfoViewModel(): FilmInfoViewModel
    fun cinemaInfoViewModel(): CinemaInfoViewModel
    fun cinemaMapViewModel():CinemaMapViewModel
    fun cinemaAddReviewVideModel():CinemaAddReviewViewModel
    fun filmImagesViewModel():FilmImagesViewModel

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context):Builder

        fun build():AppComponent
    }
}
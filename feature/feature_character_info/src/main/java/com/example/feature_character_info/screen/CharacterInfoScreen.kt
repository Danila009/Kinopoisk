package com.example.feature_character_info.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.core_utils.state.CharacterInfoState
import com.example.feature_character_info.view.RiakAndMortyCharacterInfoView
import com.example.feature_character_info.viewModel.CharacterInfoViewModel

@Composable
internal fun CharacterInfoScreen(
    navController: NavController,
    characterInfoViewModel: CharacterInfoViewModel,
    characterInfoState: CharacterInfoState,
    id:Int
) = when(characterInfoState){
    CharacterInfoState.RIAK_AND_MORTY -> RiakAndMortyCharacterInfoView(
        riakAndMortyId = id,
        characterInfoViewModel = characterInfoViewModel,
        navController = navController
    )
}
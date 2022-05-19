package com.example.core_utils.navigation.characterNavGraph

const val CHARACTER_INFO_ROUTE = "character_info_route"

const val CHARACTER_ID_ARGUMENT = "characterId"
const val CHARACTER_STATE_ARGUMENT = "characterState"

sealed class CharacterScreenRoute(val route:String) {
    object CharacterInfoScreen:CharacterScreenRoute(route = "character_info_screen/{characterId}?" +
            "characterState={characterState}"){
        fun argument(
            characterId:Int,
            characterState:String
        ):String = "character_info_screen/$characterId?characterState=$characterState"
    }
}
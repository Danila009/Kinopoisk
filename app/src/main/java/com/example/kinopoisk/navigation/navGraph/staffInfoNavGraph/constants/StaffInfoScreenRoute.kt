package com.example.kinopoisk.navigation.navGraph.staffInfoNavGraph.constants

sealed class StaffInfoScreenRoute(val route:String) {
    object StaffInfo:StaffInfoScreenRoute("staff_info?staffId={staffId}&filmId={filmId}&keyStaffScreen={keyStaffScreen}"){
        fun base(
            staffId:String,
            filmId:String? = null,
            key:String
        ):String = "staff_info?staffId=$staffId&filmId=$filmId&keyStaffScreen=$key"
    }
    object MoreStaff:StaffInfoScreenRoute("more_staff?staffId={staffId}&filmId={filmId}"){
        fun base(
            staffIf: String,
            filmId:String
        ):String = "more_staff?staffId=$staffIf&filmId=$filmId"
    }
}
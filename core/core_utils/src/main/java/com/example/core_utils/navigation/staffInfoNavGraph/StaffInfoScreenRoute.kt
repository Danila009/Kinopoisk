package com.example.core_utils.navigation.staffInfoNavGraph

sealed class StaffInfoScreenRoute(val route:String) {
    object StaffInfo:StaffInfoScreenRoute("staff_info?staffId={staffId}&filmId={filmId}&keyStaffScreen={keyStaffScreen}"){
        fun base(
            staffId:String,
            filmId:String? = null,
            key:String
        ):String = "staff_info?staffId=$staffId&filmId=$filmId&keyStaffScreen=$key"
    }
    object MoreStaff:StaffInfoScreenRoute("more_staff?staffId={staffId}"){
        fun base(
            staffIf: String
        ):String = "more_staff?staffId=$staffIf"
    }
}
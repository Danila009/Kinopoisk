package com.example.core_utils.navigation.staffInfoNavGraph

sealed class StaffInfoScreenRoute(val route:String) {
    object StaffInfo:StaffInfoScreenRoute("staff_info?staffId={staffId}"){
        fun base(
            staffId:Int
        ):String = "staff_info?staffId=$staffId"
    }
    object MoreStaff:StaffInfoScreenRoute("more_staff?staffId={staffId}"){
        fun base(
            staffIf: Int
        ):String = "more_staff?staffId=$staffIf"
    }
}
package com.example.lab4.ui

import com.example.lab4.R

sealed class NavItem(var route: String, var icon: Int, var title: String)
{
    object Home : NavItem("home", R.drawable.ic_home, "Home")
    object Orders : NavItem("orders", R.drawable.orders_icon, "Orders")
    object Profile : NavItem("profile", R.drawable.ic_profile, "Profile")
}
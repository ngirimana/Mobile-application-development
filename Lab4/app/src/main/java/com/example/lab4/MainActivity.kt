package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab4.ui.NavItem
import com.example.lab4.ui.compose.HomeScreen
import com.example.lab4.ui.compose.OrderScreen
import com.example.lab4.ui.compose.ProfileScreen
import com.example.lab4.ui.theme.Lab4Theme
import com.example.lab4.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4Theme {
                MainScreen()
            }

        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
    )
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavItem.Home.route)
    {
        composable(NavItem.Home.route)
        {
            HomeScreen()
        }
        composable(NavItem.Orders.route)
        {
            OrderScreen()
        }
        composable(NavItem.Profile.route) {
            ProfileScreen()
        }

    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        contentColor = Color.White
    )
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavItem.Home,
        NavItem.Orders,
        NavItem.Profile
    )
    BottomNavigation(
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


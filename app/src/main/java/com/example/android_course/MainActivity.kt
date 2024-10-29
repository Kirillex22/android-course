package com.example.android_course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController
import com.example.android_course.ui.theme.AndroidcourseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidcourseTheme {
                val configuration = LocalConfiguration.current
                S_W = configuration.screenWidthDp
                S_H = configuration.screenHeightDp
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}




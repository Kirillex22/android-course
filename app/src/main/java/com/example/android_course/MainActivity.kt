package com.example.android_course

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController
import com.example.android_course.ui.theme.AndroidcourseTheme

class MainActivity : ComponentActivity() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidcourseTheme {
                val configuration = LocalConfiguration.current
                S_W = configuration.screenWidthDp
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}




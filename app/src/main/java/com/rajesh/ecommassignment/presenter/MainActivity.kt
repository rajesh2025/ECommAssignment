package com.rajesh.ecommassignment.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rajesh.ecommassignment.presenter.navigation.AppNavigation
import com.rajesh.ecommassignment.ui.theme.ECommAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECommAssignmentTheme {
                AppNavigation()
            }
        }
    }
}


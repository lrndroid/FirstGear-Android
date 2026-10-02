package com.firstgear.telemetry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.firstgear.telemetry.ui.MainApp
import com.firstgear.telemetry.ui.theme.FirstGearTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstGearTheme {
                MainApp()
            }
        }
    }
}

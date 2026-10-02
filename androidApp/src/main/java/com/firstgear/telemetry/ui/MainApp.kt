package com.firstgear.telemetry.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.firstgear.engine.TelemetrySimulator
import com.firstgear.model.SafetyProfile
import com.firstgear.repository.TripLogRepository
import com.firstgear.telemetry.ui.components.AppTab
import com.firstgear.telemetry.ui.components.BottomNavBar
import com.firstgear.telemetry.ui.components.TelemetryTopBar
import com.firstgear.telemetry.ui.screens.AnalysisScreen
import com.firstgear.telemetry.ui.screens.LiveDriveScreen
import com.firstgear.telemetry.ui.screens.SafetyHubScreen
import com.firstgear.telemetry.ui.screens.TripLogScreen
import com.firstgear.telemetry.ui.theme.DeepObsidian

@Composable
fun MainApp(
    simulator: TelemetrySimulator = remember { TelemetrySimulator() },
    tripRepo: TripLogRepository = remember { TripLogRepository() }
) {
    val snapshot by simulator.snapshot.collectAsState()
    val trips by tripRepo.trips.collectAsState()
    val safetyProfile = remember { SafetyProfile() }

    var selectedTab by remember { mutableStateOf(AppTab.DRIVE) }

    Scaffold(
        topBar = {
            TelemetryTopBar()
        },
        bottomBar = {
            BottomNavBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        containerColor = DeepObsidian
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(DeepObsidian)
        ) {
            when (selectedTab) {
                AppTab.DRIVE -> LiveDriveScreen(
                    snapshot = snapshot,
                    onAccelerate = { simulator.accelerate() },
                    onBrake = { simulator.brake() },
                    onSteer = { simulator.steerHard() },
                    onInduceJerk = { simulator.induceJerk() },
                    onEndTrip = {
                        val record = simulator.createCompletedTripRecord()
                        tripRepo.addTrip(record)
                    }
                )
                AppTab.ANALYSIS -> AnalysisScreen(
                    snapshot = snapshot
                )
                AppTab.TRIP_LOG -> TripLogScreen(
                    trips = trips,
                    profile = safetyProfile
                )
                AppTab.SAFETY -> SafetyHubScreen(
                    profile = safetyProfile
                )
            }
        }
    }
}

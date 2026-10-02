package com.firstgear.telemetry.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Sos
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.TurnLeft
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstgear.model.TelemetrySnapshot
import com.firstgear.telemetry.ui.components.GForceVectorPod
import com.firstgear.telemetry.ui.components.IntersectionRatingPod
import com.firstgear.telemetry.ui.components.JerkShockPod
import com.firstgear.telemetry.ui.components.SignalBrakingPod
import com.firstgear.telemetry.ui.components.SpeedLimitShield
import com.firstgear.telemetry.ui.components.SpeedometerGauge
import com.firstgear.telemetry.ui.components.WaypointTimeline
import com.firstgear.telemetry.ui.theme.BorderSubtle
import com.firstgear.telemetry.ui.theme.CautionAmber
import com.firstgear.telemetry.ui.theme.CautionAmberDim
import com.firstgear.telemetry.ui.theme.CoralContainer
import com.firstgear.telemetry.ui.theme.DeepObsidian
import com.firstgear.telemetry.ui.theme.ElectricEmerald
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.HeadlineSm
import com.firstgear.telemetry.ui.theme.LabelCaps
import com.firstgear.telemetry.ui.theme.LabelTelemetryLg
import com.firstgear.telemetry.ui.theme.LabelTelemetrySm
import com.firstgear.telemetry.ui.theme.OnCoralContainer
import com.firstgear.telemetry.ui.theme.OnEmerald
import com.firstgear.telemetry.ui.theme.OnSurfaceHighLuminance
import com.firstgear.telemetry.ui.theme.OnSurfaceSlate
import com.firstgear.telemetry.ui.theme.SurfaceContainer
import com.firstgear.telemetry.ui.theme.SurfaceContainerHigh
import com.firstgear.telemetry.ui.theme.SurfaceContainerHighest
import com.firstgear.telemetry.ui.theme.SurfaceContainerLow
import com.firstgear.telemetry.ui.theme.VividCoral
import kotlin.math.roundToInt

@Composable
fun LiveDriveScreen(
    snapshot: TelemetrySnapshot,
    onAccelerate: () -> Unit,
    onBrake: () -> Unit,
    onSteer: () -> Unit,
    onInduceJerk: () -> Unit,
    onEndTrip: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showEndTripDialog by remember { mutableStateOf(false) }
    var showSosDialog by remember { mutableStateOf(false) }
    var showTripSavedConfirmation by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepObsidian)
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // 1. Active Trip Status Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainer, RoundedCornerShape(14.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(9.dp)
                                .background(ElectricEmeraldDim, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "AUTOMATED TRIP IN PROGRESS",
                            style = LabelCaps.copy(color = ElectricEmeraldDim, letterSpacing = 0.1.sp)
                        )
                    }

                    // Night drive / curfew tag
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(SurfaceContainerHighest, RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DarkMode,
                            contentDescription = null,
                            tint = CautionAmber,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "NIGHT DRIVE • CURFEW ACTIVE",
                            style = LabelCaps.copy(fontSize = 8.sp, color = CautionAmber)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Navigation,
                                contentDescription = null,
                                tint = ElectricEmeraldDim,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = snapshot.currentRoad,
                                style = HeadlineSm.copy(fontSize = 17.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                        Text(
                            text = snapshot.corridorDescription,
                            style = LabelTelemetrySm.copy(color = OnSurfaceSlate, fontSize = 11.sp),
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = null,
                                tint = ElectricEmeraldDim,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = snapshot.formattedDuration,
                                style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance)
                            )
                        }
                        Text(
                            text = "${"%.1f".format(snapshot.distanceMiles)} MI",
                            style = LabelTelemetryLg.copy(color = ElectricEmeraldDim, fontSize = 18.sp)
                        )
                    }
                }
            }
        }

        // 2. Speedometer & Speed Limit HUD Zone
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainerLow, RoundedCornerShape(14.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Advisory Warning banner if over tolerance
                if (snapshot.isOverTolerance) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CoralContainer.copy(alpha = 0.25f), RoundedCornerShape(8.dp))
                            .border(1.dp, VividCoral.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = CautionAmber,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "ADVISORY: SPEED LIMIT EXCEEDED (+${snapshot.excessPercentage}%)",
                                style = LabelCaps.copy(color = CautionAmber)
                            )
                        }
                        Text(
                            text = "+${snapshot.excessMph.roundToInt()} MPH OVER",
                            style = LabelTelemetrySm.copy(color = CautionAmber, fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                // Speed Dial Radial Arc Visualizer
                SpeedometerGauge(
                    currentSpeed = snapshot.speedMph,
                    speedLimit = snapshot.speedLimitMph
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Authentic Speed Limit Shield & Advisory Pill
                SpeedLimitShield(
                    speedLimit = snapshot.speedLimitMph,
                    currentSpeed = snapshot.speedMph
                )
            }
        }

        // 3. Interactive Driving Simulation Controls
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainer, RoundedCornerShape(12.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "SIMULATOR CONTROLS",
                    style = LabelCaps.copy(color = OnSurfaceSlate, fontSize = 9.sp)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    OutlinedButton(
                        onClick = onAccelerate,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(32.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("+Gas", style = LabelCaps.copy(fontSize = 10.sp))
                    }

                    OutlinedButton(
                        onClick = onBrake,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(32.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp)
                    ) {
                        Icon(Icons.Default.Remove, contentDescription = null, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("-Brake", style = LabelCaps.copy(fontSize = 10.sp))
                    }

                    OutlinedButton(
                        onClick = onSteer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(32.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp)
                    ) {
                        Icon(Icons.Default.TurnLeft, contentDescription = null, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Turn", style = LabelCaps.copy(fontSize = 10.sp))
                    }

                    OutlinedButton(
                        onClick = onInduceJerk,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(32.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 8.dp)
                    ) {
                        Icon(Icons.Default.Vibration, contentDescription = null, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Jerk", style = LabelCaps.copy(fontSize = 10.sp))
                    }
                }
            }
        }

        // 4. Real-time Sensor Telemetry Pods (2x2 Grid Layout)
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    SignalBrakingPod(smoothnessPct = snapshot.brakingSmoothnessPct)
                }
                Box(modifier = Modifier.weight(1f)) {
                    IntersectionRatingPod(rating = snapshot.intersectionRating)
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    GForceVectorPod(lateralG = snapshot.lateralG, longitudinalG = snapshot.longitudinalG)
                }
                Box(modifier = Modifier.weight(1f)) {
                    JerkShockPod(harshJerks = snapshot.harshJerksDetected)
                }
            }
        }

        // 5. Route Mini Visualizer & Horizon
        WaypointTimeline(
            passed = snapshot.waypointPassed,
            current = snapshot.waypointCurrent,
            next = snapshot.waypointNext,
            etaMinutes = snapshot.etaMinutes,
            speedDeltaLabel = "+${snapshot.excessMph.roundToInt()} MPH"
        )

        // 6. Cockpit Quick Controls / Action Strip
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainerHigh, RoundedCornerShape(14.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
                .padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(38.dp)
                            .background(ElectricEmerald.copy(alpha = 0.12f), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sync,
                            contentDescription = null,
                            tint = ElectricEmeraldDim,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Trip Auto-Tracking Active",
                            style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                            text = "CLOUD SYNCING • 5Hz IMU",
                            style = LabelCaps.copy(color = OnSurfaceSlate)
                        )
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // SOS Button
                    IconButton(
                        onClick = { showSosDialog = true },
                        modifier = Modifier
                            .size(46.dp)
                            .background(CoralContainer.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sos,
                            contentDescription = "Emergency SOS",
                            tint = VividCoral,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    // End Trip Button
                    Button(
                        onClick = { showEndTripDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ElectricEmeraldDim,
                            contentColor = OnEmerald
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(46.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.StopCircle,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("End Trip", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

    // End Trip Confirmation Dialog
    if (showEndTripDialog) {
        AlertDialog(
            onDismissRequest = { showEndTripDialog = false },
            title = { Text("Complete Drive Session?", fontWeight = FontWeight.Bold) },
            text = {
                Text("This will save ${"%.1f".format(snapshot.distanceMiles)} miles to your California Provisional Permit logbook and compute your driving score summary.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showEndTripDialog = false
                        onEndTrip()
                        showTripSavedConfirmation = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricEmeraldDim, contentColor = OnEmerald)
                ) {
                    Text("Confirm & Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEndTripDialog = false }) {
                    Text("Continue Drive")
                }
            },
            containerColor = SurfaceContainerHigh,
            textContentColor = OnSurfaceHighLuminance,
            titleContentColor = OnSurfaceHighLuminance
        )
    }

    // Emergency SOS Dialog
    if (showSosDialog) {
        AlertDialog(
            onDismissRequest = { showSosDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Sos, contentDescription = null, tint = VividCoral)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Emergency Roadside Relay", color = VividCoral, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column {
                    Text("Broadcasting high-precision vehicle telemetry & telemetry coordinates to roadside dispatch and parents:")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("• GPS: 37.4220° N, 122.0841° W (El Camino Real)", color = ElectricEmeraldDim)
                    Text("• Speed: ${snapshot.speedMph.roundToInt()} MPH • Corridor: Palo Alto", color = OnSurfaceSlate)
                    Text("• Contacts: Elena & David Rivera ((650) 555-0199)", color = OnSurfaceSlate)
                }
            },
            confirmButton = {
                Button(
                    onClick = { showSosDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = VividCoral, contentColor = DeepObsidian)
                ) {
                    Text("Call AAA (1-800-AAA-HELP)", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showSosDialog = false }) {
                    Text("Dismiss")
                }
            },
            containerColor = SurfaceContainerHigh,
            textContentColor = OnSurfaceHighLuminance,
            titleContentColor = OnSurfaceHighLuminance
        )
    }

    // Trip Saved Toast Notification
    if (showTripSavedConfirmation) {
        AlertDialog(
            onDismissRequest = { showTripSavedConfirmation = false },
            title = { Text("Trip Successfully Logged!", color = ElectricEmeraldDim, fontWeight = FontWeight.Bold) },
            text = {
                Text("Saved to Trip Logbook. Overall safety score: ${snapshot.safetyScore}/100. Hours credited to your DMV provisional practice total.")
            },
            confirmButton = {
                Button(
                    onClick = { showTripSavedConfirmation = false },
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricEmeraldDim, contentColor = OnEmerald)
                ) {
                    Text("View Logbook")
                }
            },
            containerColor = SurfaceContainerHigh,
            textContentColor = OnSurfaceHighLuminance
        )
    }
}

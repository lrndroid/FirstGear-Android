package com.firstgear.telemetry.ui.screens

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
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstgear.model.SafetyProfile
import com.firstgear.telemetry.ui.theme.BorderSubtle
import com.firstgear.telemetry.ui.theme.CautionAmber
import com.firstgear.telemetry.ui.theme.DeepObsidian
import com.firstgear.telemetry.ui.theme.ElectricEmerald
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.HeadlineMd
import com.firstgear.telemetry.ui.theme.HeadlineSm
import com.firstgear.telemetry.ui.theme.LabelCaps
import com.firstgear.telemetry.ui.theme.LabelTelemetrySm
import com.firstgear.telemetry.ui.theme.OnEmerald
import com.firstgear.telemetry.ui.theme.OnSurfaceHighLuminance
import com.firstgear.telemetry.ui.theme.OnSurfaceSlate
import com.firstgear.telemetry.ui.theme.SurfaceContainer
import com.firstgear.telemetry.ui.theme.SurfaceContainerHigh
import com.firstgear.telemetry.ui.theme.SurfaceContainerHighest

@Composable
fun SafetyHubScreen(
    profile: SafetyProfile = SafetyProfile(),
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var curfewEnabled by remember { mutableStateOf(profile.isCurfewEnforced) }
    var speedAdvisoryEnabled by remember { mutableStateOf(true) }
    var guardianNotificationsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepObsidian)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                Text(
                    text = "PARENT & TEEN HUB",
                    style = HeadlineMd.copy(fontWeight = FontWeight.Bold, color = OnSurfaceHighLuminance)
                )
                Text(
                    text = "Collaborative Coaching & Safety Envelope",
                    style = LabelTelemetrySm.copy(color = OnSurfaceSlate)
                )
            }
            Box(
                modifier = Modifier
                    .background(ElectricEmerald.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text("ENCRYPTED LINK", style = LabelCaps.copy(color = ElectricEmeraldDim), maxLines = 1)
            }
        }

        // 1. Teen Driver Profile Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainer, RoundedCornerShape(14.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(54.dp)
                        .background(SurfaceContainerHigh, CircleShape)
                        .border(2.dp, ElectricEmeraldDim, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = ElectricEmeraldDim,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = profile.teenName,
                        style = HeadlineSm.copy(fontWeight = FontWeight.Bold, color = OnSurfaceHighLuminance)
                    )
                    Text(
                        text = profile.licenseStatus,
                        style = LabelTelemetrySm.copy(color = ElectricEmeraldDim, fontSize = 11.sp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Assigned Vehicle: 2024 Honda Civic Sport (IMU Fused)",
                        style = LabelCaps.copy(color = OnSurfaceSlate, fontSize = 9.sp)
                    )
                }
            }
        }

        // 2. Curfew Management
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainer, RoundedCornerShape(12.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
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
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(28.dp)
                                .background(CautionAmber.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                        ) {
                            Icon(Icons.Default.DarkMode, contentDescription = null, tint = CautionAmber, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("NIGHT CURFEW RESTRICTION", style = LabelCaps.copy(color = OnSurfaceSlate))
                            Text(
                                text = "${profile.curfewStart} – ${profile.curfewEnd}",
                                style = HeadlineSm.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            )
                        }
                    }

                    Switch(
                        checked = curfewEnabled,
                        onCheckedChange = { curfewEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = ElectricEmeraldDim,
                            checkedTrackColor = ElectricEmerald.copy(alpha = 0.3f),
                            uncheckedThumbColor = OnSurfaceSlate,
                            uncheckedTrackColor = SurfaceContainerHighest
                        )
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Complies with DMV California provisional permit nighttime restriction (no driving without licensed adult aged 25+).",
                    style = LabelTelemetrySm.copy(color = OnSurfaceSlate, fontSize = 11.sp)
                )
            }
        }

        // 3. Advisory Speed Envelope
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainer, RoundedCornerShape(12.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
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
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(28.dp)
                                .background(ElectricEmerald.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                        ) {
                            Icon(Icons.Default.Speed, contentDescription = null, tint = ElectricEmeraldDim, modifier = Modifier.size(16.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("SPEED TOLERANCE ADVISORY", style = LabelCaps.copy(color = OnSurfaceSlate))
                            Text(
                                text = "+${profile.speedToleranceThresholdMph} MPH Non-Punitive Margin",
                                style = HeadlineSm.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                            )
                        }
                    }

                    Switch(
                        checked = speedAdvisoryEnabled,
                        onCheckedChange = { speedAdvisoryEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = ElectricEmeraldDim,
                            checkedTrackColor = ElectricEmerald.copy(alpha = 0.3f),
                            uncheckedThumbColor = OnSurfaceSlate,
                            uncheckedTrackColor = SurfaceContainerHighest
                        )
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Real-time audio and visual HUD chime triggers when exceeding posted speed limits by more than 10%.",
                    style = LabelTelemetrySm.copy(color = OnSurfaceSlate, fontSize = 11.sp)
                )
            }
        }

        // 4. Guardians & Contacts
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainer, RoundedCornerShape(12.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = ElectricEmeraldDim, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("GUARDIAN ALERT RELAY", style = LabelCaps.copy(color = OnSurfaceSlate))
                }

                Spacer(modifier = Modifier.height(8.dp))

                profile.guardians.forEach { guardian ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(guardian, style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Phone, contentDescription = null, tint = ElectricEmeraldDim, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(profile.emergencyPhone, style = LabelCaps.copy(color = OnSurfaceSlate))
                        }
                    }
                }
            }
        }

        // 5. Emergency Hotlines
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainerHigh, RoundedCornerShape(12.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.SupportAgent, contentDescription = null, tint = CautionAmber, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("ROADSIDE HOTLINE", style = LabelCaps.copy(color = OnSurfaceSlate))
                        Text(profile.roadsideAssistancePhone, style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontWeight = FontWeight.Bold))
                    }
                }
                Box(
                    modifier = Modifier
                        .background(CautionAmber.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("24/7 AAA DISPATCH", style = LabelCaps.copy(color = CautionAmber))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

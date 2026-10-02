package com.firstgear.telemetry.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.Icon
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
import com.firstgear.model.TripRecord
import com.firstgear.telemetry.ui.theme.BorderSubtle
import com.firstgear.telemetry.ui.theme.CautionAmber
import com.firstgear.telemetry.ui.theme.DeepObsidian
import com.firstgear.telemetry.ui.theme.ElectricEmerald
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.HeadlineMd
import com.firstgear.telemetry.ui.theme.HeadlineSm
import com.firstgear.telemetry.ui.theme.LabelCaps
import com.firstgear.telemetry.ui.theme.LabelTelemetryLg
import com.firstgear.telemetry.ui.theme.LabelTelemetrySm
import com.firstgear.telemetry.ui.theme.OnSurfaceHighLuminance
import com.firstgear.telemetry.ui.theme.OnSurfaceSlate
import com.firstgear.telemetry.ui.theme.SurfaceContainer
import com.firstgear.telemetry.ui.theme.SurfaceContainerHigh
import com.firstgear.telemetry.ui.theme.SurfaceContainerHighest

@Composable
fun TripLogScreen(
    trips: List<TripRecord>,
    profile: SafetyProfile = SafetyProfile(),
    modifier: Modifier = Modifier
) {
    var selectedFilter by remember { mutableStateOf("All") }

    val filteredTrips = remember(trips, selectedFilter) {
        when (selectedFilter) {
            "Night" -> trips.filter { it.isNightDrive }
            "Top Scores" -> trips.filter { it.overallScore >= 90 }
            else -> trips
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DeepObsidian)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "TRIP LOGBOOK",
                        style = HeadlineMd.copy(fontWeight = FontWeight.Bold, color = OnSurfaceHighLuminance)
                    )
                    Text(
                        text = profile.licenseStatus,
                        style = LabelTelemetrySm.copy(color = OnSurfaceSlate)
                    )
                }

                Box(
                    modifier = Modifier
                        .background(SurfaceContainerHigh, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("${trips.size} DRIVES LOGGED", style = LabelCaps.copy(color = ElectricEmeraldDim))
                }
            }
        }

        item {
            // DMV Provisional License Hours Tracker Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainer, RoundedCornerShape(14.dp))
                    .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.HourglassBottom, contentDescription = null, tint = ElectricEmeraldDim, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("PROVISIONAL LICENSE REQUIREMENT", style = LabelCaps.copy(color = OnSurfaceSlate))
                        }
                        Text("${profile.totalProgressPct}% Completed", style = LabelCaps.copy(color = ElectricEmeraldDim, fontWeight = FontWeight.Bold))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Total Hours Progress
                    Text(
                        text = "Total Supervised Hours: ${profile.totalHoursCompleted} / ${profile.totalHoursRequired.toInt()} hrs",
                        style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontWeight = FontWeight.SemiBold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .background(SurfaceContainerHighest, RoundedCornerShape(4.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(profile.totalProgressPct / 100f)
                                .height(8.dp)
                                .background(ElectricEmeraldDim, RoundedCornerShape(4.dp))
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Night Hours Progress
                    Text(
                        text = "Mandatory Night Hours: ${profile.nightHoursCompleted} / ${profile.nightHoursRequired.toInt()} hrs",
                        style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontWeight = FontWeight.SemiBold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .background(SurfaceContainerHighest, RoundedCornerShape(4.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(profile.nightProgressPct / 100f)
                                .height(8.dp)
                                .background(CautionAmber, RoundedCornerShape(4.dp))
                        )
                    }
                }
            }
        }

        item {
            // Filter Chips
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(label = "All Drives", selected = selectedFilter == "All") { selectedFilter = "All" }
                FilterChip(label = "Night Practice", selected = selectedFilter == "Night") { selectedFilter = "Night" }
                FilterChip(label = "Score 90+", selected = selectedFilter == "Top Scores") { selectedFilter = "Top Scores" }
            }
        }

        items(filteredTrips, key = { it.id }) { trip ->
            TripHistoryCard(trip = trip)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun TripHistoryCard(trip: TripRecord) {
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
                        Icon(Icons.Default.DirectionsCar, contentDescription = null, tint = ElectricEmeraldDim, modifier = Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(trip.timestampFormatted, style = LabelCaps.copy(color = OnSurfaceSlate))
                }

                if (trip.isNightDrive) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(SurfaceContainerHighest, RoundedCornerShape(10.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Icon(Icons.Default.DarkMode, contentDescription = null, tint = CautionAmber, modifier = Modifier.size(11.dp))
                        Spacer(modifier = Modifier.width(3.dp))
                        Text("Night Practice", style = LabelCaps.copy(fontSize = 8.sp, color = CautionAmber))
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = trip.title,
                style = HeadlineSm.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = trip.routeDescription,
                style = LabelTelemetrySm.copy(color = OnSurfaceSlate, fontSize = 11.sp),
                modifier = Modifier.padding(top = 2.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerHigh, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Column {
                    Text("DISTANCE & TIME", style = LabelCaps.copy(fontSize = 8.sp, color = OnSurfaceSlate))
                    Text(
                        text = "${trip.distanceMiles} MI • ${trip.durationMinutes} mins",
                        style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontWeight = FontWeight.SemiBold)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SMOOTHNESS", style = LabelCaps.copy(fontSize = 8.sp, color = OnSurfaceSlate))
                    Text(
                        text = "${trip.smoothnessScorePct}%",
                        style = LabelTelemetrySm.copy(color = ElectricEmeraldDim, fontWeight = FontWeight.SemiBold)
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text("SAFETY SCORE", style = LabelCaps.copy(fontSize = 8.sp, color = OnSurfaceSlate))
                    Text(
                        text = "${trip.overallScore}",
                        style = LabelTelemetryLg.copy(color = ElectricEmeraldDim, fontSize = 16.sp)
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if (selected) ElectricEmeraldDim else SurfaceContainerHigh,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            style = LabelCaps.copy(
                color = if (selected) DeepObsidian else OnSurfaceHighLuminance,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
            )
        )
    }
}

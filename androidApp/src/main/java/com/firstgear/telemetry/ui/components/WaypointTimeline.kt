package com.firstgear.telemetry.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.AltRoute
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstgear.telemetry.ui.theme.BorderSubtle
import com.firstgear.telemetry.ui.theme.CautionAmber
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.LabelCaps
import com.firstgear.telemetry.ui.theme.LabelTelemetrySm
import com.firstgear.telemetry.ui.theme.OnSurfaceHighLuminance
import com.firstgear.telemetry.ui.theme.OnSurfaceSlate
import com.firstgear.telemetry.ui.theme.OutlineSlate
import com.firstgear.telemetry.ui.theme.SurfaceContainer
import com.firstgear.telemetry.ui.theme.SurfaceContainerHigh
import com.firstgear.telemetry.ui.theme.SurfaceContainerHighest
import com.firstgear.telemetry.ui.theme.SurfaceContainerLowest

@Composable
fun WaypointTimeline(
    passed: String = "Charleston Rd",
    current: String = "Oregon Expy",
    next: String = "University Ave",
    etaMinutes: Int = 6,
    speedDeltaLabel: String = "+7 MPH",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
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
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.AltRoute,
                        contentDescription = null,
                        tint = ElectricEmeraldDim,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("CURRENT WAYPOINT HORIZON", style = LabelCaps.copy(color = OnSurfaceSlate))
                }
                Text("ETA: $etaMinutes mins", style = LabelTelemetrySm.copy(color = ElectricEmeraldDim))
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Stylized Path Timeline
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerLowest, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                // Passed Waypoint
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(12.dp)
                            .background(ElectricEmeraldDim.copy(alpha = 0.35f), CircleShape)
                    ) {
                        Box(modifier = Modifier.size(6.dp).background(ElectricEmeraldDim, CircleShape))
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text("PASSED", style = LabelCaps.copy(fontSize = 8.sp, color = OnSurfaceSlate))
                        Text(passed, style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontSize = 11.sp))
                    }
                }

                // Connector
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp)
                        .height(2.dp)
                        .background(ElectricEmeraldDim.copy(alpha = 0.4f))
                )

                // Current Waypoint
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(SurfaceContainerHigh, RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Box(modifier = Modifier.size(8.dp).background(CautionAmber, CircleShape))
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text("NOW ($speedDeltaLabel)", style = LabelCaps.copy(fontSize = 8.sp, color = CautionAmber))
                        Text(current, style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontSize = 11.sp))
                    }
                }

                // Connector
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp)
                        .height(2.dp)
                        .background(SurfaceContainerHighest)
                )

                // Next Waypoint
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).background(OutlineSlate, CircleShape))
                    Spacer(modifier = Modifier.width(6.dp))
                    Column {
                        Text("NEXT", style = LabelCaps.copy(fontSize = 8.sp, color = OnSurfaceSlate))
                        Text(next, style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontSize = 11.sp))
                    }
                }
            }
        }
    }
}

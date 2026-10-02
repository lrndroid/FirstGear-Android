package com.firstgear.telemetry.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Signpost
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstgear.telemetry.ui.theme.BorderSubtle
import com.firstgear.telemetry.ui.theme.CautionAmber
import com.firstgear.telemetry.ui.theme.ElectricEmerald
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.HeadlineLgMobile
import com.firstgear.telemetry.ui.theme.LabelCaps
import com.firstgear.telemetry.ui.theme.LabelTelemetrySm
import com.firstgear.telemetry.ui.theme.OnSurfaceHighLuminance
import com.firstgear.telemetry.ui.theme.OnSurfaceSlate
import com.firstgear.telemetry.ui.theme.SurfaceContainer
import com.firstgear.telemetry.ui.theme.SurfaceContainerHighest
import com.firstgear.telemetry.ui.theme.VividCoral

@Composable
fun TelemetryPodCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceContainer, RoundedCornerShape(12.dp))
            .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
            .padding(14.dp)
    ) {
        content()
    }
}

@Composable
fun SignalBrakingPod(
    smoothnessPct: Int = 88,
    lastStopG: Float = 0.18f,
    lastStopLocation: String = "San Antonio Rd"
) {
    val animatedProgress by animateFloatAsState(
        targetValue = smoothnessPct / 100f,
        animationSpec = tween(500),
        label = "brakeProgress"
    )

    TelemetryPodCard {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PodIcon(Icons.Default.Traffic)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("SIGNAL BRAKING", style = LabelCaps.copy(color = OnSurfaceSlate))
                }
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(ElectricEmeraldDim, CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "$smoothnessPct%",
                    style = HeadlineLgMobile.copy(color = ElectricEmeraldDim, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Smooth Decel",
                    style = LabelCaps.copy(color = OnSurfaceSlate)
                )
            }

            // Progress Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(vertical = 1.dp)
                    .background(SurfaceContainerHighest, RoundedCornerShape(3.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(animatedProgress)
                        .height(6.dp)
                        .background(ElectricEmeraldDim, RoundedCornerShape(3.dp))
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerHighest.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "LAST STOP: $lastStopLocation",
                        style = LabelCaps.copy(fontSize = 9.sp, color = OnSurfaceSlate)
                    )
                    Text(
                        text = "Gradual & Smooth",
                        style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance)
                    )
                }
                Text(
                    text = "${"%.2f".format(lastStopG)}g",
                    style = LabelTelemetrySm.copy(color = ElectricEmeraldDim, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun IntersectionRatingPod(
    rating: Float = 0.94f,
    approachDescription: String = "Approaching Stanford Ave green signal. Rate of closure within green envelope."
) {
    TelemetryPodCard {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PodIcon(Icons.Default.Signpost)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("INTERSECTION RATING", style = LabelCaps.copy(color = OnSurfaceSlate))
                }
                Box(
                    modifier = Modifier
                        .background(ElectricEmeraldDim.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text("SAFE APPROACH", style = LabelCaps.copy(color = ElectricEmeraldDim))
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "%.2f".format(rating),
                    style = HeadlineLgMobile.copy(color = OnSurfaceHighLuminance, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "OPTIMAL",
                    style = LabelCaps.copy(color = ElectricEmeraldDim, fontWeight = FontWeight.Bold)
                )
            }

            Text(
                text = approachDescription,
                style = LabelTelemetrySm.copy(color = OnSurfaceSlate, fontSize = 11.sp),
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerHighest.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = ElectricEmeraldDim,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "No rapid throttle releases detected",
                    style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontSize = 11.sp)
                )
            }
        }
    }
}

@Composable
fun GForceVectorPod(
    lateralG: Float,
    longitudinalG: Float
) {
    TelemetryPodCard {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PodIcon(Icons.Default.Explore)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("IMU G-FORCE VECTOR", style = LabelCaps.copy(color = OnSurfaceSlate))
                }
                Text("5 Hz Sync", style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontSize = 11.sp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            FrictionCircle(lateralG = lateralG, longitudinalG = longitudinalG)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Stability: Nominal", style = LabelCaps.copy(color = OnSurfaceSlate))
                Text("Inside Grip Envelope", style = LabelCaps.copy(color = ElectricEmeraldDim, fontWeight = FontWeight.Bold))
            }
        }
    }
}

@Composable
fun JerkShockPod(
    harshJerks: Int = 0,
    dampingBenchmark: String = "<0.4g/s comfort benchmark",
    latestEvent: String = "Pavement Seam absorbed at 38 MPH"
) {
    TelemetryPodCard {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PodIcon(Icons.Default.Vibration)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("JERK & SHOCK IMU", style = LabelCaps.copy(color = OnSurfaceSlate))
                }
                Box(
                    modifier = Modifier
                        .background(
                            if (harshJerks == 0) ElectricEmeraldDim.copy(alpha = 0.15f) else VividCoral.copy(alpha = 0.15f),
                            RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (harshJerks == 0) "SMOOTH LEG" else "SPIKE LOGGED",
                        style = LabelCaps.copy(color = if (harshJerks == 0) ElectricEmeraldDim else VividCoral)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = harshJerks.toString(),
                    style = HeadlineLgMobile.copy(
                        color = if (harshJerks == 0) ElectricEmeraldDim else VividCoral,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Harsh Jerks Detected",
                    style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance)
                )
            }

            Text(
                text = "Damping and pitch transition rates remain well within $dampingBenchmark.",
                style = LabelTelemetrySm.copy(color = OnSurfaceSlate, fontSize = 11.sp),
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerHighest.copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    Box(modifier = Modifier.size(6.dp).background(ElectricEmeraldDim, CircleShape))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = latestEvent,
                        style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontSize = 11.sp),
                        maxLines = 1
                    )
                }
                Text("Filtered", style = LabelCaps.copy(color = OnSurfaceSlate))
            }
        }
    }
}

@Composable
private fun PodIcon(icon: ImageVector) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(24.dp)
            .background(ElectricEmerald.copy(alpha = 0.12f), RoundedCornerShape(6.dp))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = ElectricEmeraldDim,
            modifier = Modifier.size(15.dp)
        )
    }
}

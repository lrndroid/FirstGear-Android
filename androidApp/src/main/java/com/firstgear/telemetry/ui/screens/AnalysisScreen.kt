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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstgear.model.TelemetrySnapshot
import com.firstgear.telemetry.ui.theme.BorderSubtle
import com.firstgear.telemetry.ui.theme.CautionAmber
import com.firstgear.telemetry.ui.theme.CautionAmberDim
import com.firstgear.telemetry.ui.theme.DeepObsidian
import com.firstgear.telemetry.ui.theme.ElectricEmerald
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.HeadlineLgMobile
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
fun AnalysisScreen(
    snapshot: TelemetrySnapshot,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

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
            Column {
                Text(
                    text = "TELEMETRY ANALYSIS",
                    style = HeadlineMd.copy(fontWeight = FontWeight.Bold, color = OnSurfaceHighLuminance)
                )
                Text(
                    text = "Post-Trip Telemetry & Habit Reinforcement",
                    style = LabelTelemetrySm.copy(color = OnSurfaceSlate)
                )
            }
            Box(
                modifier = Modifier
                    .background(ElectricEmerald.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text("5Hz HIGH RES", style = LabelCaps.copy(color = ElectricEmeraldDim))
            }
        }

        // 1. Overall Score Hero Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainer, RoundedCornerShape(14.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text("OVERALL SAFETY RATING", style = LabelCaps.copy(color = OnSurfaceSlate))
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "${snapshot.safetyScore}",
                            style = HeadlineLgMobile.copy(fontSize = 42.sp, color = ElectricEmeraldDim, fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "/100",
                            style = HeadlineSm.copy(color = OnSurfaceSlate),
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }
                    Text(
                        text = "Mastery Level • Safe & Smooth Driver",
                        style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontWeight = FontWeight.SemiBold)
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(72.dp)
                        .background(SurfaceContainerHigh, CircleShape)
                        .border(3.dp, ElectricEmeraldDim, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = null,
                        tint = ElectricEmeraldDim,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        }

        // 2. Telemetry Breakdown Matrix
        Text("PERFORMANCE METRICS", style = LabelCaps.copy(color = OnSurfaceSlate, letterSpacing = 0.1.sp))

        AnalysisMetricRow(
            icon = Icons.Default.Speed,
            title = "Speed Limit Compliance",
            scoreText = "89%",
            description = "Maintained legal envelope for 89% of session. Minor +7 MPH drift on El Camino arterial.",
            isGood = true
        )

        AnalysisMetricRow(
            icon = Icons.Default.Traffic,
            title = "Braking & Deceleration Quality",
            scoreText = "94%",
            description = "Exceptional progressive braking before intersections. Max deceleration peak: -0.21g.",
            isGood = true
        )

        AnalysisMetricRow(
            icon = Icons.Default.Timeline,
            title = "Cornering & Lateral G Stability",
            scoreText = "96%",
            description = "Turn radiuses executed smoothly within grip limits (<0.35g threshold).",
            isGood = true
        )

        // 3. Speed Envelope Distribution
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
                    Text("SPEED ENVELOPE DISTRIBUTION", style = LabelCaps.copy(color = OnSurfaceSlate))
                    Text("18m 42s Monitored", style = LabelCaps.copy(color = ElectricEmeraldDim))
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Segmented Progress Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(SurfaceContainerHighest, RoundedCornerShape(5.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .weight(0.85f)
                            .height(10.dp)
                            .background(ElectricEmeraldDim, RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp))
                    )
                    Box(
                        modifier = Modifier
                            .weight(0.12f)
                            .height(10.dp)
                            .background(CautionAmber)
                    )
                    Box(
                        modifier = Modifier
                            .weight(0.03f)
                            .height(10.dp)
                            .background(SurfaceContainerHighest, RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp))
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LegendItem(color = ElectricEmeraldDim, label = "Legal Speed (85%)")
                    LegendItem(color = CautionAmber, label = "Advisory +1-9% (12%)")
                    LegendItem(color = OnSurfaceSlate, label = "Signal Stop (3%)")
                }
            }
        }

        // 4. Coaching Insights Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceContainerHigh, RoundedCornerShape(12.dp))
                .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(32.dp)
                        .background(CautionAmber.copy(alpha = 0.15f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = CautionAmber,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "COACHING TIP: NIGHTTIME ARTERIALS",
                        style = LabelCaps.copy(color = CautionAmber, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Great job managing curve deceleration on Oregon Expressway. During night driving conditions, keep your eye on cross-street traffic signals 2 blocks ahead to begin coasting earlier.",
                        style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance, fontSize = 12.sp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun AnalysisMetricRow(
    icon: ImageVector,
    title: String,
    scoreText: String,
    description: String,
    isGood: Boolean
) {
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
                        Icon(icon, contentDescription = null, tint = ElectricEmeraldDim, modifier = Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(title, style = HeadlineSm.copy(fontSize = 14.sp, fontWeight = FontWeight.SemiBold))
                }
                Text(
                    text = scoreText,
                    style = LabelTelemetryLg.copy(color = if (isGood) ElectricEmeraldDim else CautionAmberDim)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                style = LabelTelemetrySm.copy(color = OnSurfaceSlate, fontSize = 11.sp)
            )
        }
    }
}

@Composable
private fun LegendItem(color: androidx.compose.ui.graphics.Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(8.dp).background(color, CircleShape))
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = label, style = LabelCaps.copy(fontSize = 9.sp, color = OnSurfaceSlate))
    }
}

package com.firstgear.telemetry.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstgear.telemetry.ui.theme.CautionAmber
import com.firstgear.telemetry.ui.theme.CautionAmberDim
import com.firstgear.telemetry.ui.theme.DisplayHeroMobile
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.LabelCaps
import com.firstgear.telemetry.ui.theme.OnSurfaceSlate
import com.firstgear.telemetry.ui.theme.SurfaceContainer
import com.firstgear.telemetry.ui.theme.VividCoral
import kotlin.math.roundToInt

@Composable
fun SpeedometerGauge(
    currentSpeed: Float,
    speedLimit: Int = 35,
    maxGaugeSpeed: Float = 80f,
    modifier: Modifier = Modifier
) {
    val animatedSpeed by animateFloatAsState(
        targetValue = currentSpeed,
        animationSpec = tween(durationMillis = 300),
        label = "speedAnimation"
    )

    // Gauge geometry
    val startAngle = 140f
    val sweepAngle = 260f

    val limitFraction = (speedLimit / maxGaugeSpeed).coerceIn(0f, 1f)
    val legalSweep = sweepAngle * limitFraction

    val speedFraction = (animatedSpeed / maxGaugeSpeed).coerceIn(0f, 1f)
    val totalActiveSweep = sweepAngle * speedFraction

    val isOverLimit = animatedSpeed > speedLimit
    val excessSweep = (totalActiveSweep - legalSweep).coerceAtLeast(0f)

    Box(
        modifier = modifier.size(260.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            val strokeWidth = 14.dp.toPx()
            val diameter = size.minDimension - strokeWidth
            val topLeft = Offset((size.width - diameter) / 2f, (size.height - diameter) / 2f)
            val arcSize = Size(diameter, diameter)

            // 1. Background Track Arc
            drawArc(
                color = Color(148, 163, 184, 38), // rgba(148, 163, 184, 0.15)
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // 2. Legal Limit Base Track Arc (Electric Emerald)
            val legalTrackSweep = if (totalActiveSweep < legalSweep) totalActiveSweep else legalSweep
            if (legalTrackSweep > 0f) {
                drawArc(
                    color = ElectricEmeraldDim,
                    startAngle = startAngle,
                    sweepAngle = legalTrackSweep,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            // 3. Exceedance Segment (Amber / Coral warning)
            if (isOverLimit && excessSweep > 0f) {
                val warningColor = if (animatedSpeed - speedLimit > 10) VividCoral else CautionAmberDim
                drawArc(
                    color = warningColor,
                    startAngle = startAngle + legalSweep,
                    sweepAngle = excessSweep,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth + 2.dp.toPx(), cap = StrokeCap.Round)
                )
            }
        }

        // Center Telemetry Well
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Text(
                text = "GROUND SPEED",
                style = LabelCaps.copy(letterSpacing = 0.15.sp, color = OnSurfaceSlate)
            )

            Text(
                text = animatedSpeed.roundToInt().toString(),
                style = DisplayHeroMobile
            )

            Text(
                text = "MILES PER HOUR",
                style = LabelCaps.copy(
                    color = if (isOverLimit) CautionAmber else ElectricEmeraldDim,
                    letterSpacing = 0.12.sp
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            // IMU 5.0 Hz status badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(SurfaceContainer, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(if (isOverLimit) CautionAmber else ElectricEmeraldDim, CircleShape)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "IMU FUSED: 5.0 Hz",
                    style = LabelCaps.copy(color = OnSurfaceSlate)
                )
            }
        }
    }
}

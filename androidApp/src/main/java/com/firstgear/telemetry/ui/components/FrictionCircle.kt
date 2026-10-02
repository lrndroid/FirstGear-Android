package com.firstgear.telemetry.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.LabelCaps
import com.firstgear.telemetry.ui.theme.LabelTelemetrySm
import com.firstgear.telemetry.ui.theme.OnSurfaceHighLuminance
import com.firstgear.telemetry.ui.theme.OnSurfaceSlate
import com.firstgear.telemetry.ui.theme.SurfaceContainerHighest
import com.firstgear.telemetry.ui.theme.SurfaceContainerLowest

@Composable
fun FrictionCircle(
    lateralG: Float,
    longitudinalG: Float,
    modifier: Modifier = Modifier
) {
    val animLatG by animateFloatAsState(
        targetValue = lateralG,
        animationSpec = tween(durationMillis = 200),
        label = "animLatG"
    )
    val animLongG by animateFloatAsState(
        targetValue = longitudinalG,
        animationSpec = tween(durationMillis = 200),
        label = "animLongG"
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        // 2D Friction Circle Graphic
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(76.dp)
                .background(SurfaceContainerLowest, shape = RoundedCornerShape(38.dp))
        ) {
            Canvas(modifier = Modifier.size(76.dp)) {
                val center = Offset(size.width / 2f, size.height / 2f)
                val radiusMax = (size.minDimension / 2f) - 6.dp.toPx()

                // Concentric circles
                drawCircle(
                    color = Color(148, 163, 184, 40),
                    radius = radiusMax,
                    center = center,
                    style = Stroke(width = 1.dp.toPx())
                )
                drawCircle(
                    color = Color(148, 163, 184, 30),
                    radius = radiusMax * 0.6f,
                    center = center,
                    style = Stroke(width = 1.dp.toPx())
                )
                drawCircle(
                    color = Color(148, 163, 184, 25),
                    radius = radiusMax * 0.3f,
                    center = center,
                    style = Stroke(width = 1.dp.toPx())
                )

                // Crosshairs
                drawLine(
                    color = Color(148, 163, 184, 50),
                    start = Offset(6.dp.toPx(), center.y),
                    end = Offset(size.width - 6.dp.toPx(), center.y),
                    strokeWidth = 1.dp.toPx()
                )
                drawLine(
                    color = Color(148, 163, 184, 50),
                    start = Offset(center.x, 6.dp.toPx()),
                    end = Offset(center.x, size.height - 6.dp.toPx()),
                    strokeWidth = 1.dp.toPx()
                )

                // Dynamic G-force point (clamp to radius)
                val dotX = (center.x + (animLatG.coerceIn(-1f, 1f) * radiusMax)).coerceIn(8f, size.width - 8f)
                val dotY = (center.y - (animLongG.coerceIn(-1f, 1f) * radiusMax)).coerceIn(8f, size.height - 8f)

                drawCircle(
                    color = ElectricEmeraldDim,
                    radius = 4.5.dp.toPx(),
                    center = Offset(dotX, dotY)
                )
            }
        }

        // Telemetry details readout
        Column(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            val latSign = if (animLatG >= 0) "+" else ""
            val latLabel = if (animLatG >= 0.05f) "Gentle Right" else if (animLatG <= -0.05f) "Gentle Left" else "Center Track"

            Box(
                modifier = Modifier
                    .background(SurfaceContainerHighest.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Column {
                    Text("LATERAL ACCEL", style = LabelCaps.copy(color = OnSurfaceSlate))
                    Text(
                        text = "$latSign${"%.2f".format(animLatG)} g ($latLabel)",
                        style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            val longSign = if (animLongG >= 0) "+" else ""
            val longLabel = if (animLongG < -0.15f) "Coasting Decel" else if (animLongG > 0.15f) "Steady Throttle" else "Cruising"

            Box(
                modifier = Modifier
                    .background(SurfaceContainerHighest.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Column {
                    Text("LONGITUDINAL ACCEL", style = LabelCaps.copy(color = OnSurfaceSlate))
                    Text(
                        text = "$longSign${"%.2f".format(animLongG)} g ($longLabel)",
                        style = LabelTelemetrySm.copy(color = OnSurfaceHighLuminance)
                    )
                }
            }
        }
    }
}

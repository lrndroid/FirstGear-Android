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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstgear.telemetry.ui.theme.CautionAmber
import com.firstgear.telemetry.ui.theme.CautionAmberDim
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.HeadlineSm
import com.firstgear.telemetry.ui.theme.JetBrainsMono
import com.firstgear.telemetry.ui.theme.LabelCaps
import com.firstgear.telemetry.ui.theme.LabelTelemetrySm
import com.firstgear.telemetry.ui.theme.OnSurfaceHighLuminance
import com.firstgear.telemetry.ui.theme.OnSurfaceSlate
import com.firstgear.telemetry.ui.theme.SurfaceContainer
import kotlin.math.roundToInt

@Composable
fun SpeedLimitShield(
    speedLimit: Int,
    currentSpeed: Float,
    roadName: String = "El Camino 35 Limit",
    modifier: Modifier = Modifier
) {
    val excessMph = (currentSpeed - speedLimit).roundToInt()
    val isOverLimit = excessMph > 0
    val excessPct = if (speedLimit > 0) ((excessMph.toFloat() / speedLimit) * 100).roundToInt() else 0

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        // Authentic US Standard Speed Limit Shield
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(SurfaceContainer, RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 8.dp)
        ) {
            // White sign container
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(42.dp)
                    .height(52.dp)
                    .background(Color.White, RoundedCornerShape(4.dp))
                    .border(2.dp, Color.Black, RoundedCornerShape(4.dp))
                    .padding(2.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "SPEED\nLIMIT",
                        color = Color.Black,
                        fontSize = 7.sp,
                        lineHeight = 8.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = speedLimit.toString(),
                        color = Color.Black,
                        fontFamily = JetBrainsMono,
                        fontWeight = FontWeight.Black,
                        fontSize = 17.sp,
                        lineHeight = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AddLocationAlt,
                        contentDescription = "Roads API",
                        tint = ElectricEmeraldDim,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "ROADS API",
                        style = LabelCaps.copy(color = OnSurfaceSlate)
                    )
                }
                Text(
                    text = roadName,
                    style = HeadlineSm.copy(fontSize = 13.sp, color = OnSurfaceHighLuminance)
                )
            }
        }

        // Advisory Delta Indicator Pill
        Column(horizontalAlignment = Alignment.End) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = if (isOverLimit) CautionAmberDim.copy(alpha = 0.15f) else ElectricEmeraldDim.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Speed,
                    contentDescription = null,
                    tint = if (isOverLimit) CautionAmber else ElectricEmeraldDim,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = if (isOverLimit) "+$excessMph MPH (+$excessPct%)" else "LEGAL ENVELOPE",
                    style = LabelTelemetrySm.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isOverLimit) CautionAmber else ElectricEmeraldDim
                    )
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Non-Punitive Advisory",
                style = LabelCaps.copy(color = OnSurfaceSlate)
            )
        }
    }
}

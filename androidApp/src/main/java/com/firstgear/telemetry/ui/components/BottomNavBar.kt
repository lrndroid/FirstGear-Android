package com.firstgear.telemetry.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firstgear.telemetry.ui.theme.BorderSubtle
import com.firstgear.telemetry.ui.theme.ElectricEmeraldDim
import com.firstgear.telemetry.ui.theme.LabelTelemetrySm
import com.firstgear.telemetry.ui.theme.OnSurfaceSlate
import com.firstgear.telemetry.ui.theme.SurfaceContainerLowest

enum class AppTab(val title: String, val icon: ImageVector) {
    DRIVE("Drive", Icons.Default.Speed),
    ANALYSIS("Analysis", Icons.Default.QueryStats),
    TRIP_LOG("Trip Log", Icons.Default.Route),
    SAFETY("Safety", Icons.Default.VerifiedUser)
}

@Composable
fun BottomNavBar(
    selectedTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(SurfaceContainerLowest.copy(alpha = 0.95f))
            .border(1.dp, BorderSubtle)
            .navigationBarsPadding()
            .padding(vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppTab.values().forEach { tab ->
                val isSelected = tab == selectedTab

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = 14.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.title,
                        tint = if (isSelected) ElectricEmeraldDim else OnSurfaceSlate,
                        modifier = Modifier.size(22.dp)
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = tab.title,
                        style = LabelTelemetrySm.copy(
                            fontSize = 11.sp,
                            color = if (isSelected) ElectricEmeraldDim else OnSurfaceSlate
                        )
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .size(width = 18.dp, height = 2.dp)
                                .background(ElectricEmeraldDim, RoundedCornerShape(1.dp))
                        )
                    } else {
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        }
    }
}

package com.firstgear.repository

import com.firstgear.model.TripRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TripLogRepository {
    private val _trips = MutableStateFlow(
        listOf(
            TripRecord(
                id = "TRIP-0901",
                title = "Downtown Commute & Arterial Route",
                routeDescription = "El Camino Real ➔ Embarcadero Rd",
                timestampFormatted = "Today • 8:15 PM",
                distanceMiles = 8.2f,
                durationMinutes = 24,
                overallScore = 92,
                isNightDrive = true,
                harshBrakesCount = 0,
                speedCompliancePct = 95,
                smoothnessScorePct = 94
            ),
            TripRecord(
                id = "TRIP-0899",
                title = "High School to Community Center",
                routeDescription = "Middlefield Rd ➔ San Antonio Rd",
                timestampFormatted = "Yesterday • 4:30 PM",
                distanceMiles = 4.7f,
                durationMinutes = 16,
                overallScore = 86,
                isNightDrive = false,
                harshBrakesCount = 1,
                speedCompliancePct = 88,
                smoothnessScorePct = 89
            ),
            TripRecord(
                id = "TRIP-0895",
                title = "Highway Merge & Curve Navigation",
                routeDescription = "CA-85 South ➔ Saratoga Ave",
                timestampFormatted = "Sep 30 • 6:45 PM",
                distanceMiles = 14.1f,
                durationMinutes = 28,
                overallScore = 90,
                isNightDrive = false,
                harshBrakesCount = 0,
                speedCompliancePct = 92,
                smoothnessScorePct = 93
            ),
            TripRecord(
                id = "TRIP-0890",
                title = "Night Practice & Signal Deceleration",
                routeDescription = "Foothill Expressway ➔ Page Mill Rd",
                timestampFormatted = "Sep 28 • 9:20 PM",
                distanceMiles = 11.5f,
                durationMinutes = 31,
                overallScore = 95,
                isNightDrive = true,
                harshBrakesCount = 0,
                speedCompliancePct = 98,
                smoothnessScorePct = 96
            )
        )
    )

    val trips: StateFlow<List<TripRecord>> = _trips.asStateFlow()

    fun addTrip(trip: TripRecord) {
        _trips.value = listOf(trip) + _trips.value
    }
}

package com.firstgear.model

/**
 * Real-time telemetry snapshot capturing 5Hz IMU, fused GPS, and driving dynamics.
 */
data class TelemetrySnapshot(
    val speedMph: Float = 42f,
    val speedLimitMph: Int = 35,
    val lateralG: Float = 0.12f,
    val longitudinalG: Float = -0.21f,
    val jerk: Float = 0.18f,
    val brakingSmoothnessPct: Int = 88,
    val intersectionRating: Float = 0.94f,
    val harshJerksDetected: Int = 0,
    val distanceMiles: Float = 6.4f,
    val tripDurationSeconds: Long = 1122L, // 18m 42s
    val isCurfewActive: Boolean = true,
    val currentRoad: String = "El Camino Real",
    val roadClass: String = "Class 2: Major Arterial",
    val corridorDescription: String = "towards University Ave • Palo Alto Corridor",
    val waypointPassed: String = "Charleston Rd",
    val waypointCurrent: String = "Oregon Expy",
    val waypointNext: String = "University Ave",
    val etaMinutes: Int = 6,
    val imuFrequencyHz: Float = 5.0f,
    val gpsAccuracyFused: Boolean = true,
    val safetyScore: Int = 88
) {
    val excessMph: Float
        get() = (speedMph - speedLimitMph).coerceAtLeast(0f)

    val excessPercentage: Int
        get() = if (speedLimitMph > 0) ((excessMph / speedLimitMph) * 100).toInt() else 0

    val isOverTolerance: Boolean
        get() = excessMph >= 5f

    val formattedDuration: String
        get() {
            val minutes = tripDurationSeconds / 60
            val seconds = tripDurationSeconds % 60
            return "${minutes}m ${seconds.toString().padStart(2, '0')}s"
        }
}

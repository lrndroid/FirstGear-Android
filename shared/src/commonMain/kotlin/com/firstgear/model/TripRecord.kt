package com.firstgear.model

/**
 * Historical record of a logged drive session towards provisional license requirements.
 */
data class TripRecord(
    val id: String,
    val title: String,
    val routeDescription: String,
    val timestampFormatted: String,
    val distanceMiles: Float,
    val durationMinutes: Int,
    val overallScore: Int,
    val isNightDrive: Boolean,
    val harshBrakesCount: Int,
    val speedCompliancePct: Int,
    val smoothnessScorePct: Int
)

package com.firstgear.model

/**
 * Driver profile, curfew specifications, and parental supervision guidelines.
 */
data class SafetyProfile(
    val teenName: String = "Alex Rivera",
    val licenseStatus: String = "California Provisional Permit (Class C)",
    val totalHoursRequired: Float = 50.0f,
    val totalHoursCompleted: Float = 38.5f,
    val nightHoursRequired: Float = 10.0f,
    val nightHoursCompleted: Float = 8.0f,
    val curfewStart: String = "10:00 PM",
    val curfewEnd: String = "5:00 AM",
    val isCurfewEnforced: Boolean = true,
    val speedToleranceThresholdMph: Int = 7,
    val guardians: List<String> = listOf("Elena Rivera (Mother)", "David Rivera (Father)"),
    val emergencyPhone: String = "(650) 555-0199",
    val roadsideAssistancePhone: String = "1-800-AAA-HELP"
) {
    val totalProgressPct: Int
        get() = ((totalHoursCompleted / totalHoursRequired) * 100).toInt().coerceIn(0, 100)

    val nightProgressPct: Int
        get() = ((nightHoursCompleted / nightHoursRequired) * 100).toInt().coerceIn(0, 100)
}

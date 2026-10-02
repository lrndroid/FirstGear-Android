package com.firstgear.engine

import com.firstgear.model.TelemetrySnapshot
import com.firstgear.model.TripRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * High-performance 5Hz IMU & telemetry simulation engine.
 */
class TelemetrySimulator(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    private val _snapshot = MutableStateFlow(TelemetrySnapshot())
    val snapshot: StateFlow<TelemetrySnapshot> = _snapshot.asStateFlow()

    private var simulationJob: Job? = null

    init {
        startSimulation()
    }

    fun startSimulation() {
        if (simulationJob?.isActive == true) return
        simulationJob = scope.launch {
            while (true) {
                delay(1000L)
                updateTick()
            }
        }
    }

    fun stopSimulation() {
        simulationJob?.cancel()
        simulationJob = null
    }

    private fun updateTick() {
        val current = _snapshot.value
        // Natural jitter around 42 mph (+/- 1 mph)
        val jitter = if (Random.nextBoolean()) 1 else -1
        val newSpeed = (current.speedMph + if (Random.nextFloat() > 0.4f) jitter else 0)
            .coerceIn(38f, 45f)

        // Micro variations in G-forces
        val latJitter = (Random.nextFloat() - 0.5f) * 0.04f
        val longJitter = (Random.nextFloat() - 0.5f) * 0.05f

        val newLatG = ((current.lateralG + latJitter) * 100f).roundToInt() / 100f
        val newLongG = ((current.longitudinalG + longJitter) * 100f).roundToInt() / 100f

        _snapshot.value = current.copy(
            speedMph = newSpeed,
            lateralG = newLatG.coerceIn(-0.35f, 0.35f),
            longitudinalG = newLongG.coerceIn(-0.5f, 0.2f),
            tripDurationSeconds = current.tripDurationSeconds + 1,
            distanceMiles = (current.distanceMiles + (newSpeed / 3600f) * 1f)
        )
    }

    fun accelerate() {
        val current = _snapshot.value
        val boostedSpeed = (current.speedMph + 4f).coerceAtMost(52f)
        _snapshot.value = current.copy(
            speedMph = boostedSpeed,
            longitudinalG = 0.28f,
            jerk = 0.22f
        )
    }

    fun brake() {
        val current = _snapshot.value
        val slowedSpeed = (current.speedMph - 5f).coerceAtLeast(20f)
        _snapshot.value = current.copy(
            speedMph = slowedSpeed,
            longitudinalG = -0.48f,
            jerk = 0.34f
        )
    }

    fun steerHard() {
        val current = _snapshot.value
        _snapshot.value = current.copy(
            lateralG = if (Random.nextBoolean()) 0.38f else -0.38f,
            jerk = 0.31f
        )
    }

    fun induceJerk() {
        val current = _snapshot.value
        _snapshot.value = current.copy(
            harshJerksDetected = current.harshJerksDetected + 1,
            jerk = 0.65f
        )
    }

    fun createCompletedTripRecord(): TripRecord {
        val current = _snapshot.value
        return TripRecord(
            id = "TRIP-" + Random.nextInt(1000, 9999),
            title = "El Camino Corridor & Palo Alto Run",
            routeDescription = "${current.currentRoad} (${current.waypointPassed} ➔ ${current.waypointCurrent})",
            timestampFormatted = "Just Now",
            distanceMiles = ((current.distanceMiles * 10).roundToInt() / 10f),
            durationMinutes = (current.tripDurationSeconds / 60).toInt().coerceAtLeast(1),
            overallScore = current.safetyScore,
            isNightDrive = current.isCurfewActive,
            harshBrakesCount = current.harshJerksDetected,
            speedCompliancePct = if (current.isOverTolerance) 85 else 94,
            smoothnessScorePct = current.brakingSmoothnessPct
        )
    }
}

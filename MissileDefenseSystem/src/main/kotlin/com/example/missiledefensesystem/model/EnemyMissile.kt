package com.example.missiledefensesystem.model

import kotlin.math.roundToInt

class EnemyMissile() {
    private var locX = -1.00
    private var locY = -1.00
    private var velX = -1.00
    private var velY = -1.00
    private val accY = 9.81 / 300.0
    fun roundToNearestTenth(number: Double): Double {
        return (number * 100).roundToInt() / 100.0
    }

    init {
        reset()
    }

    fun getLocX() : Double {
        return locX
    }
    fun getLocY() : Double {
        return locY
    }

    fun tick() {
        locX += velX
        locY += velY
        velY += accY
    }
    fun reset() {
        locX = 1205.00
        locY = roundToNearestTenth(400.00 * Math.random() + 100.00)
        velX = roundToNearestTenth(-10 * Math.random() - 5.0) / 2.0
        val maxVelY = -1 * Math.sqrt(accY * locY * 2/3)
        val minVelY = Math.sqrt(accY * (600.00 - locY) * 2/3)
        velY = roundToNearestTenth((minVelY - maxVelY) * Math.random() + maxVelY) / 2.0
    }
}
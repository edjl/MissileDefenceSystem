package com.example.missiledefensesystem.model

import kotlin.math.roundToInt

class EnemyMissile() {
    private var locX = -1.00
    private var locY = -1.00
    private var velX = -1.00
    private var velY = -1.00
    private val accY = 9.81 / 300.0
    private var state = true
    private var time = 0
    fun roundToNearestHundredth(number: Double): Double {
        return (number * 100).roundToInt() / 100.0
    }

    init {
        locX = 1205.00
        locY = roundToNearestHundredth(200.00 * Math.random() + 200.00)
        velX = roundToNearestHundredth(-5 * Math.random() - 15.0) / 2.0
        val maxVelY = -1 * Math.sqrt(accY * locY * 2/3)
        val minVelY = Math.sqrt(accY * (600.00 - locY) * 2/3)
        velY = roundToNearestHundredth((minVelY - maxVelY) * Math.random() + maxVelY) / 2.0
    }

    fun getLocX() : Double {
        return locX
    }
    fun getLocY() : Double {
        return locY
    }
    fun getState() : Boolean {
        return state
    }

    fun tick() {
        locX += velX
        locY += velY
        velY += accY

        time++

        if (locX < 0 || locY < 0)
            destroy()
    }
    fun destroy() {
        state = false
    }
}
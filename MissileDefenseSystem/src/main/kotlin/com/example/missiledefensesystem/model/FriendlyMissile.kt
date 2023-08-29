package com.example.missiledefensesystem.model

import com.example.missiledefensesystem.viewmodel.Interceptor

class FriendlyMissile(private val source: Interceptor, private val target: EnemyMissile,
                      private val targetX: Double, private val targetY: Double, private val time: Int) {
    private var locX = -1.00
    private var locY = -1.00
    private val vel = 8.40
    private var velX = -1.00
    private var velY = -1.00
    private val accY = 9.81 / 300.0

    init {
        locX = source.getLocX() + source.getWidth()/2
        locY = 600.0 - source.getHeight()

        velX = (targetX - locX) / time
        velY = (targetY - locY) / time
    }

    fun tick() {
        locX += velX
        locY += velY

        val dist = Math.sqrt(Math.pow(locX - target.getLocX(), 2.0) + Math.pow(locY - target.getLocY(), 2.0))

        if (dist <= 10.0) {
            source.hit(this, target)
            target.destroy()
        }
    }

    fun getLocX() : Double {
        return locX
    }
    fun getLocY() : Double {
        return locY
    }
}
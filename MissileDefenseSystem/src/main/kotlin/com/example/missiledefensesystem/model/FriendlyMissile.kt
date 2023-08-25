package com.example.missiledefensesystem.model

class FriendlyMissile(private val source: Interceptor, private val target: EnemyMissile) {
    private var locX = -1.00
    private var locY = -1.00
    private val vel = 8.40
    private var velX = -1.00
    private var velY = -1.00
    private val accY = 9.81 / 300.0

    init {
        locX = source.getLocX() + source.getWidth()/2
        locY = 600.0 - source.getHeight()

        val distX = target.getLocX() - locX
        val distY = locY - target.getLocY()
        val dist = Math.sqrt(Math.pow(distX, 2.0) + Math.pow(distY, 2.0))
        velX = vel * distX / dist
        velY = vel * distY / dist
    }

    fun tick() {
        val distX = target.getLocX() - locX
        val distY = locY - target.getLocY()
        val dist = Math.sqrt(Math.pow(distX, 2.0) + Math.pow(distY, 2.0))
        velX = vel * distX / dist
        velY = -1 * vel * distY / dist

        locX += velX
        locY += velY
        //velY += accY

        if (Math.sqrt(Math.pow(distX, 2.0) + Math.pow(distY, 2.0)) <= 5.0) {
            source.hit(this, target)
            target.reset()
        }
    }

    fun getLocX() : Double {
        return locX
    }
    fun getLocY() : Double {
        return locY
    }
}
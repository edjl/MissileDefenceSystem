package com.example.missiledefensesystem.viewmodel

import com.example.missiledefensesystem.model.EnemyMissile
import com.example.missiledefensesystem.model.EnemyModel
import com.example.missiledefensesystem.model.FriendlyMissile
import kotlin.math.roundToInt

class Interceptor () {
    private val locX = 200.0
    private val width = 100.0
    private val height = 20.0
    private var firstReading: MutableList<EnemyModel> = mutableListOf()

    private val enemyList: MutableList<EnemyMissile> = mutableListOf()
    private val friendlyList: MutableList<FriendlyMissile> = mutableListOf()
    private val hitEnemies: MutableList<EnemyMissile> = mutableListOf()
    private val usedFriends: MutableList<FriendlyMissile> = mutableListOf()

    init {
    }
    fun trackEnemies(missiles: EnemyMissiles) {
        missiles.getMissiles().forEach {
            if (it.getLocX() == 1205.0) {
                firstReading.add(EnemyModel(it.getLocX(), it.getLocY()))
            }
            else if (firstReading.count() > 0 && it.getLocX() >= 1195.0) {
                intercept(it)
            }
        }
    }

    fun findValidTime(accel: Double, velX: Double, velY: Double, disX: Double, disY: Double): Int {
        val targetY = 50.0
        var time = (-1*velY + Math.sqrt(Math.pow(velY, 2.0) + 4 * 0.5 * accel * (disY - targetY))) / (1 * accel)
        if (time > 130.0)
            time = 130.0
        return time.roundToInt()
    }
    fun intercept(missile: EnemyMissile) {
        val accel = 9.81 / 300.0
        var velX = 0.0
        var velY = 0.0
        var fired = false
        var readingUsed = EnemyModel(0.0, 0.0)

        firstReading.forEach {
            if (-3.3 < missile.getLocY() - it.y && missile.getLocY() - it.y < 2.57) {
                readingUsed = it
                velX = missile.getLocX() - it.x
                velY = missile.getLocY() - it.y

                var time = findValidTime(accel, velX, velY, readingUsed.x, 600 -readingUsed.y)
                var targetX = readingUsed.x + velX * time
                var targetY = readingUsed.y + velY * time + 0.5 * accel * Math.pow(1.0*time, 2.0)
                if (targetX < locX + width + 100) {
                    time = time *3/4
                    targetX = readingUsed.x + velX * time
                    targetY = readingUsed.y + velY * time + 0.5 * accel * Math.pow(1.0*time, 2.0)
                }
                friendlyList.add(FriendlyMissile(this, missile, targetX, targetY, time))
                fired = true
            }
        }

        if (fired)
            firstReading.remove(readingUsed)

    }
    fun hit(fmissile: FriendlyMissile, emissile: EnemyMissile) {
        hitEnemies.add(emissile)
        usedFriends.add(fmissile)
    }
    fun clean() {
        hitEnemies.forEach {
            enemyList.remove(it)
        }
        usedFriends.forEach {
            friendlyList.remove(it)
        }
        hitEnemies.clear()
        usedFriends.clear()
    }

    fun getLocX() : Double{
        return locX
    }
    fun getWidth() : Double{
        return width
    }
    fun getHeight() : Double{
        return height
    }
    fun getFriendlyMissiles() : MutableList<FriendlyMissile> {
        return friendlyList
    }
}
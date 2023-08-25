package com.example.missiledefensesystem.viewmodel

import com.example.missiledefensesystem.model.EnemyMissile

class EnemyMissiles {
    private val missiles: MutableList<EnemyMissile> = mutableListOf()
    init {
    }

    fun addMissiles(num : Int) {
        for (i in 0 until num)
            missiles.add(EnemyMissile())
    }
    fun tick() {
        missiles.forEach { missile ->
            missile.tick()
            if (missile.getLocY() <= 0)
                missiles.remove(missile)
        }
    }
    fun getMissiles() : MutableList<EnemyMissile> {
        return missiles
    }
}
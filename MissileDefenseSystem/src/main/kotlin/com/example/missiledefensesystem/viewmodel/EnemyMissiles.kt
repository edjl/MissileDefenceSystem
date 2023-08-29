package com.example.missiledefensesystem.viewmodel

import com.example.missiledefensesystem.model.EnemyMissile

class EnemyMissiles(val count : Int) {
    private val missiles: MutableList<EnemyMissile> = mutableListOf()
    init {
        if (count > 0)
            missiles.add(EnemyMissile())
    }

    fun removeInvalidMissiles() {
        var toRemove: MutableList<EnemyMissile> = mutableListOf()
        missiles.forEach { missile ->
            if (!missile.getState()) {
                toRemove.add(missile)
            }
        }
        toRemove.forEach {
            missiles.remove(it)
        }
    }
    fun tick() {
        missiles.forEach { missile ->
            missile.tick()
        }
        if (missiles.count() < count) {
            missiles.add(EnemyMissile())
        }
    }
    fun getMissiles() : MutableList<EnemyMissile> {
        return missiles
    }
}

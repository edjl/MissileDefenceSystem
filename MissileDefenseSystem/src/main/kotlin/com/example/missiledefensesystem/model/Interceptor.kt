package com.example.missiledefensesystem.model

class Interceptor {
    private val locX = 200.0
    private val width = 100.0
    private val height = 20.0
    private val enemyList: MutableList<EnemyMissile> = mutableListOf()
    private val friendlyList: MutableList<FriendlyMissile> = mutableListOf()
    private val hitEnemies: MutableList<EnemyMissile> = mutableListOf()
    private val usedFriends: MutableList<FriendlyMissile> = mutableListOf()

    init {
    }
    fun trackEnemy(missile: EnemyMissile) {
        if (!enemyList.contains(missile)) {
            enemyList.add(missile)
            intercept(missile)
        }
    }

    fun intercept(missile: EnemyMissile) {
        friendlyList.add(FriendlyMissile(this, missile))
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
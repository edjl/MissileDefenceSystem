package com.example.missiledefensesystem.view

import com.example.missiledefensesystem.model.Interceptor
import com.example.missiledefensesystem.viewmodel.EnemyMissiles
import javafx.animation.AnimationTimer
import javafx.scene.canvas.Canvas
import javafx.scene.layout.Pane
import javafx.scene.paint.Color

class CanvasView(val interceptor: Interceptor) : Pane() {
    private var enemyMissile = EnemyMissiles()
    private val canvas = Canvas(1200.0, 600.0)

    init {
        enemyMissile.addMissiles(7)


        val animationTimer = object : AnimationTimer() {
            override fun handle(now: Long) {
                canvas.graphicsContext2D.clearRect(0.0, 0.0, canvas.width, canvas.height)
                canvas.graphicsContext2D.fill = Color.RED
                enemyMissile.getMissiles().forEach {
                    canvas.graphicsContext2D.fillOval(it.getLocX(), it.getLocY(), 10.0, 10.0)
                    it.tick()
                    if (it.getLocY() >= canvas.height || it.getLocX() <= 0) {
                        it.reset()
                    }
                    interceptor.trackEnemy(it)
                }
                canvas.graphicsContext2D.fill = Color.GREENYELLOW
                interceptor.getFriendlyMissiles().forEach {
                    canvas.graphicsContext2D.fillOval(it.getLocX(), it.getLocY(), 10.0, 10.0)
                    it.tick()
                }
                interceptor.clean()
            }
        }
        animationTimer.start()

        children.add(canvas)
    }
}
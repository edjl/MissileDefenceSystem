package com.example.missiledefensesystem

import com.example.missiledefensesystem.viewmodel.Interceptor
import com.example.missiledefensesystem.view.CanvasView
import com.example.missiledefensesystem.viewmodel.EnemyMissiles
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.stage.Stage

class Application : Application() {
    override fun start(stage: Stage) {
        stage.apply {
            title = "Missile Defense System"

            val background = Rectangle(0.0, 0.0, 1200.0, 600.0)
            background.fill = Color.BLACK

            var enemyMissiles = EnemyMissiles(11)
            val interceptor = Interceptor()
            val interceptorView = Rectangle(interceptor.getLocX(), background.height - interceptor.getHeight(), interceptor.getWidth(), interceptor.getHeight())
            interceptorView.fill = Color.GREENYELLOW

            val canvasPane = CanvasView(interceptor, enemyMissiles)
            scene = Scene(Group(background, interceptorView, canvasPane), 1200.0, 600.0)

        }
        stage.show()
    }
}


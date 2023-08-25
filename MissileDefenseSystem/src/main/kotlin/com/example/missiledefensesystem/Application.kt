package com.example.missiledefensesystem

import com.example.missiledefensesystem.model.Interceptor
import com.example.missiledefensesystem.view.CanvasView
import javafx.animation.Interpolator
import javafx.animation.Transition
import javafx.animation.TranslateTransition
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.Stage
import javafx.util.Duration

class Application : Application() {
    override fun start(stage: Stage) {
        stage.apply {
            title = "Missile Defense System"

            val background = Rectangle(0.0, 0.0, 1200.0, 600.0)
            background.fill = Color.BLACK
            val interceptor = Interceptor()
            val interceptorView = Rectangle(interceptor.getLocX(), background.height - interceptor.getHeight(), interceptor.getWidth(), interceptor.getHeight())
            interceptorView.fill = Color.GREENYELLOW

            val canvasPane = CanvasView(interceptor)
            scene = Scene(Group(background, interceptorView, canvasPane), 1200.0, 600.0)

        }
        stage.show()
    }
}


package br.svcdev.imageviewer

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    private lateinit var cicerone: Cicerone<Router>

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        cicerone = Cicerone.create()
    }

    fun getRouter() = cicerone.router
    fun getNavigatorHolder() = cicerone.navigatorHolder

}

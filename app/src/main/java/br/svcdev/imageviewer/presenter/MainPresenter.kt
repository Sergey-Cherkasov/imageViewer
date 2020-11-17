package br.svcdev.imageviewer.presenter

import br.svcdev.imageviewer.App
import br.svcdev.imageviewer.view.interfaces.IMainView
import br.svcdev.imageviewer.view.ui.Screens
import moxy.MvpPresenter

class MainPresenter : MvpPresenter<IMainView>() {

    private val router = App.instance.getRouter()

    fun onBackPressed() {
        router.exit()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.ImageScreen())
    }
}
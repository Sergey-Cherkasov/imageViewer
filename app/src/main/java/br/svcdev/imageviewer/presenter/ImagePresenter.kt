package br.svcdev.imageviewer.presenter

import br.svcdev.imageviewer.App
import br.svcdev.imageviewer.model.repository.ImageRepository
import br.svcdev.imageviewer.view.interfaces.IImageView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter


class ImagePresenter : MvpPresenter<IImageView>(), IImagePresenter<IImageView> {

    private val router = App.instance.getRouter()
    private val imageRepo = ImageRepository()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override var buttonClickListener: ((IImageView) -> Unit)? = {
        imageRepo.single().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { bitmap -> it.setImage(bitmap) },
                { println(it.message) })
    }
}

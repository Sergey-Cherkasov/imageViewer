package br.svcdev.imageviewer.presenter

import br.svcdev.imageviewer.view.interfaces.IImageView

interface IImagePresenter<V : IImageView> {
    var buttonClickListener: ((V) -> Unit)?
}
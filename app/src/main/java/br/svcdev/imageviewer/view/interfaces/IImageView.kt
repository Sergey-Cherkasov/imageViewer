package br.svcdev.imageviewer.view.interfaces

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IImageView : MvpView {
    fun init()
    fun setImage(bitmap: Bitmap)
}
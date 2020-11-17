package br.svcdev.imageviewer.view.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.svcdev.imageviewer.databinding.FragmentImageBinding
import br.svcdev.imageviewer.presenter.ImagePresenter
import br.svcdev.imageviewer.view.interfaces.IBackButtonListener
import br.svcdev.imageviewer.view.interfaces.IImageView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ImageFragment : MvpAppCompatFragment(), IImageView, IBackButtonListener {

    private val presenter by moxyPresenter { ImagePresenter() }
    private lateinit var binding: FragmentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        binding.btnLoadImage.setOnClickListener { presenter.buttonClickListener?.invoke(this) }
    }

    override fun setImage(bitmap: Bitmap) {
        binding.ivImage.setImageBitmap(bitmap)
    }

    override fun onBackPressed(): Boolean {
        return presenter.backPressed()
    }
}

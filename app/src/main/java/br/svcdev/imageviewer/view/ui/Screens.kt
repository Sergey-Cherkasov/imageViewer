package br.svcdev.imageviewer.view.ui

import androidx.fragment.app.Fragment
import br.svcdev.imageviewer.view.ui.fragment.ImageFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class ImageScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ImageFragment()
        }
    }
}
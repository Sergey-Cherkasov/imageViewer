package br.svcdev.imageviewer.view.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.svcdev.imageviewer.App
import br.svcdev.imageviewer.R
import br.svcdev.imageviewer.presenter.MainPresenter
import br.svcdev.imageviewer.view.interfaces.IBackButtonListener
import br.svcdev.imageviewer.view.interfaces.IMainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), IMainView {

    private val presenter by moxyPresenter { MainPresenter() }
    private val router = App.instance.getRouter()
    private val navigatorHolder = App.instance.getNavigatorHolder()
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    companion object {
        private final val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE_WRITE_EXTERNAL_STORAGE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray) {

        when(requestCode) {
            REQUEST_CODE_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    return
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
                    return
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onStop() {
        super.onStop()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is IBackButtonListener && fragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }
}
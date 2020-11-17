package br.svcdev.imageviewer.model.repository

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.core.Single
import java.io.ByteArrayOutputStream
import java.io.File

class ImageRepository {

    private val pathFile = Environment.getExternalStorageDirectory().path + "/Download/tmpImage.png"

    companion object {
        private const val STRING_RESOURCE = "https://source.unsplash.com/random"
    }

    private fun getBitmapImage(): Bitmap {
        var bitmap = loadBitmap(STRING_RESOURCE)
        savePNGImage(bitmap)
        val filePNG = File(pathFile)
        Log.i("IMAGE", filePNG.absolutePath)
        bitmap = loadBitmap(filePNG)
        return bitmap
    }

    private fun loadBitmap(path: String): Bitmap {
        return Picasso.get().load(path).get()
    }

    private fun loadBitmap(path: File): Bitmap {
        return Picasso.get().load(path).get()
    }

    private fun savePNGImage(bitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val file = File(pathFile).outputStream()
        baos.writeTo(file)
        file.close()
        baos.close()
    }

    fun single(): Single<Bitmap> = Single.fromCallable {
        return@fromCallable getBitmapImage()
    }
}

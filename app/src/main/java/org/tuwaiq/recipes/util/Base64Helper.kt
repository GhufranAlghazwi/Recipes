package org.tuwaiq.recipes.util

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.FileUtils
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.provider.MediaStore
import android.text.BoringLayout
import io.grpc.internal.SharedResourceHolder
import org.tuwaiq.recipes.R
import java.util.regex.Pattern


class Base64Helper {
    companion object {

        fun encodeImage(uri: Uri): String {
            //encode
            val bm = BitmapFactory.decodeFile(uri.path)
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos) // bm is the bitmap object
            val b = baos.toByteArray()
            var encodedImage = Base64.encodeToString(b, Base64.DEFAULT)
            return encodedImage
        }

        fun decodeImage(context: Context, encodedImage: String): Bitmap {
            //decode
            var bytes = Base64.decode(encodedImage, Base64.DEFAULT)
            var decodedImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

            val baos = ByteArrayOutputStream()
            decodedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos) // bm is the bitmap object
//            val path = MediaStore.Images.Media.insertImage(
//                context.contentResolver,
//                decodedImage,
//                "Title",
//                null
//            )
//            return Uri.parse(path)
            return decodedImage


        }

        fun isBase64(str: String): Boolean {
            var newStr = str.replace("\n","")
            var base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$"
            var pattern = Pattern.compile(base64Pattern)
            var bool = pattern.matcher(newStr).matches()
            return bool
        }


    }
}
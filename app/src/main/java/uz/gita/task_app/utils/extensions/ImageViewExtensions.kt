package uz.gita.task_app.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


// Created by Jamshid Isoqov an 8/10/2022


@SuppressLint("UseCompatLoadingForDrawables")
fun drawableStringToDrawable(ctx: Context, drawableString: String) =
    ctx.resources.getIdentifier(drawableString, "drawable", ctx.packageName)


fun getDrawable(ctx: Context, drawableString: String) =
    ContextCompat.getDrawable(ctx, drawableStringToDrawable(ctx, drawableString))


fun ImageView.setDrawableImage(@DrawableRes resource: Int, applyCircle: Boolean = false) {
    val glide = Glide.with(this).load(resource).diskCacheStrategy(DiskCacheStrategy.NONE)
    if (applyCircle) {
        glide.apply(RequestOptions.circleCropTransform()).into(this)
    } else {
        glide.into(this)
    }
}

fun ImageView.setLocalImage(uri: Uri, applyCircle: Boolean = false) {
    val glide = Glide.with(this).load(uri).diskCacheStrategy(DiskCacheStrategy.NONE)
    if (applyCircle) {
        glide.apply(RequestOptions.circleCropTransform()).into(this)
    } else {
        glide.into(this)
    }
}

package uz.gita.task_app.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat


// Created by Jamshid Isoqov an 8/10/2022


@SuppressLint("UseCompatLoadingForDrawables")
fun drawableStringToDrawable(ctx: Context, drawableString: String) =
    ctx.resources.getIdentifier(drawableString, "drawable", ctx.packageName)


fun getDrawable(ctx: Context, drawableString: String) =
    ContextCompat.getDrawable(ctx, drawableStringToDrawable(ctx, drawableString))

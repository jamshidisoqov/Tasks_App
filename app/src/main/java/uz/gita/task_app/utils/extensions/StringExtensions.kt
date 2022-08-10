package uz.gita.task_app.utils.extensions


import java.text.SimpleDateFormat
import java.util.*

// Created by Jamshid Isoqov an 8/9/2022

fun getCurrentDate(): String {
    val c = Calendar.getInstance().time
    return SimpleDateFormat("MMMM dd,yyyy", Locale.getDefault()).format(c).uppercase()
}

fun getCurrentTime():String{
    val c = Calendar.getInstance().time
    return SimpleDateFormat("HH:mm",Locale.getDefault()).format(c)
}
package com.nutricare.util
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    context?.showToast(message, duration)
}

fun String.toDateFromString(inputFormat: String = "yyyy-MM-dd"): Date? {
    return try {
        SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.formatToString(outputFormat: String = "yyyy-MM-dd"): String {
    return SimpleDateFormat(outputFormat, Locale.getDefault()).format(this)
}

fun Long.toDateString(format: String = "yyyy-MM-dd"): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(Date(this))
}

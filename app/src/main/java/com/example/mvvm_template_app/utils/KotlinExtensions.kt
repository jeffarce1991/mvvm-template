package com.example.mvvm_template_app.utils

import android.app.Activity
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mvvm_template_app.R
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun String.Companion.substringWithDots(s: String, maxLength: Int) : String {
    return when {
        s.length >= maxLength -> {
            String.format("${s.substring(0, maxLength)}…")
        }
        else -> {
            s
        }
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun String.toTitleCase(): String = this.capitalize(Locale.ROOT)

fun Activity.invokeSimpleDialog(title: String,
                                positiveButtonText: String,
                                message: String,
                                onApprove: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { dialog, which ->
            onApprove.invoke()
        }
        .setNegativeButton(getString(R.string.cancel)) { dialog, which ->
            dialog.dismiss()
        }
        .show()
}

fun Activity.invokeSimpleDialog(title: String,
                                positiveButtonText: String,
                                negativeButtonText: String,
                                message: String,
                                onApprove: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { dialog, which ->
            onApprove.invoke()
        }
        .setNegativeButton(negativeButtonText) { dialog, which ->
            dialog.dismiss()
        }
        .show()
}

fun Activity.invokeSimpleDialog(title: String,
                                message: String) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .show()
}

fun Activity.invokeSimpleDialog(title: String,
                                positiveButtonText: String,
                                message: String) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonText) { dialog, which ->
            dialog.dismiss()
        }
        .show()
}

fun Activity.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.invertColor() {
    val invertMX = floatArrayOf(
        -1.0f, 0.0f, 0.0f, 0.0f, 255.0f,
        0.0f, -1.0f, 0.0f, 0.0f, 255.0f,
        0.0f, 0.0f, -1.0f, 0.0f, 255.0f,
        0.0f, 0.0f, 0.0f, 1.0f, 0.0f
    )

    val invertCM = ColorMatrix(invertMX)

    val filter = ColorMatrixColorFilter(invertCM)
    this.colorFilter = filter
}

fun formatNumberToAcronym(n : Int): String {
    return when {
        abs(n / 1000000) > 1 -> {
            (n / 1000000).toString() + "M"
        }
        abs(n / 1000) > 1 -> {
            (n / 1000).toString() + "K"
        }
        else -> {
            n.toString()
        }
    }
}
fun Any?.isNull() = this == null

fun Any?.isNotNull() = !isNull()

fun String.toPriceAmount(): String {
    val dec = DecimalFormat("###,###,###.00")
    return dec.format(this.toDouble())
}

fun Double.toPriceAmount(): String {
    val dec = DecimalFormat("###,###,###.00")
    return dec.format(this)
}

/*
* USAGE
*
* val currentTime = System.currentTimeMillis()
* println(currentTime.getTimeStamp())
* println(currentTime.getYearMonthDay())
* println("2020-09-20".getDateUnixTime())
*
* */

private const val TIME_STAMP_FORMAT = "EEEE, MMMM d, yyyy - hh:mm:ss a"
private const val DATE_FORMAT = "yyyy-MM-dd"

fun Long.getTimeStamp(): String {
    val date = Date(this)
    val simpleDateFormat = SimpleDateFormat(TIME_STAMP_FORMAT, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()
    return simpleDateFormat.format(date)
}

fun Long.getYearMonthDay(): String {
    val date = Date(this)
    val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()
    return simpleDateFormat.format(date)
}

@Throws(ParseException::class)
fun String.getDateUnixTime(): Long {
    try {
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault()
        return simpleDateFormat.parse(this)!!.time
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    throw ParseException("Please Enter a valid date", 0)
}

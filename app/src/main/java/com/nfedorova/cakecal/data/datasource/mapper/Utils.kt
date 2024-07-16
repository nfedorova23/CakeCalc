package com.nfedorova.cakecal.data.datasource.mapper

import android.widget.TextView
import java.security.MessageDigest

fun TextView.sha256(): String {
    val str = text.toString()
    val bytes = str.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.joinToString("") {
        "%02x".format(it)
    }
}
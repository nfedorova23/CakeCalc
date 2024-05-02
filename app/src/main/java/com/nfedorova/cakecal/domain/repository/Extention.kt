package com.nfedorova.cakecal.domain.repository

import java.security.MessageDigest

fun sha256(input: String): String {
    val bytes = input.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.joinToString("") {
        "%02x".format(it)
    }
}



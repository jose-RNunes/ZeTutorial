package com.example.zetutorial.utils

import java.math.BigInteger
import java.security.MessageDigest

fun String.toMd5(): String {
    val md = MessageDigest.getInstance(ALGORITHM_TYPE)
    return BigInteger(SIGNUM, md.digest(toByteArray())).toString(RADIX).padStart(PAD_START, PAD_CHAR)
}

private const val ALGORITHM_TYPE = "MD5"
private const val RADIX = 16
private const val PAD_START = 32
private const val PAD_CHAR = '0'
private const val SIGNUM = 1
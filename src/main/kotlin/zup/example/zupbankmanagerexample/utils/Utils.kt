package zup.example.zupbankmanagerexample.utils

import java.util.*

fun String.onlyDigits() = this.filter { it.isDigit() }
fun generateUUID() = UUID.randomUUID().toString()
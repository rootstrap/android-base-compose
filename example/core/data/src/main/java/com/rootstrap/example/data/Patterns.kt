package com.rootstrap.example.data

object PatternsUtil {

    const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=!]).{4,}\$"
    const val NAME_REGEX = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}\$"
}

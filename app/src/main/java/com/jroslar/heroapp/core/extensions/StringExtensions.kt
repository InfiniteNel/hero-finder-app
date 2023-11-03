package com.jroslar.heroapp.core.extensions

import com.jroslar.heroapp.core.Constant.WITHOUT_RESULT


fun String.notNullResult(): String {
    return if (this == "null" || this == "-") WITHOUT_RESULT else this
}
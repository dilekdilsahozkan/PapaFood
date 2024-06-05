package com.dilsahozkan.papafood.common

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

fun String.stripHtml(): String {
    return this.fromHtml().toString()
}
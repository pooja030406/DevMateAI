package com.pooja.devmateai.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

fun getFileName(
    context: Context,
    uri: Uri
): String {

    var fileName = "Unknown Resume"

    val cursor = context.contentResolver.query(
        uri,
        null,
        null,
        null,
        null
    )

    cursor?.use {

        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)

        if (it.moveToFirst() && nameIndex != -1) {
            fileName = it.getString(nameIndex)
        }

    }

    return fileName
}
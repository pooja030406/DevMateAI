package com.pooja.devmateai.utils

import android.content.Context
import android.net.Uri
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper

object PdfUtils {

    fun extractText(context: Context, uri: Uri): String {

        PDFBoxResourceLoader.init(context)

        val inputStream = context.contentResolver.openInputStream(uri)
        val document = PDDocument.load(inputStream)

        val text = PDFTextStripper().getText(document)

        document.close()
        inputStream?.close()

        return text
    }
}
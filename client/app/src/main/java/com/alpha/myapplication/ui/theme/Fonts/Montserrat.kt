package com.alpha.myapplication.ui.theme.Fonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.alpha.myapplication.R

object Montserrat {
    private val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    private val fontName = GoogleFont("Montserrat")

    fun getFont(): FontFamily {
        return FontFamily(
            Font(googleFont = fontName, fontProvider = provider)
        )
    }
}
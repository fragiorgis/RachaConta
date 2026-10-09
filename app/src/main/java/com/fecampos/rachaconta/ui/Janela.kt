package com.fecampos.rachaconta.ui

import android.content.res.Configuration
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

internal fun AppCompatActivity.prepararJanela(root: View) {
    enableEdgeToEdge()
    val escuro = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    WindowInsetsControllerCompat(window, root).apply {
        isAppearanceLightStatusBars = !escuro
        isAppearanceLightNavigationBars = !escuro
    }
    ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
        val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
        val teclado = insets.getInsets(WindowInsetsCompat.Type.ime())
        view.setPadding(barras.left, barras.top, barras.right, maxOf(barras.bottom, teclado.bottom))
        insets
    }
    ViewCompat.requestApplyInsets(root)
}

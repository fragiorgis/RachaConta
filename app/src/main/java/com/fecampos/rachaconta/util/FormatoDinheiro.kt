package com.fecampos.rachaconta.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object FormatoDinheiro {
    private val formatoBrasileiro = Regex("(?:[0-9]{1,5}|[0-9]{1,3}(?:\\.[0-9]{3})+)(?:,[0-9]{1,2})?")
    private val decimalComPonto = Regex("[0-9]{1,5}\\.[0-9]{1,2}")

    fun interpretar(texto: String): Long? {
        val entrada = texto.trim()
        val normalizado = if (decimalComPonto.matches(entrada)) entrada.replace('.', ',') else entrada
        if (!formatoBrasileiro.matches(normalizado)) return null
        val partes = normalizado.replace(".", "").split(',')
        // Limitar antes da multiplicação também impede overflow em entradas agrupadas enormes.
        val reais = partes[0].toLongOrNull()?.takeIf { it <= 99_999 } ?: return null
        val centavos = partes.getOrNull(1)?.padEnd(2, '0')?.toLongOrNull() ?: 0L
        return (reais * 100 + centavos).takeIf { it in 1..9_999_999 }
    }

    fun formatar(centavos: Long): String {
        val formato = DecimalFormat("#,##0.00", DecimalFormatSymbols(Locale.forLanguageTag("pt-BR")))
        return "R$ " + formato.format(BigDecimal.valueOf(centavos, 2))
    }

    fun paraEntrada(centavos: Long): String = BigDecimal.valueOf(centavos, 2).toPlainString().replace('.', ',')
}

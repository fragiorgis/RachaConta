package com.fragiorgis.rachaconta.domain

import com.fragiorgis.rachaconta.model.Conta
import com.fragiorgis.rachaconta.util.FormatoDinheiro

data class ValidacaoConta(val conta: Conta?, val erroValor: Boolean, val erroNome: Boolean)

object ValidadorConta {
    fun validar(valor: String, pessoas: Int, nome: String): ValidacaoConta {
        val centavos = FormatoDinheiro.interpretar(valor)
        val encontro = nome.trim().takeIf { it.isNotEmpty() }
        val erroNome = encontro != null && encontro.length > 40
        val conta = if (centavos != null && !erroNome && pessoas in 1..50)
            Conta(centavos, pessoas, encontro) else null
        return ValidacaoConta(conta, centavos == null, erroNome)
    }
}

package com.fecampos.rachaconta.domain

import com.fecampos.rachaconta.model.Conta
import com.fecampos.rachaconta.model.ParcelaPessoa
import com.fecampos.rachaconta.model.ResultadoRateio

object CalculadoraRateio {
    fun dividir(conta: Conta): ResultadoRateio {
        val base = conta.valorCentavos / conta.quantidadePessoas
        val resto = (conta.valorCentavos % conta.quantidadePessoas).toInt()
        // Os centavos extras ficam no final, com atribuição estável por pessoa.
        val parcelas = List(conta.quantidadePessoas) { indice ->
            val adicional = if (indice >= conta.quantidadePessoas - resto) 1L else 0L
            ParcelaPessoa(id = indice + 1, valorCentavos = base + adicional)
        }
        return ResultadoRateio(conta.valorCentavos, parcelas)
    }
}

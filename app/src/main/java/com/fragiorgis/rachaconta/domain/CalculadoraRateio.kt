package com.fragiorgis.rachaconta.domain

import com.fragiorgis.rachaconta.model.Conta
import com.fragiorgis.rachaconta.model.ParcelaPessoa
import com.fragiorgis.rachaconta.model.ResultadoRateio

object CalculadoraRateio {
    fun dividir(conta: Conta): ResultadoRateio {
        val base = conta.valorCentavos / conta.quantidadePessoas
        val resto = (conta.valorCentavos % conta.quantidadePessoas).toInt()
        val parcelas = List(conta.quantidadePessoas) { indice ->
            val adicional = if (indice >= conta.quantidadePessoas - resto) 1L else 0L
            ParcelaPessoa(id = indice + 1, valorCentavos = base + adicional)
        }
        return ResultadoRateio(conta.valorCentavos, parcelas)
    }
}

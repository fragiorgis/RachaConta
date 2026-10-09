package com.fecampos.rachaconta.model

data class Conta(
    val valorCentavos: Long,
    val quantidadePessoas: Int,
    val nomeEncontro: String? = null
) {
    init {
        require(valorCentavos in 1..9_999_999)
        require(quantidadePessoas in 1..50)
        require(nomeEncontro == null || (nomeEncontro.isNotBlank() && nomeEncontro.length <= 40))
    }
}

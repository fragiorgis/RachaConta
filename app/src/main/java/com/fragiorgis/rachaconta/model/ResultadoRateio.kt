package com.fragiorgis.rachaconta.model

data class ResultadoRateio(val totalCentavos: Long, val parcelas: List<ParcelaPessoa>) {
    val quantidadePagos: Int get() = parcelas.count { it.pago }

    fun marcarPagamento(id: Int, pago: Boolean): ResultadoRateio =
        copy(parcelas = parcelas.map { if (it.id == id) it.copy(pago = pago) else it })
}

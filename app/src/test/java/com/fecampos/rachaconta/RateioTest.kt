package com.fecampos.rachaconta

import com.fecampos.rachaconta.domain.CalculadoraRateio
import com.fecampos.rachaconta.domain.ValidadorConta
import com.fecampos.rachaconta.model.Conta
import com.fecampos.rachaconta.util.FormatoDinheiro
import org.junit.Assert.*
import org.junit.Test

class RateioTest {
    @Test fun exemploDistribuiCentavosSemPerderDinheiro() {
        val r = CalculadoraRateio.dividir(Conta(10000, 3))
        assertEquals(listOf(3333L, 3333L, 3334L), r.parcelas.map { it.valorCentavos })
        assertTrue(r.parcelas.none { it.pago })
    }
    @Test fun somaEDiferencaPermanecemCorretasNosLimites() {
        for (pessoas in 1..50) for (total in listOf(1L, 2L, 5L, 99L, 10000L, 9999999L)) {
            val p = CalculadoraRateio.dividir(Conta(total, pessoas)).parcelas
            assertEquals(total, p.sumOf { it.valorCentavos })
            assertEquals(pessoas, p.size)
            assertEquals(pessoas, p.map { it.id }.distinct().size)
            assertTrue(p.maxOf { it.valorCentavos } - p.minOf { it.valorCentavos } <= 1)
        }
    }
    @Test fun confirmacaoEReversivelEImutavel() {
        val original = CalculadoraRateio.dividir(Conta(10000, 3))
        val marcado = original.marcarPagamento(2, true)
        assertEquals(0, original.quantidadePagos)
        assertEquals(1, marcado.quantidadePagos)
        assertEquals(listOf(false, true, false), marcado.parcelas.map { it.pago })
        assertEquals(0, marcado.marcarPagamento(2, false).quantidadePagos)
        assertEquals(original.parcelas.map { it.valorCentavos }, marcado.parcelas.map { it.valorCentavos })
    }
    @Test fun pessoaUnicaRecebeTotal() {
        assertEquals(9999999L, CalculadoraRateio.dividir(Conta(9999999, 1)).parcelas.single().valorCentavos)
    }
    @Test fun valorPequenoPodeGerarParcelaZero() {
        assertEquals(listOf(0L, 0L, 1L), CalculadoraRateio.dividir(Conta(1, 3)).parcelas.map { it.valorCentavos })
    }
    @Test fun interpretaMoedaSemPontoFlutuante() {
        mapOf("100,00" to 10000L, "100.00" to 10000L, "1.234,56" to 123456L,
            "1.000" to 100000L, "0,01" to 1L, "99.999,99" to 9999999L,
            "12,5" to 1250L, " 12 " to 1200L).forEach { (entrada, esperado) ->
            assertEquals(esperado, FormatoDinheiro.interpretar(entrada))
        }
    }
    @Test fun rejeitaValoresInvalidos() {
        listOf("", "0", "0,00", "-1", "1e3", "1,234", "100000,00", "100.000,00", "abc", "1.2.3", "1,", "1 000").forEach {
            assertNull(it, FormatoDinheiro.interpretar(it))
        }
    }
    @Test fun formataEmReaisComCentavos() {
        assertEquals("R$ 33,33", FormatoDinheiro.formatar(3333))
        assertEquals("R$ 99.999,99", FormatoDinheiro.formatar(9999999))
        assertEquals("100,00", FormatoDinheiro.paraEntrada(10000))
    }
    @Test fun nomeAusenteENormalizado() {
        assertNull(ValidadorConta.validar("100", 3, "  ").conta?.nomeEncontro)
        assertEquals("Pizza", ValidadorConta.validar("100", 3, " Pizza ").conta?.nomeEncontro)
    }
    @Test fun validaLimitesDeNomeEPessoas() {
        assertTrue(ValidadorConta.validar("100", 3, "x".repeat(41)).erroNome)
        assertNotNull(ValidadorConta.validar("100", 3, "x".repeat(40)).conta)
        assertNull(ValidadorConta.validar("100", 0, "").conta)
        assertNull(ValidadorConta.validar("100", 51, "").conta)
    }
}

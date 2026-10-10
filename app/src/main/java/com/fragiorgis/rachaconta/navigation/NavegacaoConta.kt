package com.fragiorgis.rachaconta.navigation

import android.content.Context
import android.content.Intent
import com.fragiorgis.rachaconta.model.Conta
import com.fragiorgis.rachaconta.ui.ResultadoActivity

object NavegacaoConta {
    private const val VALOR = "conta.valor"
    private const val PESSOAS = "conta.pessoas"
    private const val NOME = "conta.nome"

    fun resultado(context: Context, conta: Conta): Intent =
        Intent(context, ResultadoActivity::class.java).apply {
            putExtra(VALOR, conta.valorCentavos)
            putExtra(PESSOAS, conta.quantidadePessoas)
            conta.nomeEncontro?.let { putExtra(NOME, it) }
        }

    fun ler(intent: Intent): Conta? {
        val valor = intent.getLongExtra(VALOR, 0)
        val pessoas = intent.getIntExtra(PESSOAS, 0)
        val nome = intent.getStringExtra(NOME)
        return runCatching { Conta(valor, pessoas, nome) }.getOrNull()
    }
}

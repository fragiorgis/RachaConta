package com.fragiorgis.rachaconta.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fragiorgis.rachaconta.R
import com.fragiorgis.rachaconta.databinding.ActivityResultadoBinding
import com.fragiorgis.rachaconta.domain.CalculadoraRateio
import com.fragiorgis.rachaconta.model.ResultadoRateio
import com.fragiorgis.rachaconta.navigation.NavegacaoConta
import com.fragiorgis.rachaconta.util.FormatoDinheiro

class ResultadoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultadoBinding
    private lateinit var resultado: ResultadoRateio
    private lateinit var adapter: ParcelaPessoaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val conta = NavegacaoConta.ler(intent) ?: run { finish(); return }
        binding = ActivityResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepararJanela(binding.root)
        resultado = CalculadoraRateio.dividir(conta)
        val pagos = savedInstanceState?.getIntArray("pagos")?.toSet().orEmpty()
        resultado = resultado.copy(parcelas = resultado.parcelas.map { it.copy(pago = it.id in pagos) })
        binding.meetingTitle.text = conta.nomeEncontro ?: getString(R.string.default_meeting)
        binding.totalValue.text = FormatoDinheiro.formatar(resultado.totalCentavos)
        adapter = ParcelaPessoaAdapter { id, pago ->
            resultado = resultado.marcarPagamento(id, pago)
            updateUI()
        }
        binding.peopleList.layoutManager = LinearLayoutManager(this)
        binding.peopleList.adapter = adapter
        binding.peopleList.itemAnimator = null
        binding.backButton.setOnClickListener { finish() }
        binding.editButton.setOnClickListener { finish() }
        updateUI()
    }

    private fun updateUI() {
        adapter.apresentar(resultado.parcelas)
        binding.paidCount.text = resources.getQuantityString(R.plurals.paid_progress, resultado.parcelas.size, resultado.quantidadePagos, resultado.parcelas.size)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (::resultado.isInitialized) outState.putIntArray("pagos", resultado.parcelas.filter { it.pago }.map { it.id }.toIntArray())
        super.onSaveInstanceState(outState)
    }
}

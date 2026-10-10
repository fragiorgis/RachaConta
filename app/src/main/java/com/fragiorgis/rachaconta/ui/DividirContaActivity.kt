package com.fragiorgis.rachaconta.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.widget.doAfterTextChanged
import com.fragiorgis.rachaconta.R
import com.fragiorgis.rachaconta.databinding.ActivityDividirContaBinding
import com.fragiorgis.rachaconta.domain.ValidadorConta
import com.fragiorgis.rachaconta.navigation.NavegacaoConta

class DividirContaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDividirContaBinding
    private var pessoas = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDividirContaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepararJanela(binding.root)
        pessoas = savedInstanceState?.getInt("pessoas", 2) ?: 2
        binding.amountInput.setText(savedInstanceState?.getString("valor") ?: "")
        binding.nameInput.setText(savedInstanceState?.getString("nome") ?: "")
        binding.lessButton.setOnClickListener { pessoas = (pessoas - 1).coerceAtLeast(1); updateUI() }
        binding.moreButton.setOnClickListener { pessoas = (pessoas + 1).coerceAtMost(50); updateUI() }
        binding.divideButton.setOnClickListener { dividir() }
        binding.amountInput.doAfterTextChanged { binding.amountLayout.error = null }
        binding.nameInput.doAfterTextChanged { binding.nameLayout.error = null }
        binding.nameInput.setOnEditorActionListener { _, action, _ ->
            if (action == EditorInfo.IME_ACTION_DONE) { dividir(); true } else false
        }
        updateUI()
    }

    private fun updateUI() {
        binding.peopleCount.text = getString(R.string.people_number, pessoas)
        binding.peopleCount.contentDescription = if (pessoas == 1) getString(R.string.one_person) else resources.getQuantityString(R.plurals.people_count, pessoas, pessoas)
        binding.lessButton.isEnabled = pessoas > 1
        binding.moreButton.isEnabled = pessoas < 50
    }

    private fun dividir() {
        val validacao = ValidadorConta.validar(binding.amountInput.text.toString(), pessoas, binding.nameInput.text.toString())
        binding.amountLayout.error = if (validacao.erroValor) getString(R.string.invalid_amount) else null
        binding.nameLayout.error = if (validacao.erroNome) getString(R.string.invalid_name) else null
        val conta = validacao.conta
        if (conta == null) {
            if (validacao.erroValor) binding.amountInput.requestFocus() else binding.nameInput.requestFocus()
            return
        }
        WindowInsetsControllerCompat(window, binding.root).hide(WindowInsetsCompat.Type.ime())
        startActivity(NavegacaoConta.resultado(this, conta))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("pessoas", pessoas)
        outState.putString("valor", binding.amountInput.text.toString())
        outState.putString("nome", binding.nameInput.text.toString())
        super.onSaveInstanceState(outState)
    }
}

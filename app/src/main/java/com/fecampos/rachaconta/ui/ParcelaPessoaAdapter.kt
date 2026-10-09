package com.fecampos.rachaconta.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fecampos.rachaconta.R
import com.fecampos.rachaconta.databinding.ComponentParcelaPessoaBinding
import com.fecampos.rachaconta.model.ParcelaPessoa
import com.fecampos.rachaconta.util.FormatoDinheiro

class ParcelaPessoaAdapter(private val confirmar: (Int, Boolean) -> Unit) : RecyclerView.Adapter<ParcelaPessoaAdapter.Holder>() {
    private var parcelas: List<ParcelaPessoa> = emptyList()

    init { setHasStableIds(true) }
    override fun getItemId(position: Int): Long = parcelas[position].id.toLong()
    override fun getItemCount(): Int = parcelas.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ComponentParcelaPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val pessoa = parcelas[position]
        val b = holder.binding
        val context = b.root.context
        b.personName.text = context.getString(R.string.person_name, pessoa.id)
        b.shareValue.text = FormatoDinheiro.formatar(pessoa.valorCentavos)
        b.paymentStatus.setText(if (pessoa.pago) R.string.paid else R.string.pending)
        // Remover o listener evita eventos durante a reciclagem/preenchimento da View.
        b.paidCheck.setOnCheckedChangeListener(null)
        b.paidCheck.isChecked = pessoa.pago
        b.paidCheck.contentDescription = context.getString(R.string.payment_description, pessoa.id, b.shareValue.text)
        b.paidCheck.setOnCheckedChangeListener { _, marcado -> confirmar(pessoa.id, marcado) }
        b.personRow.setOnClickListener { b.paidCheck.performClick() }
    }

    fun apresentar(novas: List<ParcelaPessoa>) {
        val anteriores = parcelas
        parcelas = novas.toList()
        if (anteriores.isEmpty()) notifyItemRangeInserted(0, parcelas.size)
        else parcelas.indices.filter { anteriores[it] != parcelas[it] }.forEach { notifyItemChanged(it) }
    }

    class Holder(val binding: ComponentParcelaPessoaBinding) : RecyclerView.ViewHolder(binding.root)
}

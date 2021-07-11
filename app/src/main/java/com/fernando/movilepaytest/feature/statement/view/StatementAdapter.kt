package com.fernando.movilepaytest.feature.statement.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fernando.movilepaytest.R
import com.fernando.movilepaytest.feature.statement.model.Transactions

class StatementAdapter(
    val context: Context,
): RecyclerView.Adapter<StatementAdapter.StatementVH>(){

    private val listTransactions = arrayListOf<Transactions>()

    class StatementVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val label = itemView.findViewById<TextView>(R.id.tv_label_transaction)
        val value = itemView.findViewById<TextView>(R.id.tv_value_trasaction)
        val description = itemView.findViewById<TextView>(R.id.tv_description_transaction)

        fun bind(transaction: Transactions) {
            label.text = transaction.label
            value.text = transaction.value
            description.text = transaction.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementVH {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_transactions, parent, false)
        val vh = StatementVH(view)
        return vh
    }


    override fun onBindViewHolder(holder: StatementVH, position: Int) {
        holder.bind(listTransactions[position])
    }

    override fun getItemCount(): Int = listTransactions.size


    fun updateTransaction(list: List<Transactions>){
        listTransactions.clear()
        listTransactions.addAll(list)
        notifyDataSetChanged()
    }
}
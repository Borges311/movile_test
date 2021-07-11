package com.fernando.movilepaytest.feature.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fernando.movilepaytest.R
import com.fernando.movilepaytest.feature.main.model.SimpleWidget


class HomeAdapter(
    val context: Context,
    private val callback: (SimpleWidget?) -> Unit):
    RecyclerView.Adapter<HomeAdapter.HomeVH>() {
    private val widgetList = arrayListOf<SimpleWidget>()

    class HomeVH(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val content = itemView.findViewById<TextView>(R.id.tv_name)
        val value = itemView.findViewById<TextView>(R.id.tv_value)
        val btnWidget = itemView.findViewById<Button>(R.id.btn_action_widget)

        fun bind(widget: SimpleWidget){
            title.text = widget.contentTitle
            content.text = widget.contentType ?: widget.label
            value.text = widget.value ?: ""
            btnWidget.text = widget.buttonTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeVH {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_card_home, parent, false)
        val vh = HomeVH(view)
        vh.btnWidget.setOnClickListener {
            val widget = widgetList[vh.adapterPosition]
            callback(widget)
        }
        return vh
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.bind(widgetList[position])
    }

    override fun getItemCount(): Int = widgetList.size

    fun updateWidgets(list: List<SimpleWidget>){
        widgetList.clear()
        widgetList.addAll(list)
        notifyDataSetChanged()
    }
}
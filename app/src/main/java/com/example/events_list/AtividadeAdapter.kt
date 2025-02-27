package com.example.events_list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.events_list.CadastroActivity
import com.example.events_list.DetalhesActivity
import com.example.events_list.R
import com.example.events_list.Atividade
import com.example.events_list.AtividadeRepository

class AtividadeAdapter(private val atividades: MutableList<Atividade>) :
    RecyclerView.Adapter<AtividadeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNome: TextView = itemView.findViewById(R.id.txtNome)
        val btnSettings: ImageButton = itemView.findViewById(R.id.btnSettings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_atividade, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val atividade = atividades[position]
        holder.txtNome.text = atividade.nome

        // Clique no item para ver detalhes
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetalhesActivity::class.java).apply {
                putExtra("nome", atividade.nome)
                putExtra("responsavel", atividade.responsavel)
                putExtra("data", atividade.data)
                putExtra("descricao", atividade.descricao)
            }
            holder.itemView.context.startActivity(intent)
        }

        // Configurações: Editar ou Deletar
        holder.btnSettings.setOnClickListener { view ->
            val popup = PopupMenu(view.context, holder.btnSettings)
            popup.menuInflater.inflate(R.menu.menu_item, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_edit -> {
                        // Inicia CadastroActivity em modo de edição, passando a posição e dados da atividade
                        val context = view.context
                        val intent = Intent(context, CadastroActivity::class.java).apply {
                            putExtra("position", position)
                            putExtra("nome", atividade.nome)
                            putExtra("responsavel", atividade.responsavel)
                            putExtra("data", atividade.data)
                            putExtra("descricao", atividade.descricao)
                        }
                        context.startActivity(intent)
                        true
                    }
                    R.id.menu_delete -> {
                        // Remove a atividade e notifica a remoção no RecyclerView
                        atividades.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, atividades.size)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int = atividades.size
}

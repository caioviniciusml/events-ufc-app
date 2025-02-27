package com.example.events_list

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.events_list.AtividadeAdapter
import com.example.events_list.AtividadeRepository

// MainActivity.kt
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AtividadeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNovaAtividade = findViewById<Button>(R.id.btnNovaAtividade)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AtividadeAdapter(AtividadeRepository.listaAtividades)
        recyclerView.adapter = adapter

        btnNovaAtividade.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // Atualiza a lista para mostrar as novas atividades adicionadas
        adapter.notifyDataSetChanged()
    }
}


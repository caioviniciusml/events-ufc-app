package com.example.events_list

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalhesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val txtDetalheNome = findViewById<TextView>(R.id.txtDetalheNome)
        val txtDetalheResponsavel = findViewById<TextView>(R.id.txtDetalheResponsavel)
        val txtDetalheData = findViewById<TextView>(R.id.txtDetalheData)
        val txtDetalheDescricao = findViewById<TextView>(R.id.txtDetalheDescricao)

        txtDetalheNome.text = intent.getStringExtra("nome")
        txtDetalheResponsavel.text = intent.getStringExtra("responsavel")
        txtDetalheData.text = intent.getStringExtra("data")
        txtDetalheDescricao.text = intent.getStringExtra("descricao")
    }
}

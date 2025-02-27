package com.example.events_list

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.events_list.Atividade
import com.example.events_list.AtividadeRepository
import java.text.SimpleDateFormat
import java.util.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtResponsavel = findViewById<EditText>(R.id.edtResponsavel)
        val edtData = findViewById<EditText>(R.id.edtData)
        val edtDescricao = findViewById<EditText>(R.id.edtDescricao)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        // Se for edição, preenche os campos com os dados existentes
        val posicao = intent.getIntExtra("position", -1)
        if (posicao != -1) {
            edtNome.setText(intent.getStringExtra("nome"))
            edtResponsavel.setText(intent.getStringExtra("responsavel"))
            edtData.setText(intent.getStringExtra("data"))
            edtDescricao.setText(intent.getStringExtra("descricao"))
        }

        edtData.setOnClickListener {
            val calendario = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, ano, mes, dia ->
                val formatoData = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                calendario.set(ano, mes, dia)
                edtData.setText(formatoData.format(calendario.time))
            }, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        btnSalvar.setOnClickListener {
            val nome = edtNome.text.toString()
            val responsavel = edtResponsavel.text.toString()
            val data = edtData.text.toString()
            val descricao = edtDescricao.text.toString()

            if (nome.isNotEmpty() && responsavel.isNotEmpty() && data.isNotEmpty() && descricao.isNotEmpty()) {
                val novaAtividade = Atividade(nome, responsavel, data, descricao)
                if (posicao != -1) {
                    // Atualiza a atividade existente
                    AtividadeRepository.listaAtividades[posicao] = novaAtividade
                } else {
                    // Adiciona nova atividade
                    AtividadeRepository.listaAtividades.add(novaAtividade)
                }
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

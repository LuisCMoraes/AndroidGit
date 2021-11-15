package com.luis.pesquise

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    var ida = 0
    var resp1 = 0
    var somaida = 0
    var contida = 0
    var contazul = 0
    var resp4 = 0
    var pes = 0.0
    var alt = 0.0
    var mensagem = ""
    var mens2 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnOk = findViewById<Button>(R.id.btnOk)
        val edtIdade = findViewById<EditText>(R.id.edtIdade)
        val edtPeso = findViewById<EditText>(R.id.edtPeso)
        val edtAltura = findViewById<EditText>(R.id.edtAltura)
        val txtMensagem = findViewById<TextView>(R.id.txtMsg)
        val ragIOlhos = findViewById<RadioGroup>(R.id.ragOlhos)
        val ragICabelos = findViewById<RadioGroup>(R.id.ragCabelos)

        btnOk.setOnClickListener {
            ida = edtIdade.text.toString().toInt()
            pes = edtPeso.text.toString().toDouble()
            alt = edtAltura.text.toString().toDouble()
            //Resposta 1
            if (ida > 50 && pes < 60) {
                resp1++
            }

            //Resposta 2
            if (alt < 1.5) {
                somaida += ida
                contida++
            }
            //Resposta 3
            val idOlho = ragIOlhos.checkedRadioButtonId
            val idCabelo = ragICabelos.checkedRadioButtonId
            if (idOlho == -1) {
                Toast.makeText(this@MainActivity,
                        "Selecione uma cor de Olhos",
                        Toast.LENGTH_LONG)
                        .show()
            } else {
                val radOlho = ragIOlhos.findViewById<RadioButton>(idOlho)
                if (radOlho.text == "Azul") {
                    contazul++
                }
            }
            //Resposta 4
            if (idCabelo == -1) {
                Toast.makeText(this@MainActivity,
                        "Selecione uma cor de Cabelo",
                        Toast.LENGTH_SHORT)
                        .show()
            } else {
                val radOlho = ragIOlhos.findViewById<RadioButton>(idOlho)
                val radCabelo = ragICabelos.findViewById<RadioButton>(idCabelo)
                if (radCabelo.text == "Ruivo" && radOlho.text != "Azul") {
                    resp4++
                }
            }
            mens2 = if (contida == 0) {
                "Não Calculável"
            } else {
                (somaida / contida).toString()
            }
            mensagem = """
                Resposta1: $resp1
                Resposta2: $mens2
                Resposta3: ${contazul / 20 * 100}
                Resposta4: $resp4
                """.trimIndent()
            txtMensagem.text = mensagem
            ragIOlhos.clearCheck()
            ragICabelos.clearCheck()
        }
    }
}
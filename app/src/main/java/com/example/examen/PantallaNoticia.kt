package com.example.examen

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PantallaNoticia : AppCompatActivity() {
    private val STRING_TITULO = ""
    private val STRING_CONTENIDO = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.noticia)
        //linkeamos valores con componentes del layout con su id
        val Titulo = findViewById<TextView>(R.id.textViewTitulo)
        val Contenido = findViewById<TextView>(R.id.textViewContenido)

        if (savedInstanceState != null){
            //Si tenemos un estado guaradado recuperamos el texto de las constantes privadas
            Titulo.text = savedInstanceState.getString(STRING_TITULO)
            Contenido.text = savedInstanceState.getString(STRING_CONTENIDO)
        }


        //Recivimos strings del intent y los ponemos en los valores
        Titulo.text = intent.getStringExtra("Titulo").toString()
        Contenido.text = intent.getStringExtra("Contenido").toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Guardamos los valores en las constantes privadas
        outState.putString(STRING_TITULO,intent.getStringExtra("Titulo").toString())
        outState.putString(STRING_CONTENIDO,intent.getStringExtra("Contenido").toString())
    }
}
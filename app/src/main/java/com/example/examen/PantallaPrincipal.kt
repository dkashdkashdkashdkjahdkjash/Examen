package com.example.examen

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PantallaPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.pantalla_principal)

        //Conectamos boton con el componente imageButton del layout usando su id
        val boton = findViewById<ImageButton>(R.id.imageButton)

        //Le ponemos un listener al boton
        boton.setOnClickListener{
            //Creamos un intent para la PantallaListaNoticias
            val intent = Intent(this, PantallaListaNoticias::class.java)
            //Lanzamos el intent
            this.startActivity(intent)
        }
    }
}
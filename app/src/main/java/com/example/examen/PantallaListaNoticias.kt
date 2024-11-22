package com.example.examen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.component1

class PantallaListaNoticias : AppCompatActivity(), AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    //lateinit para usarlo luego y en mas que solo en el onCreate
    lateinit var spin : Spinner
    lateinit var listView : ListView

    //He intentado poner los array en el strings.xml y no me los pillaba
    //Hacemos el array conel titulo de las noticias
    var listaTitulo = arrayOf("Noticia de politica 1",
        "Noticia de politica 2",
        "Noticia de Economía 1",
        "Noticia de economía 2",
        "Noticia de cultura 1",
        "Noticia de cultura 2",
        "Noticia de deportes 1",
        "Noticia de deportes 2")
    //lista de contenidos
    val listaContenido = arrayOf("Contenido de la noticia de politica 1",
        "Contenido de la noticia de politica 2",
        "Contenido de la noticia de economía 1",
        "Contenido de la noticia de economía 2",
        "Contenido de la noticia de cultura 1",
        "Contenido de la noticia de cultura 2",
        "Contenido de la noticia de deportes 1",
        "Contenido de la noticia de deportes 2")
    //lsita de imagenes
    val listaImagenes = arrayOf(
        R.mipmap.ic_politics,
        R.mipmap.ic_economy,
        R.mipmap.ic_culture,
        R.mipmap.ic_sports
    )

    //lista de categorias
    val listaCategorias = arrayOf(
        "Politica",
        "Economía",
        "Cultura",
        "Deportes"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Conectamos spin con el componente spinner del layout usando su id
        spin = findViewById(R.id.spinner)
        //Conectamos listView con el componente listView del layout usando su id
        listView = findViewById<ListView>(R.id.listView)


        //Hacemos un adaptador para el spinner
        val adaptadorPersonalizado = AdaptadorPersonalizado(this,R.layout.linea_spinner,listaCategorias)
        spin.adapter = adaptadorPersonalizado


        //El listener del spinner
        spin.onItemSelectedListener = this

        //Hacemos otro adaptador para el listView
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1,listaTitulo)

        //Le aplicamos el adaptador a la lista
        listView.adapter = adaptador

        listView.onItemClickListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //Una chapuza pero asi se filtra
        if (position == 0){
            listaTitulo = arrayOf("Noticia de politica 1","Noticia de politica 2")
            val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1,listaTitulo)
            listView.adapter = adaptador
        }else if (position == 1){
            listaTitulo = arrayOf("Noticia de economía 1","Noticia de economía 2")
            val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1,listaTitulo)
            listView.adapter = adaptador
        }else if (position == 2){
            listaTitulo = arrayOf("Noticia de cultura 1","Noticia de cultura 2")
            val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1,listaTitulo)
            listView.adapter = adaptador
        }else if(position == 3){
            listaTitulo = arrayOf("Noticia de deporte 1","Noticia de deporte 2")
            val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1,listaTitulo)
            listView.adapter = adaptador
        }


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    //Codigo copiado, pegado y adaptado
    private inner class AdaptadorPersonalizado(
        context: Context,
        resource: Int,
        objects: Array<String>
    ) : ArrayAdapter<String>(context, resource, objects) {
        //Constructor de mi adaptador paso el contexto (this)
        // el layout, y los elementos

        /**
         * Reescribo el método getDropDownView para que me devuelva una fila personalizada en la
         * lista desplegable en vez del elemento que se encuentra en esa posición
         * @param posicion
         * @param ViewConvertida
         * @param padre
         * @return
         */
        override fun getDropDownView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {


            // Llama a la función para crear la fila personalizada y la devuelve
            return crearFilaPersonalizada(position, convertView, parent)
        }

        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            // Este método se llama para mostrar una vista personalizada en el elemento seleccionado

            // Llama a la función para crear la fila personalizada y la devuelve
            return crearFilaPersonalizada(position, convertView, parent)
        }

        /**
         * Método que me crea mis filas personalizadas pasando como parámetro la posición
         * la vista y la vista padre
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        private fun crearFilaPersonalizada(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {

            // Crea un objeto LayoutInflater para inflar la vista personalizada desde un diseño XML
            val layoutInflater = LayoutInflater.from(context)

            //Declaro una vista de mi fila, y la preparo para inflarla con datos
            // Los parametros son: XML descriptivo
            // Vista padre
            // Booleano que indica si se debe ceñir a las características del padre
            // ?: si existe
            val rowView = convertView ?: layoutInflater.inflate(R.layout.linea_spinner, parent, false)

            //Fijamos el nombre de la categoria
            rowView.findViewById<TextView>(R.id.textView).text = listaCategorias[position]

            //Fijamos la imagen de la ciudad
            rowView.findViewById<ImageView>(R.id.imageView).setImageResource(listaImagenes[position])

            // Devuelve la vista de fila personalizada
            return rowView
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //Creamos un intent
        val intent = Intent(this,PantallaNoticia::class.java)
        //Pasamos strings por el intent
        intent.putExtra("Titulo",listaTitulo[position])
        intent.putExtra("Contenido",listaContenido[position])
        //Iniciamos el intent
        this.startActivity(intent)
    }

}
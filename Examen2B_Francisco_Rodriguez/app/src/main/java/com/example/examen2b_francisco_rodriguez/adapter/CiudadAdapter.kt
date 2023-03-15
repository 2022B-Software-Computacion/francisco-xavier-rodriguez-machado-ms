package com.example.examen2b_francisco_rodriguez.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_francisco_rodriguez.Ciudad
import com.example.examen2b_francisco_rodriguez.R
import com.google.firebase.firestore.FirebaseFirestore

class CiudadAdapter(private val listaCiudades: ArrayList<Ciudad>) :
    RecyclerView.Adapter<CiudadAdapter.CiudadViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class CiudadViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val nombreCiudad: TextView = itemView.findViewById(R.id.tvTítuloNombreCiudadContenido)
        val numeroHabitantes: TextView =
            itemView.findViewById(R.id.tvTituloNumeroHabitantesContenido)
        val fechaDeFundacion: TextView = itemView.findViewById(R.id.tvTituloFechaFundacionCiudadContenido)
        val estaEnFiestasPatronales: TextView =
            itemView.findViewById(R.id.tvTituloEstaEnFiestasPatronalesContenido)
        val btnEliminarCiudad: Button = itemView.findViewById(R.id.btnEliminarCiudad)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CiudadViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_view_ciudades,
            parent, false
        )
        return CiudadViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int = listaCiudades.size

    override fun onBindViewHolder(holder: CiudadViewHolder, position: Int) {
        val item = listaCiudades[position]
        holder.nombreCiudad.text = listaCiudades[position].nombre
        holder.numeroHabitantes.text = listaCiudades[position].numeroDeHabitantes.toString()
        holder.fechaDeFundacion.text = listaCiudades[position].fechaDeFundacion
        holder.estaEnFiestasPatronales.text =
            listaCiudades[position].estaEnFiestasPatronales.toString()

        holder.btnEliminarCiudad.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val activity = it.context as AppCompatActivity
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Borrar")
            builder.setMessage("¿Está seguro de borrar la ciudad?")
            builder.setPositiveButton("Si") { dialogInterface, i: Int ->
                val borrarItem = db.collection("ciudades").document(item.id)
                println(item.id)
                db.runBatch { batch ->
                    batch.delete(borrarItem)
                }.addOnCompleteListener {
                    Toast.makeText(activity, "Se borro exitosamente la ciudad", Toast.LENGTH_SHORT)
                        .show()
                    listaCiudades.removeAt(position)
                    notifyDataSetChanged()
                }
            }
            builder.setNegativeButton("No") { dialogInterface, i: Int ->
                println("Sin seleccionado")
            }
            builder.show()
        }
    }
}
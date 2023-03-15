package com.example.examen2b_francisco_rodriguez.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_francisco_rodriguez.Provincia
import com.example.examen2b_francisco_rodriguez.R
import com.google.firebase.firestore.FirebaseFirestore

class ProvinciaAdapter(private val listaProvincias: ArrayList<Provincia>) :
    RecyclerView.Adapter<ProvinciaAdapter.ProvinciaViewHolder>() {
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class ProvinciaViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val nombreProvincia: TextView = itemView.findViewById(R.id.tvTítuloNombreProvinciaContenido)
        val gradoDeSeguridad: TextView =
            itemView.findViewById(R.id.tvTituloGradoDeSeguridadContenido)
        val fechaDeFundacion: TextView =
            itemView.findViewById(R.id.tvTituloFechaDeFundacionProvinciaContenido)
        val estaEnFiestasProvinciales: TextView =
            itemView.findViewById(R.id.tvTituloEstaEnFiestasProvincialesContenido)
        val btnBorrarProvincia: Button = itemView.findViewById(R.id.btnEliminarProvincia)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinciaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_view_provincias,
            parent, false
        )
        return ProvinciaViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int = listaProvincias.size

    override fun onBindViewHolder(holder: ProvinciaViewHolder, position: Int) {
        val item = listaProvincias[position]
        holder.nombreProvincia.text = listaProvincias[position].nombre
        holder.gradoDeSeguridad.text = listaProvincias[position].gradoDeSeguridad.toString()
        holder.fechaDeFundacion.text = listaProvincias[position].fechaDeFundacion
        holder.estaEnFiestasProvinciales.text =
            listaProvincias[position].estaEnFiestasProvinciales.toString()

        holder.btnBorrarProvincia.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val activity = it.context as AppCompatActivity
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Eliminar")
            builder.setMessage("¿Está seguro de eliminar la provincia?")
            builder.setPositiveButton("Si") { dialogInterface, i: Int ->
                val eliminarItem = db.collection("provincias").document(item.id)
                println(item.id)
                db.runBatch { batch ->
                    batch.delete(eliminarItem)
                }.addOnCompleteListener {
                    Toast.makeText(
                        activity,
                        "La provincia se eliminó con éxito",
                        Toast.LENGTH_SHORT
                    ).show()
                    listaProvincias.removeAt(position)
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
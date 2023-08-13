package com.example.aplicacionminceturcl3

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacionminceturcl3.domain.entity.RestauranteModel

class MainAdapter(var items: MutableList<RestauranteModel>, var iCardRestaurante: ICardRestaurante ) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    interface ICardRestaurante{
        fun onRestauranteCardClick( item: RestauranteModel)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), OnClickListener{
        val tvNombre: TextView = itemView.findViewById(R.id.rvNombre)
        val tvDescripcion: TextView = itemView.findViewById(R.id.rvDescripcion)
        val imagen : ImageFilterView = itemView.findViewById(R.id.rvImagen)
        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            iCardRestaurante.onRestauranteCardClick(items [adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurante, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.tvNombre.text = item.nombre
        holder.tvDescripcion.text= item.descripcion
        Glide.with(holder.itemView.context).load(item.imagen).into(holder.imagen)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems : List<RestauranteModel>){
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }
}
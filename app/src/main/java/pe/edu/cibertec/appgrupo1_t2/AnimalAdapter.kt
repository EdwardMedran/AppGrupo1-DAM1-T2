package pe.edu.cibertec.appgrupo1_t2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.cibertec.appgrupo1_t2.databinding.ItemAnimalBinding

class AnimalAdapter(private var lista: List<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = lista[position]
        holder.binding.tvNombre.text = animal.nombre
        holder.binding.tvDescripcion.text = animal.descripcion

        Glide.with(holder.itemView.context)
            .load(animal.imagenUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            .centerCrop()
            .into(holder.binding.ivAnimal)
    }

    override fun getItemCount(): Int = lista.size

    fun actualizar(nuevaLista: List<Animal>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }
}


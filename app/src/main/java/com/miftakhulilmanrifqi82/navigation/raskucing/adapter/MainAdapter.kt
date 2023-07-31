package com.miftakhulilmanrifqi82.navigation.raskucing.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.miftakhulilmanrifqi82.navigation.raskucing.DetailFragment
import com.miftakhulilmanrifqi82.navigation.raskucing.R
import com.miftakhulilmanrifqi82.navigation.raskucing.model.ModelMain
import kotlinx.android.synthetic.main.list_item_main.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(
    private val context: Context,
    private val modelMainList: MutableList<ModelMain>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(), Filterable {

    private val modelMainFilterList: List<ModelMain>
    private lateinit var navController: NavController

    init {
        modelMainFilterList = ArrayList(modelMainList)
    }

    override fun getFilter(): Filter {
        return modelFilter
    }

    private val modelFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ModelMain> = java.util.ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(modelMainFilterList)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (modelMainFilter in modelMainFilterList) {
                    if (modelMainFilter.nama?.toLowerCase()?.contains(filterPattern) == true) {
                        filteredList.add(modelMainFilter)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            modelMainList.clear()
            modelMainList.addAll(results.values as List<ModelMain>)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        navController = Navigation.findNavController(parent)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = modelMainList[position]
        holder.tvNamaTanaman.text = item.nama

        // Menambahkan onClickListener pada item RecyclerView
        holder.cvListTanaman.setOnClickListener {
            // Menggunakan Bundle untuk mengirim data ke DetailFragment
            val bundle = Bundle()
            bundle.putSerializable(DetailFragment.DETAIL_TANAMAN, item)

            // Menggunakan NavController untuk berpindah ke DetailFragment
            navController.navigate(R.id.action_utamaFragment_to_detailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return modelMainList.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListTanaman: CardView = itemView.findViewById(R.id.cvListTanaman)
        var tvNamaTanaman: TextView = itemView.findViewById(R.id.tvNamaTanaman)

        init {
            cvListTanaman = itemView.cvListTanaman
            tvNamaTanaman = itemView.tvNamaTanaman
        }
    }
}

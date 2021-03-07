package com.thor.beritahuapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thor.beritahuapp.databinding.ItemBeritaBinding
import com.thor.beritahuapp.detail.BeritaDetail
import com.thor.beritahuapp.model.ArticlesItem
import java.util.*
import kotlin.collections.ArrayList

class BeritaAdapter(var data: ArrayList<ArticlesItem?>?, var c: Context) : RecyclerView.Adapter<BeritaAdapter.BeritaHolder>(), Filterable {

    var listBerita = ArrayList<ArticlesItem>()

    init {
        listBerita.clear()
        data?.forEach { articlesItem ->
            if (articlesItem != null) {
                listBerita.add(articlesItem)
            }
        }
    }

    class BeritaHolder(var binding: ItemBeritaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItem?, c: Context) {
            binding.itemTitle.text = data?.title
            Glide.with(c).load(data?.urlToImage).into(binding.itemImg)
            binding.itemTitle.setOnClickListener {
                val intent = Intent(c, BeritaDetail::class.java)
                intent.putExtra("jdl", data?.title)
                intent.putExtra("url", data?.url)
                binding.root.context.startActivity(intent)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaHolder {
        val binding = ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeritaHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaHolder, position: Int) {
        holder.bind(data?.get(position), c)

    }

    override fun getItemCount() = data?.size ?: 0

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    data?.forEach { articlesItem ->
                        if (articlesItem != null) {
                            listBerita.add(articlesItem)
                        }
                    }
                } else {
                    val resultList = ArrayList<ArticlesItem?>()
                    if (data != null) {
                        for (row in data!!) {
                            if (row != null) {
                                if (row.title!!.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                                    resultList.add(row)
                                }
                            }
                        }
                    }
                    data = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = data

                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                listBerita = results?
                notifyDataSetChanged()
            }

        }
    }


}
package com.example.shibbydi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shibbydi.databinding.ListItemShibeBinding

class ShibeAdapter : RecyclerView.Adapter<ShibeAdapter.ShibeViewHolder>(){

    private val shibes = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShibeViewHolder {
        return ShibeViewHolder(
                ListItemShibeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: ShibeViewHolder, position: Int) {
        holder.onBind(shibes[position])
    }

    override fun getItemCount(): Int {
        return shibes.size
    }

    fun loadShibes(listOfShibes : List<String>) {
        shibes.clear()
        shibes.addAll(listOfShibes)
        notifyDataSetChanged()
    }

    class ShibeViewHolder(private val binding: ListItemShibeBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun onBind(url: String) {
                    with(binding) {
                        Glide.with(root).load(url).centerCrop().into(shibeIv)
                    }
                }
            }
}
package com.kimmandoo.project_exercise_3_2.feature2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimmandoo.project_exercise_3_2.R

class ListAdapter_RecyclerView(private val recipecon: ArrayList<recipe>) : RecyclerView.Adapter<ListAdapter_RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter_RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return ViewHolder(view)
//        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.recipelistview, parent, false)
//        return FiveFragmentAdapter_RecyclerView.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = recipecon[position]
        holder.txtName.text = currentItem.name
        holder.txtCheif.text = currentItem.chief
    }

    override fun getItemCount(): Int{
        return recipecon.size
    }

    // 각 항목에 필요한 기능을 구현
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.recipeName)
        val txtCheif: TextView = itemView.findViewById(R.id.recipeCheif)
    }
}

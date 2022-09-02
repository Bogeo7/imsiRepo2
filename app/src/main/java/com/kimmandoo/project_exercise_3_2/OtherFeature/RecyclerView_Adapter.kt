package com.kimmandoo.project_exercise_3_2.OtherFeature

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimmandoo.project_exercise_3_2.R
import com.kimmandoo.project_exercise_3_2.OtherFeature.recipe

class ListAdapter_RecyclerView(private val recipecon: MutableList<recycler>) : RecyclerView.Adapter<ListAdapter_RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter_RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_view_item, parent, false)
        return ViewHolder(view)
//        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.recipelistview, parent, false)
//        return FiveFragmentAdapter_RecyclerView.ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = recipecon[position]
        holder.txtName.text = currentItem.Tname
        holder.txtCheif.text = currentItem.Tcheif
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

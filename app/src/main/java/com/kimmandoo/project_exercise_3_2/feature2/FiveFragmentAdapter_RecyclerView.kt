package com.kimmandoo.project_exercise_3_2.feature2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kimmandoo.project_exercise_3_2.R

class FiveFragmentAdapter_RecyclerView(private val recipecon: FeatureFiveFragment) : RecyclerView.Adapter<FiveFragmentAdapter_RecyclerView.ViewHolder>(){

    var datas = mutableListOf<recipe>()
    override fun getItemCount(): Int = datas.size
    override fun onBindViewHolder(holder: FiveFragmentAdapter_RecyclerView.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.recipelistview, parent, false)
        return FiveFragmentAdapter_RecyclerView.ViewHolder(inflatedView)
    }

    // 각 항목에 필요한 기능을 구현
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val txtName: TextView = itemView.findViewById(R.id.recipeName)
        private val txtCheif: TextView = itemView.findViewById(R.id.recipeCheif)

        fun bind(item: recipe) {
            txtName.text = item.name
            txtCheif.text = item.chief
        }
    }
}

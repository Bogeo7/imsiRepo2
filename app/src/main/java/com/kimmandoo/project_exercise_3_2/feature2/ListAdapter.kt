package com.kimmandoo.project_exercise_3_2.feature2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimmandoo.project_exercise_3_2.databinding.ListItemBinding
import com.kimmandoo.project_exercise_3_2.feature1.OneAdapter

class ListAdapter(private val list: MutableList<IngredientList>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class ItemViewHolder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        val item = binding.listItem
        val btnPlus = binding.ingredientItemIvPlus
        val btnMinus = binding.ingredientItemIvMinus
        fun setItem(item: IngredientList){
            with(binding){
                ingredientItemTv.text = item.name
            }
        }
    }

    interface ItemClick{
        fun onClick(view : View, position: Int)
    }

    var itemClick : ItemClick? = null
    var minusClick : ItemClick? = null
    var plusClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            if(itemClick != null && list[position].name == "onion"){
                holder.item.setOnClickListener{v->
                    itemClick!!.onClick(v, position)

                }
            }
            if(minusClick != null){
                holder.btnMinus.setOnClickListener{v->
                    minusClick!!.onClick(v, position)
                }
            }
            if(plusClick != null){
                holder.btnPlus.setOnClickListener{v->
                    plusClick!!.onClick(v, position)
                }
            }
            holder.setItem(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
package com.kimmandoo.project_exercise_3_2.feature1

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimmandoo.project_exercise_3_2.databinding.IngredientItemBinding

class OneAdapter(val list: MutableList<Ingredient>): RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {

    inner class itemViewHolder(val binding: IngredientItemBinding): RecyclerView.ViewHolder(binding.root){
        val btnPlus = binding.ingredientItemIvPlus
        val btnMinus = binding.ingredientItemIvMinus

        fun setItem(item: Ingredient){
            with(binding){
                var itemCount = item.nums
                ingredientItemTv.text = item.name
                ingredientItemTvNums.text = "$itemCount 개"
                btnPlus.setOnClickListener {
                    itemCount++
                    ingredientItemTvNums.text = "$itemCount 개"
                }
            }
        }
    }

    interface ItemClick{
        fun onClick(view : View, position: Int)
    }

    var minusClick : ItemClick? = null
    var plusClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = IngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return itemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is itemViewHolder) {
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
            holder.setItem(list[position]!!)

        } else {

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}
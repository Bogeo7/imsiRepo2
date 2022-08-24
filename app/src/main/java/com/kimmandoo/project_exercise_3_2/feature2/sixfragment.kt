package com.kimmandoo.project_exercise_3_2.feature2

import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.icu.number.Notation.simple
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.kimmandoo.project_exercise_3_2.databinding.FragmentFeatureFourBinding
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import org.json.JSONArray as JSONArray2

class sixfragment : Fragment() {
    val ingredientList = mutableListOf<IngredientList>()
    val ingredientExp = mutableListOf<IngredientExp>()
    var getDeleteSign = false
    var expRefresh = false

    fun onViewCreated(listAdapter: ListAdapter){
        var gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://jaeryurp.duckdns.org:40131/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(IngredientListAPI::class.java)
        val callResult = api.getResult()

        var resultJsonArray : JsonArray?

        callResult.enqueue(object : Callback<JsonArray> {
            override fun onResponse(
                call: Call<JsonArray>,
                response: Response<JsonArray>
            ) {
                Log.d("FeatTwo", "${response.body()}")
                resultJsonArray = response.body()

                val jsonArray = JSONTokener(resultJsonArray.toString()).nextValue() as org.json.JSONArray
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i).getString("Tables_in_test")
                    ingredientList.add(IngredientList(item))
                }
                listAdapter.notifyDataSetChanged()
                Log.d("List","${ingredientList}")
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.d("FeatTwo", "실패 : $t")
            }
        })
    }
}
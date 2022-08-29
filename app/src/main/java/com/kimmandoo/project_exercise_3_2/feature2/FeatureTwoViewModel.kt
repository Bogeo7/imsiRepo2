package com.kimmandoo.project_exercise_3_2.feature2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.exp

class FeatureTwoViewModel: ViewModel() {
    val ingredientList = mutableListOf<IngredientList>()
    val ingredientExp = mutableListOf<IngredientExp>()
    var getDeleteSign = false
    var expRefresh = false

    fun retrofitList(listAdapter: ListAdapter){
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

                val jsonArray = JSONTokener(resultJsonArray.toString()).nextValue() as JSONArray
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

    fun retrofitExp(){
        var gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://jaeryurp.duckdns.org:40131/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(IngredientExpAPI::class.java)
        val callResult = api.getResult("onion")

        var resultJsonArray : JsonArray?

        callResult.enqueue(object : Callback<JsonArray> {
            override fun onResponse(
                call: Call<JsonArray>,
                response: Response<JsonArray>
            ) {
                Log.d("FeatTwo", "${response.body()}")
                resultJsonArray = response.body()

                val jsonArray = JSONTokener(resultJsonArray.toString()).nextValue() as JSONArray
                for (i in 0 until jsonArray.length()) {
                    val count = jsonArray.getJSONObject(i).getString("count")
                    val exp = jsonArray.getJSONObject(i).getString("expiration")
                    if(!expRefresh){
                        ingredientExp.add(IngredientExp(count, exp))
                    }else{
                        ingredientExp[i] = IngredientExp(count, exp)
                    }
                }
                Log.d("List","${ingredientExp[2]}")
                expRefresh = true
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.d("FeatTwo", "실패 : $t")
            }
        })
    }

    fun retrofitCountDelete(){
        var gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://jaeryurp.duckdns.org:40131/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(IngredientExpAPI::class.java)
        val callResult = api.minusOne("1")

        var resultJsonArray : JsonArray?

        callResult.enqueue(object : Callback<JsonArray> {
            override fun onResponse(
                call: Call<JsonArray>,
                response: Response<JsonArray>
            ) {
                Log.d("FeatTwo", "${response.body()}")
                resultJsonArray = response.body()

                val jsonArray = JSONTokener(resultJsonArray.toString()).nextValue() as JSONArray
                for (i in 0 until jsonArray.length()) {
                    val count = jsonArray.getJSONObject(i).getString("count")
                    val exp = jsonArray.getJSONObject(i).getString("expiration")
                    if(!getDeleteSign){
                        ingredientExp.add(IngredientExp(count, exp))
                    }else{
                        ingredientExp[i] = IngredientExp(count, exp)
                    }
                }
                Log.d("List","${ingredientExp[2]}")
                getDeleteSign = true
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.d("FeatTwo", "실패 : $t")
            }
        })
    }
}
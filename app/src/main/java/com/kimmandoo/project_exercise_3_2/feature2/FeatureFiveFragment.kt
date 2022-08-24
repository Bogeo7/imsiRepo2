package com.kimmandoo.project_exercise_3_2.feature2;

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


class FeatureFiveFragment : Fragment() {
    private val serviceKey = "Cname"
    private var _binding: FragmentFeatureFourBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeatureFourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://jaeryurp.duckdns.org:40131/")
//            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)) //있으나마나한 코드...
            .build()
        val api = retrofit.create(recipeapi::class.java)
        //원래는 getResult에 query 넣어야됨
        /*이슈가 있음.
        * 원래 json파일이 response body로 와야되는데 웹 태그(<pre></pre>)가 붙어서 나옴. substring으로 jsonString으로 강제로 만들어서 바꿈
        * */
        val callResult = api.getResult()
        val refrigeResult = api.getRefrigeTable()
        var resultJsonArray : JsonArray?
        var resultRefrigeJSON : JsonArray?
        val items = mutableListOf<recipe>()

        callResult.enqueue(object : Callback<JsonArray> {
            override fun onResponse(
                call: Call<JsonArray>,
                response: Response<JsonArray>
            ) {
//                Log.d("FeatTwo", "성공 : ${response.body()}")
                resultJsonArray = response.body()
//                Log.d("FeatTwo", "json : ${resultJsonArray.toString()}")

                val jsonArray = JSONTokener(resultJsonArray.toString()).nextValue() as JSONArray2
                val convertArray = MutableList<JSONObject>(jsonArray.length()) { i -> jsonArray.getJSONObject(i)}. map {  Gson().fromJson<HashMap<String, String>>(it.toString(), HashMap::class.java) }
                val filterMainIngredient = convertArray.filter { it?.get("mainIngredient") == "tomato" }
//                val mainItems = filterMainIngredient.filter { it?.get("chief") == "A" }
//                val subItems = filterMainIngredient.filter { it?.get("chief") != "A" }
                for (i in 0 until jsonArray.length()) {
                    val name = jsonArray.getJSONObject(i).getString("name")
                    val chief = jsonArray.getJSONObject(i).getString("chief")
                    val step1 = jsonArray.getJSONObject(i).getString("step1")
                    val step2 = jsonArray.getJSONObject(i).getString("step2")
                    val step3 = jsonArray.getJSONObject(i).getString("step3")
                    val step4 = jsonArray.getJSONObject(i).getString("step4")
                    val step5 = jsonArray.getJSONObject(i).getString("step5")
                    val step6 = jsonArray.getJSONObject(i).getString("step6")
                    val step7 = jsonArray.getJSONObject(i).getString("step7")
                    val step8 = jsonArray.getJSONObject(i).getString("step8")
                    val Ingredient = jsonArray.getJSONObject(i).getString("ingredient")
                    val url = jsonArray.getJSONObject(i).getString("link")
                    val match = 0
                    val list = Ingredient.substring(1,Ingredient.length-1).split(", ").toList()
                    items.add(recipe(name, list, chief, step1, step2, step3, step4, step5, step6, step7, step8, url, match))
                }
                val mainItems = items.filter { it.chief == "A" }
                val subItems = items.filter { it.chief != "A" }
                Log.d("List","$mainItems")
                Log.d("sub:","$subItems")
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Log.d("FeatTwo", "실패 : $t")
            }


        })

//        refrigeResult.enqueue(object : Callback<JsonArray>{
//            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
//                resultRefrigeJSON = response.body()
//                val refrigelist = mutableListOf<refrige>()
//
//                val jsonArray = JSONTokener(resultRefrigeJSON.toString()).nextValue() as JSONArray2
//                for (i in 0 until jsonArray.length()) {
//                    val name = jsonArray.getJSONObject(i).getString("name")
//                    refrigelist.add(refrige(name))
//                }
//                Log.d("List","$refrigelist")
//            }
//
//            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
//                Log.d("FeatTwo", "실패 : $t")
//            }
//
//        })

        fun sortrecipe(){
            for (){

            }
        }
    }
}

package com.kimmandoo.project_exercise_3_2.feature2

import com.google.gson.JsonArray
import retrofit2.http.GET
import retrofit2.Call

interface selectDateAPI {
    @GET("/json.php")
    fun getResult(

    ): Call<JsonArray>
}


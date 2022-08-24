package com.kimmandoo.project_exercise_3_2.feature1

import androidx.lifecycle.ViewModel

class FeatureOneViewModel : ViewModel() {
    override fun onCleared() {
        super.onCleared()
    }

    val ingredientList = mutableListOf(
        Ingredient("당근"),
        Ingredient("우유", 0),
        Ingredient("파", 3),
        Ingredient("호박", 1),
        Ingredient("양파", 4),
        Ingredient("감자", 1)
    )

    val toolList = mutableListOf(
        Ingredient("후라이팬"),
        Ingredient("냄비", 0),
        Ingredient("주걱", 0),
        Ingredient("국자", 0),
        Ingredient("집게", 0),
        Ingredient("채", 0),
        Ingredient("탐침온도계", 0),
    )

}
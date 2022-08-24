package com.kimmandoo.project_exercise_3_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.kimmandoo.project_exercise_3_2.databinding.ActivityMainBinding
import com.kimmandoo.project_exercise_3_2.feature1.FeatureOneFragment
import com.kimmandoo.project_exercise_3_2.feature1.FeatureOneViewModel
import com.kimmandoo.project_exercise_3_2.feature2.*


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private var container = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        container = binding.container.id

        binding.btnFun1.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(container, FeatureOneFragment()).commitAllowingStateLoss()
        }
        binding.btnFun2.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(container, FeatureTwoFragment()).commitAllowingStateLoss()
        }
        binding.btnFun3.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(container, FeatureTwoTwoFragment()).commitAllowingStateLoss()
        }
        binding.btnFun4.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(container, FeatureFourFragment()).commitAllowingStateLoss()
        }
        binding.btnFun5.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(container, FeatureFiveFragment())
                .commitAllowingStateLoss()
        }
        binding.btnFun6.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(container, sixfragment())
                .commitAllowingStateLoss()
        }
    }
}
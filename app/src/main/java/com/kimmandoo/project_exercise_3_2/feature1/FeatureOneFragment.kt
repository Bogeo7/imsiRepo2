package com.kimmandoo.project_exercise_3_2.feature1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimmandoo.project_exercise_3_2.databinding.FragmentFeatureOneBinding

class FeatureOneFragment : Fragment() {
    private var _binding: FragmentFeatureOneBinding? = null
    private val binding get() = _binding!!
    private val featureOneViewModel by lazy { ViewModelProvider(this).get(FeatureOneViewModel::class.java) }
    private lateinit var input: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeatureOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvAdapter = OneAdapter(featureOneViewModel.ingredientList)
        val toolAdapter = TwoAdapter(featureOneViewModel.toolList)

        binding.feat1Rv.adapter = rvAdapter
        binding.feat1Rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.feat1Rv2.adapter = toolAdapter
        binding.feat1Rv2.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        rvAdapter.minusClick = object : OneAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                featureOneViewModel.ingredientList.removeAt(position)
                rvAdapter.notifyItemRemoved(position)
                rvAdapter.notifyItemRangeChanged(position, featureOneViewModel.ingredientList.size);
            }
        }

        rvAdapter.plusClick = object : OneAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                //내부 data 만들어서 관리하면 될 것 같음. room으로
            }
        }

        toolAdapter.minusClick = object : TwoAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                featureOneViewModel.toolList.removeAt(position)
                toolAdapter.notifyItemRemoved(position)
                toolAdapter.notifyItemRangeChanged(position, featureOneViewModel.toolList.size);
            }
        }

        toolAdapter.plusClick = object : TwoAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                //내부 data 만들어서 관리하면 될 것 같음. room으로
            }
        }

        binding.feat1Btn.setOnClickListener {
            input = binding.feat1Et.text.toString()
            if (input.isNotEmpty()) {
                featureOneViewModel.ingredientList.add(Ingredient(input))
                rvAdapter.notifyDataSetChanged()
            }
        }
        binding.feat1BtnTool.setOnClickListener {
            input = binding.feat1Et.text.toString()
            if (input.isNotEmpty()) {
                featureOneViewModel.toolList.add(Ingredient(input))
                toolAdapter.notifyDataSetChanged()
            }
        }
    }
}
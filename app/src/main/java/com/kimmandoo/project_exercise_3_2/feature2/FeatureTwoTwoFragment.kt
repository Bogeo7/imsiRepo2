package com.kimmandoo.project_exercise_3_2.feature2

import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
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
import com.kimmandoo.project_exercise_3_2.databinding.FragmentFeatureTwoTwoBinding


class FeatureTwoTwoFragment : Fragment() {
    private val serviceKey = "Cname"
    private var _binding: FragmentFeatureTwoTwoBinding? = null
    private val binding get() = _binding!!
    private val featureTwoViewModel by lazy { ViewModelProvider(this).get(FeatureTwoViewModel::class.java) }
    val listAdapter by lazy {ListAdapter(featureTwoViewModel.ingredientList)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeatureTwoTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.feat2Rv.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        featureTwoViewModel.retrofitList(listAdapter)
        featureTwoViewModel.retrofitExp()

        listAdapter.itemClick = object : ListAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
//                featureTwoViewModel.retrofitExp()
                val intent = Intent(context, ExpItemActivity::class.java)
                intent.putExtra("count",featureTwoViewModel.ingredientExp[2].count)
                intent.putExtra("exp",featureTwoViewModel.ingredientExp[2].expiration)
                startActivityForResult(intent, 0)
            }
        }
        listAdapter.minusClick = object : ListAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                featureTwoViewModel.ingredientList.removeAt(position)
                listAdapter.notifyItemRemoved(position)
                listAdapter.notifyItemRangeChanged(position, featureTwoViewModel.ingredientList.size);
            }
        }
        listAdapter.plusClick = object : ListAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(context, "Update 필요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            //refresh exp page
            featureTwoViewModel.retrofitExp()
            Log.d("resultCode", "refreshed")
        }
    }
}
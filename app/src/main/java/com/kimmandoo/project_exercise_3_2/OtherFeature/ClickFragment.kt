package com.kimmandoo.project_exercise_3_2.OtherFeature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kimmandoo.project_exercise_3_2.databinding.RecyclerClickFragmentBinding

class ClickFragment : Fragment(){
    private var _binding: RecyclerClickFragmentBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecyclerClickFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}
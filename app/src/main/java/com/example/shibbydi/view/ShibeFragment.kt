package com.example.shibbydi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shibbydi.adapter.ShibeAdapter
import com.example.shibbydi.databinding.FragmentShibeBinding
import com.example.shibbydi.utils.Resource
import com.example.shibbydi.viewmodel.ShibeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShibeFragment : Fragment() {

    private var _binding: FragmentShibeBinding? = null
    private val binding: FragmentShibeBinding get() = _binding!!
    private val viewModel : ShibeViewModel by viewModels()
    private lateinit var adapter: ShibeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShibeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ShibeAdapter()

        binding.shibeRv.apply {
            adapter = this@ShibeFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.shibes.observe(viewLifecycleOwner) { resource ->
//            (resource is Resource.Loading)
            when(resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    adapter.loadShibes(resource.data)
                }
                is Resource.Error -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = ShibeFragment::class.java.name
    }
}
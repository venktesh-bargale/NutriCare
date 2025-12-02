package com.nutricare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nutricare.R
import com.nutricare.databinding.FragmentDashboardBinding
import com.nutricare.viewmodel.DashboardViewModel
import com.nutricare.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUI()
        loadData()
    }

    private fun setupObservers() {
        dashboardViewModel.totalCalories.observe(viewLifecycleOwner, Observer { calories ->
            binding.tvCalories.text = String.format("%.0f", calories)
        })

        dashboardViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        dashboardViewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            if (error.isNotEmpty()) {
                showErrorMessage(error)
            }
        })

        sharedViewModel.currentUser.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                loadData()
            }
        })
    }

    private fun setupUI() {
        binding.btnRefresh.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        sharedViewModel.currentUser.value?.let { user ->
            dashboardViewModel.loadTodayData(user.userId)
        }
    }

    private fun showErrorMessage(message: String) {
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

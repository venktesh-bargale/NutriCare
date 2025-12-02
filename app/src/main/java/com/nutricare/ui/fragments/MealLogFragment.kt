package com.nutricare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nutricare.R
import com.nutricare.databinding.FragmentMealLogBinding
import com.nutricare.viewmodel.MealLogViewModel
import com.nutricare.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealLogFragment : Fragment() {

    private var _binding: FragmentMealLogBinding? = null
    private val binding get() = _binding!!

    private val mealLogViewModel: MealLogViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupUI()
        loadData()
    }

    private fun setupRecyclerView() {
        binding.rvMeals.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        mealLogViewModel.meals.observe(viewLifecycleOwner, Observer { meals ->
            updateMealsList(meals)
        })

        mealLogViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        mealLogViewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            if (error.isNotEmpty()) {
                showErrorMessage(error)
            }
        })

        mealLogViewModel.successMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message.isNotEmpty()) {
                showSuccessMessage(message)
            }
        })
    }

    private fun setupUI() {
        binding.btnAddMeal.setOnClickListener {
            // Navigate to add meal screen
        }

        binding.btnRefresh.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        sharedViewModel.currentUser.value?.let { user ->
            mealLogViewModel.loadMealsByDate(user.userId)
        }
    }

    private fun updateMealsList(meals: List<Any>) {
        // TODO: Update with actual adapter
        if (meals.isEmpty()) {
            binding.tvEmptyState.visibility = View.VISIBLE
            binding.rvMeals.visibility = View.GONE
        } else {
            binding.tvEmptyState.visibility = View.GONE
            binding.rvMeals.visibility = View.VISIBLE
        }
    }

    private fun showErrorMessage(message: String) {
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = message
    }

    private fun showSuccessMessage(message: String) {
        // Show toast or snackbar
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

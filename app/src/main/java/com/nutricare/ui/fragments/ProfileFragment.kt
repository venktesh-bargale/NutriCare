package com.nutricare.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nutricare.R
import com.nutricare.databinding.FragmentProfileBinding
import com.nutricare.data.models.User
import com.nutricare.viewmodel.UserViewModel
import com.nutricare.viewmodel.SharedViewModel
import com.nutricare.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUI()
        loadUserData()
    }

    private fun setupObservers() {
        userViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                displayUserData(user)
            }
        })

        userViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        userViewModel.successMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message.isNotEmpty()) {
                showSuccessMessage(message)
            }
        })

        userViewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            if (error.isNotEmpty()) {
                showErrorMessage(error)
            }
        })
    }

    private fun setupUI() {
        binding.btnSaveProfile.setOnClickListener {
            saveProfileData()
        }

        binding.btnLogout.setOnClickListener {
            // Handle logout
        }
    }

    private fun loadUserData() {
        sharedViewModel.currentUser.value?.let { user ->
            userViewModel.loadUser(user.userId)
        }
    }

    private fun displayUserData(user: User) {
        binding.etName.setText(user.name)
        binding.etAge.setText(user.age.toString())
        binding.etWeight.setText(user.weight.toString())
        binding.etHeight.setText(user.height.toString())
    }

    private fun saveProfileData() {
        val name = binding.etName.text.toString()
        val age = binding.etAge.text.toString().toIntOrNull() ?: 0
        val weight = binding.etWeight.text.toString().toDoubleOrNull() ?: 0.0
        val height = binding.etHeight.text.toString().toDoubleOrNull() ?: 0.0

        val updatedUser = User(
            userId = sharedViewModel.currentUser.value?.userId ?: "",
            name = name,
            age = age,
            weight = weight,
            height = height,
            updatedAt = System.currentTimeMillis()
        )

        userViewModel.createOrUpdateUser(updatedUser)
    }

    private fun showSuccessMessage(message: String) {
        binding.tvMessage.visibility = View.VISIBLE
        binding.tvMessage.text = message
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

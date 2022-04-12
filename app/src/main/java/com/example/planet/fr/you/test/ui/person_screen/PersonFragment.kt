package com.example.planet.fr.you.test.ui.person_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.planet.fr.you.test.R
import com.example.planet.fr.you.test.databinding.FragmentPersonBinding
import com.example.planet.fr.you.test.model.Person
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class PersonFragment : Fragment(R.layout.fragment_person) {
    companion object {
        const val PERSON_ID = "person_id"
    }

    private val viewModel: PersonViewModel by viewModel()
    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentPerson: Person


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPersonBinding.bind(view)
        initUI()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.cancelBtn.setOnClickListener { findNavController().popBackStack() }
        binding.deleteBtn.setOnClickListener {
            val isDeleted = lifecycleScope.async {
                viewModel.deletePerson(requireArguments().getInt(PERSON_ID))
            }
            lifecycleScope.launch {
                if (isDeleted.await()) {
                    Toast.makeText(
                        requireContext(),
                        "Person ${currentPerson.lastName} deleted from local BD",
                        Toast.LENGTH_SHORT
                    ).show()
                    Timber.d("Person ${currentPerson.lastName} deleted from local BD")
                }
            }
            findNavController().popBackStack()
        }
        binding.saveBtn.setOnClickListener {
            val person =
                currentPerson.copy(
                    firstName = binding.firstNameEt.text.toString(),
                    lastName = binding.lastNameEt.text.toString(),
                    email = binding.emailEt.text.toString()
                )
            val isUpdated = lifecycleScope.async {
                viewModel.updatePerson(person)
            }
            lifecycleScope.launch {
                if (isUpdated.await()) {
                    Toast.makeText(
                        requireContext(),
                        "Person ${currentPerson.lastName} updated",
                        Toast.LENGTH_SHORT
                    ).show()
                    Timber.d("Person ${currentPerson.lastName} updated")
                }
            }
            findNavController().popBackStack()
        }
    }

    private fun initUI() {
        viewModel.loadPerson(requireArguments().getInt(PERSON_ID)).observe(viewLifecycleOwner) {
            currentPerson = it
            Glide.with(requireContext())
                .load(it.avatarPath)
                .centerCrop()
                .into(binding.avatarIv)
            binding.firstNameEt.setText(it.firstName)
            binding.lastNameEt.setText(it.lastName)
            binding.emailEt.setText(it.email)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
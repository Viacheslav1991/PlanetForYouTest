package com.example.planet.fr.you.test.ui.person_list_screen

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planet.fr.you.test.R
import com.example.planet.fr.you.test.databinding.FragmentPersonListBinding
import com.example.planet.fr.you.test.ui.person_screen.PersonFragment.Companion.PERSON_ID
import org.koin.androidx.viewmodel.ext.android.viewModel


class PersonListFragment : Fragment(R.layout.fragment_person_list) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonListAdapter
    private var _binding: FragmentPersonListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PersonListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPersonListBinding.bind(view)

        setUpRecyclerView()
        setUpAdapter()
        observeListEvent()
        observePersons()
    }

    private fun observePersons() {
        viewModel.persons.observe(viewLifecycleOwner) {
            adapter.submitList(it?.map { person -> PersonListItem.fromPerson(person!!) })
        }
    }


    private fun observeListEvent() {
        adapter.event.observe(viewLifecycleOwner) {
            findNavController().navigate(
                R.id.action_personListFragment_to_personFragment,
                bundleOf(PERSON_ID to it.personId)
            )
        }
    }

    private fun setUpRecyclerView() {
        recyclerView = binding.personsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setUpAdapter() {
        adapter = PersonListAdapter()
        recyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
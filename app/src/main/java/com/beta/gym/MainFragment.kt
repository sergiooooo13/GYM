package com.beta.gym

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Setup the RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ItemAdapter(createItemList())
        recyclerView.adapter = adapter

        // Setup the FAB to navigate to AddCronoFragment
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addCronoFragment)
        }

        return view
    }

    // Function to create a list of items
    private fun createItemList(): List<Item> {
        // Here you can create your list of items with the desired properties
        val itemList = mutableListOf<Item>()
        itemList.add(Item("Item 1", "01-06-2024", 1, "08:00", "12:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        itemList.add(Item("Item 2", "02-06-2024", 2, "09:00", "13:00"))
        // Add more items as needed
        return itemList
    }
}

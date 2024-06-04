package com.beta.gym

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Handler
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddCronoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_add_crono, container, false)



        // Setup the FAB to navigate to AddCronoFragment
        val btnAdd: FloatingActionButton = view.findViewById(R.id.btnPlay)
        btnAdd.setOnClickListener {
            btnAdd.visibility = View.GONE
        }

        return view
    }

}

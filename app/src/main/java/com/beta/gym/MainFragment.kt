package com.beta.gym

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.beta.gym.BBDD.AppDatabase
import com.beta.gym.BBDD.Crono
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var database: AppDatabase
    private lateinit var listItemsCrono: List<Crono>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        database = Room.databaseBuilder(
            requireContext(), AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()

        // Carregar os dados do banco de dados em uma corrotina
        lifecycleScope.launch {
            listItemsCrono = database.cronoDao.getAllUsers()

            // Calcular el número máximo de semana y su frecuencia
            val maxWeekData = listItemsCrono.groupingBy { it.week }.eachCount()
            val maxWeek = maxWeekData.maxByOrNull { it.value }?.key ?: 0
            val maxWeekCount = maxWeekData[maxWeek] ?: 0

            // Sumar 1 si el número máximo de semana aparece 6 veces
            val finalWeek = if (maxWeekCount == 6) maxWeek + 1 else maxWeek

            // Atualizar o RecyclerView com os dados
            adapter = ItemAdapter(listItemsCrono)
            recyclerView.adapter = adapter

            // Setup the FAB to navigate to AddCronoFragment
            val btnAdd: FloatingActionButton = view.findViewById(R.id.btnAdd)
            btnAdd.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("Semana", finalWeek) // Usar el valor final
                findNavController().navigate(R.id.action_mainFragment_to_addCronoFragment, bundle)
            }
        }

        // Setup the RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }


}

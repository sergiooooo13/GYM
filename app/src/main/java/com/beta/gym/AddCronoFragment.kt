package com.beta.gym

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Handler
import androidx.viewpager2.widget.ViewPager2

class AddCronoFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private val timerHandler = Handler()
    private val timerRunnable = object : Runnable {
        override fun run() {
            // Cambiar al siguiente texto en el ViewPager
            viewPager.currentItem = (viewPager.currentItem + 1) % 13
            // Repetir cada 10 segundos (10000 milisegundos)
            timerHandler.postDelayed(this, 10000)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_add_crono, container, false)
        // Inicializar ViewPager
        viewPager = view.findViewById(R.id.viewPager)
        // Configurar adaptador
        val adapter = TextSliderAdapter(getTexts())
        viewPager.adapter = adapter
        // Iniciar temporizador
        timerHandler.postDelayed(timerRunnable, 10000)
        return view
    }

    // Método para obtener los textos intercalados
    private fun getTexts(): List<String> {
        val texts = mutableListOf<String>()
        repeat(7) {
            texts.add("Descanso")
            texts.add("Correr")
        }
        texts.add("Descanso") // Añadir un último descanso
        return texts
    }

    override fun onDestroyView() {
        // Detener el temporizador cuando se destruya la vista
        timerHandler.removeCallbacks(timerRunnable)
        super.onDestroyView()
    }
}

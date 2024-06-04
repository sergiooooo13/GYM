package com.beta.gym

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.beta.gym.BBDD.AppDatabase
import com.beta.gym.BBDD.Crono
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.util.Locale

class AddCronoFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var items: MutableList<CarouselItem>
    private lateinit var adapter: CarouselAdapter
    private var semana: Int = 0
    private var currentItemIndex = 0
    private var currentTimer: CountDownTimer? = null
    private lateinit var database: AppDatabase
    private lateinit var tiempos: CronoTiempos
    private lateinit var vibrator: Vibrator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_add_crono, container, false)
        semana =
            arguments?.getInt("Semana", 0) ?: 0 // El valor predeterminado es 0 si no se proporciona

        // Usa el valor de "semana" según tus necesidades
        tiempos = when (semana) {
            0 -> CronoTiempos(30000L, 150000L)
            1 -> CronoTiempos(45000L, 135000L)
            2 -> CronoTiempos(60000L, 120000L)
            3 -> CronoTiempos(75000L, 105000L)
            4 -> CronoTiempos(90000L, 90000L)
            5 -> CronoTiempos(105000L, 75000L)
            6 -> CronoTiempos(120000L, 60000L)
            7 -> CronoTiempos(135000L, 45000L)
            8 -> CronoTiempos(150000L, 30000L)
            9 -> CronoTiempos(165000L, 15000L)
            else -> CronoTiempos(0L, 0L)
        }

        // Inicializar ViewPager2
        viewPager = view.findViewById(R.id.viewPager)
        items = mutableListOf(
            CarouselItem("Descanso", "05:00")
        )
        for (i in 1..6) {
            items.add(CarouselItem("Correr (${i}/6)", convertirALaHora(tiempos.correr)))
            items.add(CarouselItem("Descansar (${i}/6)", convertirALaHora(tiempos.descansar)))
        }
        adapter = CarouselAdapter(items.toList())
        viewPager.adapter = adapter

        // Setup the FAB to navigate to AddCronoFragment
        val btnPlay: FloatingActionButton = view.findViewById(R.id.btnPlay)
        btnPlay.setOnClickListener {
            btnPlay.visibility = View.GONE
            startNextTimer()
        }

        // Initialize vibrator
        vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        return view
    }

    private fun startNextTimer() {
        if (currentItemIndex >= items.size) {
            Toast.makeText(context, "¡Todos los intervalos completados!", Toast.LENGTH_SHORT).show()
            return
        }

        val currentItem = items[currentItemIndex]
        val totalMillis = convertirFechaLong(currentItem)
        currentTimer = object : CountDownTimer(totalMillis, 1000) {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                val updatedTime = String.format(
                    Locale.US,
                    "%02d:%02d",
                    secondsRemaining / 60,
                    secondsRemaining % 60
                )
                currentItem.time = updatedTime
                adapter.notifyItemChanged(currentItemIndex)



                // Vibrate when 3 and 1 seconds left
                if (secondsRemaining == 3L || secondsRemaining == 1L) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                }
            }


            override fun onFinish() {
                currentItem.time = "00:00"
                currentItemIndex++
                adapter.notifyItemChanged(currentItemIndex)
                if (currentItemIndex < items.size) {
                    viewPager.currentItem = currentItemIndex
                    startNextTimer()
                } else {
                    añadirCronoBBDD(semana)
                    Toast.makeText(
                        context,
                        "¡Todos los intervalos completados!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentTimer?.cancel()
    }

    private fun convertirFechaLong(currentItem: CarouselItem): Long{
        val timeParts = currentItem.time.split(":")
        val minutes = timeParts[0].toLong()
        val seconds = timeParts[1].toLong()
        return (minutes * 60 + seconds) * 1000

    }

    private fun añadirCronoBBDD(semanaRecibida: Int) {
        database = Room.databaseBuilder(
            requireContext(), AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
        lifecycleScope.launch {
            database.cronoDao.insert(Crono(date = System.currentTimeMillis(), week = semanaRecibida, runTime = tiempos.correr, restTime = tiempos.descansar))
        }
    }

    fun convertirALaHora(data: Long): String {
        val segundos = Math.round((data / 1000.0) % 60.0).toInt()
        val minutos = (data / 1000 / 60).toInt()
        return String.format(Locale.US, "%02d:%02d", minutos, segundos)
    }
}

class CronoTiempos(val correr: Long, val descansar: Long)

package com.rasil.viewmodel1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rasil.viewmodel1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        CoroutineScope(IO).launch {
//
//            // Parallel
//            Log.i("TAGIS: ", "Started coroutine")
//
//            val stock1 = async { getStock1() }
//            val stock2 = async { getStock2() }
//            val total = stock1.await() + stock2.await()
//            Log.i("TAGIS :", "Total value is $total")
//
//            // Linear
////            val stock1 = getStock1()
////            val stock2 = getStock2()
////            val total = stock1 + stock2
////            Log.i("TAGIS :", "Total value is $total")
//        }


        CoroutineScope(Main).launch {

            // Parallel
            Log.i("TAGIS: ", "Started coroutine")

            val stock1 = async(IO) { getStock1() }
            val stock2 = async(IO) { getStock2() }
            val total = stock1.await() + stock2.await()
            Toast.makeText(this@MainActivity, "TOtal is $total", Toast.LENGTH_SHORT).show()
            Log.i("TAGIS :", "Total value is $total")

            // Linear
//            val stock1 = getStock1()
//            val stock2 = getStock2()
//            val total = stock1 + stock2
//            Log.i("TAGIS :", "Total value is $total")
        }
    }

    private suspend fun getStock1(): Int {
        delay(10000)
        Log.i("TAGIS: ", "get Stock 1")
        return 5000
    }

    private suspend fun getStock2(): Int {
        delay(8000)
        Log.i("TAGIS: ", "get Stock 2")
        return 5000
    }
}
package com.rasil.viewmodel1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rasil.viewmodel1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnCount.setOnClickListener {
            binding.countTV.text = count++.toString()
        }

        binding.downloadUserDataButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                binding.userMessageTV.text = UserDataManager().getTotalUserAccount().toString()
                binding.userMessageTV.text = StructuredUserDataManager().getTotalUserAccount().toString()
            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {

            withContext(Dispatchers.Main) {
//            Log.i( "download","Downloading User $i in ${Thread.currentThread().name}")
                binding.userMessageTV.text = "Downloading User $i in ${Thread.currentThread().name}"
            }
        }
    }
}
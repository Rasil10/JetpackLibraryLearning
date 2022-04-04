package com.rasil.viewmodel1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rasil.viewmodel1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelFactory = MainActivityViewModelFactory(100)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        with(binding) {
            answerTV.text = viewModel.getTotal().toString()
            addButton.setOnClickListener {
                if (!numberET.text.toString().isNullOrEmpty()) {
                    viewModel.setTotal(numberET.text.toString().toInt())
                    answerTV.text = viewModel.getTotal().toString()
                }
            }
        }
    }
}
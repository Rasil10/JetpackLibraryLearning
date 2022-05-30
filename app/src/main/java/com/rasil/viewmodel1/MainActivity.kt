package com.rasil.viewmodel1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rasil.viewmodel1.databinding.ActivityMainBinding
import com.rasil.viewmodel1.db.Subscriber
import com.rasil.viewmodel1.db.SubsriberDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subsriberViewModel: SubscriberViewModel
    private lateinit var adapter: SubsriberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubsriberDatabase.getInstance(applicationContext).subsriberDAO
        val repository = SubsriberRepository(dao)

        val factory = SubsriberViewModelFactory(repository)
        subsriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)

        binding.myViewModel = subsriberViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        subsriberViewModel.message.observe(this, {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this@MainActivity, "${it}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        binding.subriberRV.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = SubsriberAdapter({ selectedItem: Subscriber -> listItemClicked(selectedItem) })
        binding.subriberRV.adapter = adapter
        displaySubsriberList()
    }

    private fun displaySubsriberList() {
        subsriberViewModel.subscribers.observe(this, {
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber: Subscriber) {
        subsriberViewModel.initUpdateOrDelete(subscriber)
    }


}
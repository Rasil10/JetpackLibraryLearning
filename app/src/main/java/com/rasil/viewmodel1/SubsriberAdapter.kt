package com.rasil.viewmodel1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rasil.viewmodel1.databinding.ItemSubscriberBinding
import com.rasil.viewmodel1.db.Subscriber

class SubsriberAdapter(
    private val clickListener: (Subscriber) -> Unit
) :
    RecyclerView.Adapter<SubsriberAdapter.ViewHolder>() {

    private val subsribers = ArrayList<Subscriber>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemSubscriberBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_subscriber, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(subsribers[position], clickListener)
    }

    override fun getItemCount(): Int = subsribers.size

    fun setData(newList: List<Subscriber>) {
        subsribers.clear()
        subsribers.addAll(newList)
    }

    class ViewHolder(val binding: ItemSubscriberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
            binding.subsriberNameTV.text = subscriber.name
            binding.subsriberEmailTV.text = subscriber.email
            binding.cardLayout.setOnClickListener {
                clickListener(subscriber)
            }
        }
    }

}
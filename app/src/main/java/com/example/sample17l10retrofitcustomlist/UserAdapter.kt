package com.example.sample17l10retrofitcustomlist

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sample17l10retrofitcustomlist.databinding.ItemUserBinding

class UserAdapter : ListAdapter<User, UserViewHolder>() {
}

class UserViewHolder(
    private val binding: ItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: User) {

    }
}
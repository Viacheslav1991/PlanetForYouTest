package com.example.planet.fr.you.test.ui.person_list_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.planet.fr.you.test.R
import com.example.planet.fr.you.test.databinding.ItemPersonBinding

class PersonListAdapter(
    val event: MutableLiveData<PersonListEvent> = MutableLiveData(),
) : ListAdapter<PersonListItem, PersonViewHolder>(PersonDiffCallbacks()) {

    private lateinit var binding: ItemPersonBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        binding =
            ItemPersonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position).let { holder.onBind(it, event) }
    }
}

class PersonViewHolder(private val binding: ItemPersonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
    }

    fun onBind(
        item: PersonListItem,
        event: MutableLiveData<PersonListEvent>
    ) {
        binding.firstNameTv.text = item.firstName
        binding.lastNameTv.text = item.lastName
        Glide.with(itemView.context)
            .load(item.avatarPath)
            .apply(imageOption)
            .centerCrop()
            .into(binding.avatarIv)

        binding.root.setOnClickListener { event.postValue(PersonListEvent.OnClick(item.id)) }
    }
}

private class PersonDiffCallbacks : DiffUtil.ItemCallback<PersonListItem>() {
    override fun areItemsTheSame(
        oldItem: PersonListItem,
        newItem: PersonListItem
    ): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(
        oldItem: PersonListItem,
        newItem: PersonListItem
    ): Boolean =
        (oldItem == newItem)
}
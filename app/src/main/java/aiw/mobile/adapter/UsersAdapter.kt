package aiw.mobile.submissionawalulang.adapter

import aiw.mobile.Api.DataItem
import aiw.mobile.suitmediatest.databinding.ItemRvBinding
import aiw.mobile.suitmediatest.setImageUrl
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter(private val onItemClick: (DataItem) -> Unit) : ListAdapter<DataItem, UsersAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem, onItemClick: (DataItem) -> Unit) {
            binding.tvFirstName.text = user.firstName
            binding.tvLastName.text = user.lastName
            binding.tvEmail.text = user.email
            binding.ivProfile.setImageUrl(user.avatar)

            binding.root.setOnClickListener {
                onItemClick(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user, onItemClick)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}

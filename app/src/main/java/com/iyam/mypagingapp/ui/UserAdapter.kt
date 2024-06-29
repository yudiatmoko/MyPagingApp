package com.iyam.mypagingapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.iyam.mypagingapp.databinding.UserItemLayoutBinding
import com.iyam.mypagingapp.model.User

/*
Hi, Code Enthusiast!
https://github.com/yudiatmoko
*/

class UserAdapter : PagingDataAdapter<User, UserAdapter.ImageViewHolder>(diffCallback) {

    inner class ImageViewHolder(
        val binding: UserItemLayoutBinding
    ) : ViewHolder(binding.root)

    companion object {
        val diffCallback = object :
            DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onBindViewHolder(
        holder: ImageViewHolder,
        position: Int
    ) {
        val currentPosition = getItem(position)

        holder.binding.apply {
            holder.itemView.apply {
                val name = listOf(currentPosition?.firstName, currentPosition?.lastName)
                val fullName = name.joinToString(" ")
                tvUserFullname.text = fullName
                val imageLink = currentPosition?.avatar
                sivUserImg.load(imageLink) {
                    crossfade(true)
                    crossfade(1000)
                }
                tvUserEmail.text = currentPosition?.email
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        return ImageViewHolder(
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

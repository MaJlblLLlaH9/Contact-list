package com.example.contactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.databinding.ItemUserBinding
import com.example.contactlist.model.User

interface UserActionListener{
    fun onUserDelete(user: User){

    }
}

class UsersAdapter (
    private val actionListener: UserActionListener
        ): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(), View.OnClickListener {

    var users: List<User> = emptyList()

    override fun onClick(view: View) {
        val user = view.tag as User
        when (view.id) {
            R.id.deleteButtonView -> {
                deleteUser(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.deleteButtonView.setOnClickListener(this)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            holder.itemView.tag = user
            deleteButtonView.tag = user

            userNameTextView.text = user.name
            userAboutTextView.text = user.aboutMe

        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    private fun deleteUser(view: View) {
        val user = view.tag as User
        actionListener.onUserDelete(user)
    }

    class UsersViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

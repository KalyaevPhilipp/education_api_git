package ru.philipp_kalyaev.android.education_api_git.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import ru.philipp_kalyaev.android.education_api_git.R
import ru.philipp_kalyaev.android.education_api_git.databinding.UserItemListBinding

class UserListAdapter(
    private var callbacks: Callbacks,
    val users: MutableList<User> = mutableListOf(),
) : RecyclerView.Adapter<UserListAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserHolder(
            binding = UserItemListBinding.inflate(layoutInflater, parent, false),
            callbacks = callbacks,
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    class UserHolder(
        private val binding: UserItemListBinding,
        private val callbacks: Callbacks,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.userName.text = user.userName
            //binding.eachUserImage.load(user.userImage)
            Picasso.get()
                .load(user.userImage)
                .resize(300, 300)
                .transform(CropCircleTransformation())
                .into(binding.eachUserImage)

            itemView.setOnClickListener {
                callbacks.onUserSelected(user)
            }
        }
    }
}
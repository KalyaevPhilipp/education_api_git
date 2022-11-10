package ru.philipp_kalyaev.android.education_api_git.UI

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import ru.philipp_kalyaev.android.education_api_git.R

class DetailsFragmentList : Fragment() {

    interface Callbacks {
        fun onUserSelected(userId: String)
    }

    private var callbacks: Callbacks? = null
    private var adapter: UserListAdapter? = UserListAdapter(emptyList())
    private lateinit var userRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }


    private inner class UserListAdapter(val users: List<User>) :
        RecyclerView.Adapter<UserHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
            val view = layoutInflater.inflate(R.layout.detail_fragment_list, parent, false)
            return UserHolder(view)
        }

        override fun getItemCount()=5

        override fun onBindViewHolder(holder: UserHolder, position: Int) {
            val user = users[position]
            holder.bind(user)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_fragment_list,container,false)
        userRecyclerView = view.findViewById(R.id.userContainer)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.adapter = adapter
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private inner class UserHolder(view: View) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var user: User
        private val userName: TextView = itemView.findViewById(R.id.userName)
        private val userImage: CircleImageView = itemView.findViewById(R.id.eachUserImage)

        init {
            itemView.setOnClickListener(this)
        }
        fun bind(user: User){
            userName.text = this.userName.toString();
            Picasso.get()
                .load(this.userImage.toString())
                .resize(100,100)
                .into(userImage)
            //userImage.src = "@mipmap/ic_launcher"
        }
        override fun onClick(v: View){
            callbacks?.onUserSelected("2")
        }
    }




}
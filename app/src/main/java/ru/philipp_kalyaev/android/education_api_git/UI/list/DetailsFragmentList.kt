package ru.philipp_kalyaev.android.education_api_git.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.philipp_kalyaev.android.education_api_git.R
import ru.philipp_kalyaev.android.education_api_git.databinding.DetailFragmentListBinding
import ru.philipp_kalyaev.android.education_api_git.ui.details.DetailsFragment
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.Callbacks
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.UserListAdapter

class DetailsFragmentList : Fragment(), Callbacks {

    private var _binding: DetailFragmentListBinding? = null

    private var adapter = UserListAdapter(callbacks = this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailFragmentListBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding!!.userRecyclerView.adapter = adapter
        _binding!!.userRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // todo test:
        adapter.users.clear()
        adapter.users.add(User("1", "tekkub", "https://avatars.githubusercontent.com/u/706?v=4"))
        adapter.users.add(User("2", "wonderchook", "https://avatars.githubusercontent.com/u/67992?v=4"))
        adapter.users.add(User("3", "name3", "url3"))

        adapter.notifyDataSetChanged()
    }

    override fun onUserSelected(user: User) {
        val fragment = DetailsFragment.newInstance(user)

        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
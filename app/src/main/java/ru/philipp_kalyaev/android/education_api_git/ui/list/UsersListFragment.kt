package ru.philipp_kalyaev.android.education_api_git.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.philipp_kalyaev.android.education_api_git.App
import ru.philipp_kalyaev.android.education_api_git.databinding.DetailFragmentListBinding
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.UserListAdapter

class UsersListFragment : Fragment() {

    private val viewModel_: UserListViewModel by viewModels {
        UserListViewModelFactory(
            (requireActivity().application as App).appComponent.provideUserListViewModel(),
        )
    }
    private var _binding: DetailFragmentListBinding? = null
    private val adapter by lazy { UserListAdapter(callbacks = viewModel_) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DetailFragmentListBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel_.userState.observe(viewLifecycleOwner) { state ->
            when (state) {
                UserListViewModel.State.Loading -> {
                    _binding!!.userRecyclerView.isInvisible = true
                    _binding!!.listProgressBar.isInvisible = false
                }
                is UserListViewModel.State.Success -> {
                    _binding!!.userRecyclerView.adapter = adapter
                    _binding!!.userRecyclerView.layoutManager =
                        LinearLayoutManager(requireContext())
                    adapter.users.clear()
                    adapter.users.addAll(state.users)
                    _binding!!.userRecyclerView.isInvisible = false
                    _binding!!.listProgressBar.isInvisible = true

                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
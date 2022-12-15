package ru.philipp_kalyaev.android.education_api_git.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import ru.philipp_kalyaev.android.education_api_git.App
import ru.philipp_kalyaev.android.education_api_git.databinding.DetailFragmentBinding
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User
import javax.inject.Inject

class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModelFactory(
            (requireActivity().application as App).appComponent.provideDetailsViewModel().create(requireArguments().getParcelable<User>(KEY_USER)!!)
        )
    }
    private var binding: DetailFragmentBinding? = null




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = requireArguments().getParcelable<User>(KEY_USER)!!
        renderUser(user)
        binding!!.progressBar.isVisible = false
        binding!!.container.isVisible = true
        binding!!.textViewName.setOnClickListener {
            viewModel.getSubscribers()
        }

        viewModel.userState.observe(viewLifecycleOwner) { state ->
            when (state) {
                DetailsViewModel.State.Loading -> {
                    binding!!.progressBar.isVisible = true
                    binding!!.container.isVisible = false
                }
                is DetailsViewModel.State.Success -> {
                    binding!!.progressBar.isVisible = false
                    binding!!.container.isVisible = true
                    Toast.makeText(view.context, state.users.size.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun renderUser(user: User) {

        binding!!.userNameResult.text = user.userName
        binding!!.userIdResult.text = user.userId
        // binding!!.detailImageAvatar.load(userImg)

        if (user.userImage.isNotBlank()) {
            Picasso.get().isLoggingEnabled = true;
            Picasso.get()
                .load(user.userImage)
                .transform(CropCircleTransformation())
                .resize(100, 100)
                .into(binding!!.detailImageAvatar)
        }
    }


    companion object {

        private const val KEY_USER = "KEY_USER"

        fun newInstance(user: User): DetailsFragment {
            return DetailsFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(KEY_USER, user)

                arguments = bundle
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
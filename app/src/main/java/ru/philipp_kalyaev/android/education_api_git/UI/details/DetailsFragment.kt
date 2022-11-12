package ru.philipp_kalyaev.android.education_api_git.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.squareup.picasso.Picasso
import ru.philipp_kalyaev.android.education_api_git.databinding.DetailFragmentBinding
import ru.philipp_kalyaev.android.education_api_git.ui.list.adapter.User

class DetailsFragment : Fragment() {

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
         val userName = arguments?.getString("KEY_USER_NAME")
         val userImg = arguments?.getString("KEY_USER_IMG")
         val userId =  arguments?.getString("KEY_USER_ID")

        binding!!.userNameResult.text = userName
        binding!!.userIdResult.text = userId
        binding!!.detailImageAvatar.load(userImg)
      /*  Picasso.get().isLoggingEnabled = true;
        Picasso.get()
            .load(userImg)
            .resize(100,100)
            .into(binding!!.detailImageAvatar)*/


    }

    companion object {

        fun newInstance(user: User): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(
                    "KEY_USER_ID" to user.userId,
                    "KEY_USER_IMG" to user.userImage,
                    "KEY_USER_NAME" to user.userName
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
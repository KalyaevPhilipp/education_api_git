package ru.philipp_kalyaev.android.education_api_git.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
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
        val user = requireArguments().getParcelable<User>(KEY_USER)!!

        binding!!.userNameResult.text = user.userName
        binding!!.userIdResult.text = user.userId
        // binding!!.detailImageAvatar.load(userImg)
        Picasso.get().isLoggingEnabled = true;
        Picasso.get()
            .load(user.userImage)
            .transform(CropCircleTransformation())
            .resize(100, 100)
            .into(binding!!.detailImageAvatar)
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
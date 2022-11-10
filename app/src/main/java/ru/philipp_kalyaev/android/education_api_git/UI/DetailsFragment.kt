package ru.philipp_kalyaev.android.education_api_git.UI

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import ru.philipp_kalyaev.android.education_api_git.databinding.DetailFragmentBinding

class DetailsFragment : Fragment() {
    private var binding: DetailFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        Picasso.get()
            .load("http://avatars.githubusercontent.com/u/706?v=4")
            .resize(100,100)
            .into(binding!!.imageView)
        return binding!!.root

    }
    companion object{
        fun newInstance(userId: String):DetailsFragment{
        return DetailsFragment().apply{}
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
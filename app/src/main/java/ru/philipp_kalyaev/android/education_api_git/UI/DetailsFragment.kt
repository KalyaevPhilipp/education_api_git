package ru.philipp_kalyaev.android.education_api_git.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.philipp_kalyaev.android.education_api_git.databinding.DetailFragmentBinding

class DetailsFragment : Fragment() {
    private var binding: DetailFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding!!.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
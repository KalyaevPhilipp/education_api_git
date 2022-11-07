package ru.philipp_kalyaev.android.education_api_git

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.philipp_kalyaev.android.education_api_git.UI.DetailsFragment
import ru.philipp_kalyaev.android.education_api_git.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val detailFrag = DetailsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, detailFrag)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

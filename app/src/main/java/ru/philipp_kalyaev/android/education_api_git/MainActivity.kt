package ru.philipp_kalyaev.android.education_api_git

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.philipp_kalyaev.android.education_api_git.databinding.ActivityMainBinding
import ru.philipp_kalyaev.android.education_api_git.ui.list.DetailsFragmentList

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailList = DetailsFragmentList()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, detailList)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

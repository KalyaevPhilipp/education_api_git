package ru.philipp_kalyaev.android.education_api_git

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import ru.philipp_kalyaev.android.education_api_git.UI.DetailsFragment
import ru.philipp_kalyaev.android.education_api_git.UI.DetailsFragmentList
import ru.philipp_kalyaev.android.education_api_git.UI.User
import ru.philipp_kalyaev.android.education_api_git.databinding.ActivityMainBinding
import ru.philipp_kalyaev.android.education_api_git.repo.RetrofitServices




class MainActivity : AppCompatActivity(),
    DetailsFragmentList.Callbacks{
    lateinit var rService: RetrofitServices
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailList = DetailsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, detailList)
            .addToBackStack(null)
            .commit()
    }
    override fun onUserSelected(userId: String){
        val fragment = DetailsFragment.newInstance(userId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun getUsers(){
        rService.getUserList()
    }
}

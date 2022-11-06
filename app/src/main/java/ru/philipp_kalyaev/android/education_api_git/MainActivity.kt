package ru.philipp_kalyaev.android.education_api_git

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.philipp_kalyaev.android.education_api_git.UI.DetailsFragment
import ru.philipp_kalyaev.android.education_api_git.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var getDetail: Button
    private lateinit var binding_main: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding_main.root)
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }
        getDetail = binding_main.getFragment
        val detailFrag: DetailsFragment = DetailsFragment()
        getDetail.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, detailFrag)
                .addToBackStack(null)
                .commit()
        }
    }

    /*
    getDetail.setOnClickListener {
        setContentView(binding_frag.root)
    }*/

}

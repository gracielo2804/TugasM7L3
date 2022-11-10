package com.example.tugasm7.ui.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tugasm7.R
import com.example.tugasm7.databinding.ActivityMuridBinding
import com.example.tugasm7.ui.fragment.MuridListFragment
import com.example.tugasm7.ui.fragment.MuridPilihFragment

class MuridActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMuridBinding
    var usernamelogin:String=""
    var namalogin:String=""

    lateinit var muridListFragment: MuridListFragment
    lateinit var muridPilihFragment: MuridPilihFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMuridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("usernamelogin")){
            usernamelogin=intent.getStringExtra("usernamelogin").toString()
            namalogin=intent.getStringExtra("namalogin").toString()
        }

        muridListFragment= MuridListFragment(usernamelogin,namalogin)
        muridPilihFragment=MuridPilihFragment(usernamelogin,namalogin)

        binding.txtWelcomeMurid.text="Selamat Datang $namalogin !"

        val navView: BottomNavigationView = binding.navView
        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_kelas_murid ->{
                    //ToDo Do Something
                    true
                }
                R.id.item_pilih_murid ->{
                    //ToDo Do Something
                    true
                }
                else -> {
                    true
                }
            }
        }

    }
}
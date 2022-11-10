package com.example.tugasm7.ui.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tugasm7.R
import com.example.tugasm7.databinding.ActivityPengajarBinding
import com.example.tugasm7.ui.fragment.MuridListFragment
import com.example.tugasm7.ui.fragment.MuridPilihFragment
import com.example.tugasm7.ui.fragment.PengajarAddFragment
import com.example.tugasm7.ui.fragment.PengajarListFragment

class PengajarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengajarBinding
    var usernamelogin:String=""
    var namalogin:String=""

    lateinit var pengajarAddFragment: PengajarAddFragment
    lateinit var pengajarListFragment: PengajarListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPengajarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("usernamelogin")){
            usernamelogin=intent.getStringExtra("usernamelogin").toString()
            namalogin=intent.getStringExtra("namalogin").toString()
        }

        pengajarAddFragment=PengajarAddFragment(usernamelogin,namalogin)
        pengajarListFragment=PengajarListFragment(usernamelogin,namalogin)

        binding.txtWelcomePengajar.text="Selamat Datang $namalogin !"
        val navView: BottomNavigationView = binding.navView

        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.item_kelas_pengajar ->{
                    //ToDo Do Something
                    true
                }
                R.id.item_tambah_pengajar ->{
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
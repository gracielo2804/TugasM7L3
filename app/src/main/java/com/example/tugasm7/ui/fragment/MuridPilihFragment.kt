package com.example.tugasm7.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tugasm7.R
import com.example.tugasm7.database.AppDatabase
import com.example.tugasm7.database.DBDao
import com.example.tugasm7.database.entity.AmbilKelasEntity
import com.example.tugasm7.database.entity.KelasEntity
import com.example.tugasm7.database.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MuridPilihFragment(usernamelogin:String,namalogin:String) : Fragment() {

    private val coroutine = CoroutineScope(Dispatchers.IO)
    private lateinit var db: AppDatabase
    private lateinit var dao: DBDao

    private var listUser= arrayListOf<UserEntity>()
    private var listKelas= arrayListOf<KelasEntity>()
    private var listAmbilKelas= arrayListOf<AmbilKelasEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pengajar_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
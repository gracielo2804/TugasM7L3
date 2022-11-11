package com.example.tugasm7.ui

import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasm7.R
import com.example.tugasm7.database.entity.AmbilKelasEntity
import com.example.tugasm7.database.entity.KelasEntity
import com.example.tugasm7.databinding.ItemListRecyclerviewBinding
import java.text.DecimalFormat
import java.text.NumberFormat


class RecyclerviewAdapter(val callback:AdapterCallback): RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>(),View.OnCreateContextMenuListener {

    private var listKelas = ArrayList<KelasEntity>()
    private var listAmbilKelas = ArrayList<AmbilKelasEntity>()
    private var tipe=0

    fun refreshDataListKelas(listKelasKirim:ArrayList<KelasEntity>){

        listKelas.clear()
        listKelas.addAll(listKelasKirim)
        notifyDataSetChanged()
    }
    fun refreshDataListAmbilKelas(listAmbilKelasKirim:ArrayList<AmbilKelasEntity>){
        listAmbilKelas.clear()
        listAmbilKelas.addAll(listAmbilKelasKirim)
        notifyDataSetChanged()
    }

    fun refreshTipe(tipeKirim:Int){
        tipe=tipeKirim
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)  {
        private val binding = ItemListRecyclerviewBinding.bind(view)
        fun bind(dataKelas:KelasEntity){
            if(tipe==TYPE_PILIH_MURID){

            }
            else if(tipe== TYPE_LIST_MURID){
                val formatter: NumberFormat = DecimalFormat("#.###,00")
                val number = dataKelas.harga
                val formattedNumber: String = formatter.format(number)
                binding.txtJumlahMurid.visibility=View.GONE
                binding.txtHarga.text="Rp. $formattedNumber"
                var countjml=0
                for (i in listAmbilKelas){
                    if (i.idKelas==dataKelas.id)countjml++
                }
                binding.txtPengajar.text="oleh : ${dataKelas.namapengajar}"
                binding.txtNamaKelas.text=dataKelas.name

            }
            else if( tipe==TYPE_LIST_PENGAJAR){
                val formatter: NumberFormat = DecimalFormat("#.###")
                val number = dataKelas.harga
                val formattedNumber: String = formatter.format(number)
                binding.txtPengajar.visibility=View.GONE
                binding.txtStatus.visibility=View.GONE
                binding.txtHarga.text="Rp. $formattedNumber"
                var countjml=0
                for (i in listAmbilKelas){
                    if (i.idKelas==dataKelas.id)countjml++
                }

                binding.txtJumlahMurid.text="Jumlah Murid : $countjml"
                binding.txtNamaKelas.text=dataKelas.name
                view.setOnClickListener {
                    Log.e("cek click pengajar atas", "masuk long click tipe - $tipe")
                    val popup = PopupMenu(it.context, binding.txtHarga)
                    popup.inflate(R.menu.menu_pengajar)
                    //adding click listener
                    popup.setOnMenuItemClickListener {
                        when (it.getItemId()) {
                            R.id.item_pengajar_delete ->
                                //handle menu1 click
                                callback.onListPengajarLongClicked("delete", dataKelas)
                            R.id.item_pengajar_edit ->                         //handle menu2 click
                                callback.onListPengajarLongClicked("edit", dataKelas)
                            else -> false
                        }
                        true
                    }
                    //displaying the popup
                    //displaying the popup
                    popup.show()
                    true
                }
                true
            }
        }
    }


    companion object {
        const val TYPE_PILIH_MURID = 0
        const val TYPE_LIST_MURID = 1
        const val TYPE_LIST_PENGAJAR= 2

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_recyclerview, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listKelas[position])
//        holder.view.setOnClickListener {
//            Log.e("cek click pengajar bawah","masuk long click tipe - $tipe")
//            val popup = PopupMenu(it.context, it)
//            popup.inflate(R.menu.menu_pengajar)
//            //adding click listener
//            popup.setOnMenuItemClickListener {
//                when (it.getItemId()) {
//                    R.id.item_pengajar_delete ->
//                        //handle menu1 click
//                        callback.onListPengajarLongClicked("delete",listKelas[position])
//                    R.id.item_pengajar_edit ->                         //handle menu2 click
//                        callback.onListPengajarLongClicked("edit",listKelas[position])
//                    else -> false
//                }
//                true
//            }
//            //displaying the popup
//            //displaying the popup
//            popup.show()
//
//        }
    }

    override fun getItemCount(): Int {
        if(tipe==TYPE_PILIH_MURID || tipe==TYPE_LIST_PENGAJAR){
            return listKelas.size
        } else if(tipe== TYPE_LIST_MURID){
            return listAmbilKelas.size
        }
        else{
            return 0
        }
    }

    interface AdapterCallback {
        fun onListPengajarLongClicked(tipe:String,kelas:KelasEntity)
        fun onUserPilihLongClicked(kelas: KelasEntity)
        fun onUserListLongClicked(ambilKelasEntity: AmbilKelasEntity)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {
        menu.add(0, v.id, 0, "Delete")
        menu.add(0, v.id, 0, "Edit")
    }
}


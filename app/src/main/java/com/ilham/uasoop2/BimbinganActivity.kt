package com.ilham.uasoop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilham.uasoop2.database.BimbinganRomDB
import com.ilham.uasoop2.model.Bimbingan
import kotlinx.android.synthetic.main.activity_bimbingan.floatingActionButton
import kotlinx.android.synthetic.main.activity_bimbingan.recycler_view_main
import kotlinx.android.synthetic.main.activity_bimbingan.text_view_note_empty


class BimbinganActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bimbingan)

        getBimbingansData()

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, UbahBimbinganActivity::class.java))
        }
    }

    private fun getBimbingansData(){
        val database = BimbinganRomDB.getDatabase(applicationContext)
        val dao = database.getBimbinganDao()
        val listItems = arrayListOf<Bimbingan>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            text_view_note_empty.visibility = View.GONE
        }
        else{
            text_view_note_empty.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(listItems: ArrayList<Bimbingan>){
        recycler_view_main.apply {
            adapter = BimbinganAdapter(listItems, object : BimbinganAdapter.BimbinganListener{
                override fun OnItemClicked(bimbingan: Bimbingan) {
                    val intent = Intent(this@BimbinganActivity, UbahBimbinganActivity::class.java)
                    intent.putExtra(UbahBimbinganActivity().UBAH_BIMBINGAN_EXTRA, bimbingan)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@BimbinganActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getBimbingansData()
    }
}
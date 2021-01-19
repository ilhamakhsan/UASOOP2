package com.ilham.uasoop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ilham.uasoop2.database.BimbinganDao
import com.ilham.uasoop2.database.BimbinganRomDB
import com.ilham.uasoop2.model.Bimbingan
import kotlinx.android.synthetic.main.activity_ubah_tugas.*

class UbahBimbinganActivity : AppCompatActivity() {

    val UBAH_BIMBINGAN_EXTRA = "ubah_tugas_extra"
    private lateinit var bimbingan : Bimbingan
    private var isUpdate = false
    private lateinit var database: BimbinganRomDB
    private lateinit var dao:BimbinganDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_bimbingan)

        database = BimbinganRomDB.getDatabase(applicationContext)
        dao = database.getBimbinganDao()

        if (intent.getParcelableExtra<Bimbingan>(UBAH_BIMBINGAN_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            bimbingan = intent.getParcelableExtra(UBAH_BIMBINGAN_EXTRA)!!
            edit_text_name.setText(bimbingan.title)
            edit_text_operator.setText(bimbingan.body)

            edit_text_name.setSelection(bimbingan.title.length)

        }

        button_save.setOnClickListener {
            val title = edit_text_name.text.toString()
            val body = edit_text_operator.text.toString()

            if (title.isEmpty() && body.isEmpty()){
                Toast.makeText(applicationContext, "Bimbingan tidak boleh ksosong", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveBimbingan(Bimbingan(id = bimbingan.id, title = title, body = body))
                }
                else{
                    saveBimbingan(Bimbingan(title = title, body = body))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteBimbingan(bimbingan)
            finish()
        }

    }

    private fun saveBimbingan(bimbingan: Bimbingan){

        if (dao.getById(bimbingan.id).isEmpty()){

            dao.insert(bimbingan)
        }
        else{

            dao.update(bimbingan)
        }

        Toast.makeText(applicationContext, "Berhasil di simpan", Toast.LENGTH_SHORT).show()

    }

    private fun deleteBimbingan(bimbingan: Bimbingan){
        dao.delete(bimbingan)
        Toast.makeText(applicationContext, "berhasil di hapus", Toast.LENGTH_SHORT).show()
    }
}
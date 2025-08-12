package com.catatan.keuanganrt

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var pemasukan: EditText
    private lateinit var pengeluaran: EditText
    private lateinit var sisa: EditText
    private lateinit var keterangan: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pemasukan = findViewById(R.id.inputPemasukan)
        pengeluaran = findViewById(R.id.inputPengeluaran)
        sisa = findViewById(R.id.inputSisa)
        keterangan = findViewById(R.id.inputKeterangan)
        btnSimpan = findViewById(R.id.btnSimpan)

        // Request permissions for Android < 11
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                100)
        }

        btnSimpan.setOnClickListener {
            simpanKeExcel()
        }
    }

    private fun simpanKeExcel() {
        try {
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet("Keuangan")

            // Header
            val header = sheet.createRow(0)
            header.createCell(0).setCellValue("Pemasukan")
            header.createCell(1).setCellValue("Pengeluaran")
            header.createCell(2).setCellValue("Sisa")
            header.createCell(3).setCellValue("Keterangan")

            // Data dari input
            val row = sheet.createRow(1)
            row.createCell(0).setCellValue(pemasukan.text.toString())
            row.createCell(1).setCellValue(pengeluaran.text.toString())
            row.createCell(2).setCellValue(sisa.text.toString())
            row.createCell(3).setCellValue(keterangan.text.toString())

            // Simpan file di folder Download
            val file = File("/storage/emulated/0/Download/catatan_keuangan.xlsx")
            FileOutputStream(file).use {
                workbook.write(it)
            }
            workbook.close()

            Toast.makeText(this, "Data berhasil disimpan di Download", Toast.LENGTH_LONG).show()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Gagal menyimpan: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}

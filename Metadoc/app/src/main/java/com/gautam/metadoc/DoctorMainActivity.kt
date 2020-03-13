package com.gautam.metadoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_doctor_main.*
import kotlinx.android.synthetic.main.current_meds_layout.*
import org.jetbrains.anko.*
import org.jetbrains.anko.db.DoubleParser

class DoctorMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_main)

        startAppButton.setOnClickListener {
            startActivity<RecordAppointmentActivity>()
        }
        currentMedsButton.setOnClickListener {
            setContentView(R.layout.current_meds_layout)
            var items = intent.getStringArrayExtra("items")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
            currentMedList.adapter = adapter
        }
    }
}

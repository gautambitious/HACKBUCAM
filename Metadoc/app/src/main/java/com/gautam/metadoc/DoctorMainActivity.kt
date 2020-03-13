package com.gautam.metadoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_doctor_main.*
import org.jetbrains.anko.*

class DoctorMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_main)

        startAppButton.setOnClickListener {
            startActivity<RecordAppointmentActivity>()
        }
    }
}

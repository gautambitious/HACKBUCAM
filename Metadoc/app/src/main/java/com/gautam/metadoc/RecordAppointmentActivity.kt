package com.gautam.metadoc

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_record_appointment.*

class RecordAppointmentActivity : AppCompatActivity() {
    val REQUEST_CODE = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_appointment)

    }
    fun onClick(v: View?) { //Trigger the RecognizerIntent intent//
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        try {
            startActivityForResult(intent, 123)
        } catch (a: ActivityNotFoundException) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> {
                //If RESULT_OK is returned...//
                if (resultCode === Activity.RESULT_OK && null != android.R.attr.data) { //...then retrieve the ArrayList//
                    val result: ArrayList<String> = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    nlpModelCall(result[0])
                }
            }
        }
    }
    private fun nlpModelCall(text: String) {
        Log.i("workk", text)
    }
}

package com.gautam.metadoc

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_record_appointment.*

class RecordAppointmentActivity : AppCompatActivity() {
    val REQUEST_CODE = 123
    val database by lazy {
        FirebaseDatabase.getInstance()
    }
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
        val myRef = database.reference.child("text")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.value
                Log.d("workk", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("workk", "Failed to read value.", error.toException())
            }
        })


    }
}

package com.gautam.metadoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.text.isDigitsOnly
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_doctor_home.*
import org.jetbrains.anko.toast

class DoctorHomeActivity : AppCompatActivity() {
    val db by lazy {
        FirebaseFirestore.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_home)

        idSubmitButton.setOnClickListener {
            var id = patientIdText.editText?.text.toString()
            if (id.isNullOrEmpty())
                toast("Invalid Id")
            else{
                db.collection("users").document(id).get().addOnSuccessListener {
                    val result = it.toObject(Patient::class.java)
                    Log.i("workk", result.toString())
                }.addOnFailureListener{
                    toast(it.message.toString())
                }
            }
        }

    }
}

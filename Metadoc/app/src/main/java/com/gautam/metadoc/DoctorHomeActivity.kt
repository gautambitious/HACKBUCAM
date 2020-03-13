package com.gautam.metadoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_doctor_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DoctorHomeActivity : AppCompatActivity() {
    val db by lazy {
        FirebaseFirestore.getInstance()
    }
    lateinit var result: Patient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_home)

        idSubmitButton.setOnClickListener {
            var id = patientIdText.editText?.text.toString()
            if (id.isNullOrEmpty())
                toast("Invalid Id")
            else{
                db.collection("users").document(id).get().addOnSuccessListener {
                    result = it.toObject(Patient::class.java)!!
                    Log.i("workk", result.toString())
                    startActivity<DoctorMainActivity>()
                }.addOnFailureListener{
                    toast(it.message.toString())
                }
            }
        }

    }
}

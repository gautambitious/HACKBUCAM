package com.gautam.metadoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_doctor_home.*
import kotlinx.android.synthetic.main.activity_doctor_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList as ArrayList1

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
                    Log.i("resultxx", result.toString())
                    val n = result?.medicines?.size!!
                    var items = arrayOfNulls<String>(n)
                    for(i in 0 until n){
                        var cur = result!!.medicines[i]
                        items[i] =
                            cur.name + ", for " + cur.frequency + " times a day, for " + cur.days + " days"
                    }
                    Log.i("another", items[0].toString())
                    startActivity<DoctorMainActivity>("items" to items)
                }.addOnFailureListener{
                    toast(it.message.toString())
                }
            }
        }


    }
}

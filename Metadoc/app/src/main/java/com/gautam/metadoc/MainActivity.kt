package com.gautam.metadoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.password_layout.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {
    val auth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailorphoneButton.setOnClickListener {
            var number = emailorphoneText.editText?.text.toString()
            if(number.isNullOrEmpty())
                toast("Invalid Entry")
            else{
                if(number.contains("@")){
                    emailLogin(number)
                }
                else{
                    if(!(number.startsWith("+") || number.startsWith("-"))){
                        number = "+91$number"
                    loginUsingPhone(number)
                }
            }
        }
    }
}

    private fun loginUsingPhone(number: String) {

    }

    private fun emailLogin(email: String) {
        setContentView(R.layout.password_layout)
        passwordButton.setOnClickListener {
            val password = passwordText.editText?.text.toString()
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                toast("SuccessFully LoggedIn")
//                startActivity<PatientHomeActivity>()
            }.addOnFailureListener{
                toast("Error: "+it.message)
            }
        }
    }
}

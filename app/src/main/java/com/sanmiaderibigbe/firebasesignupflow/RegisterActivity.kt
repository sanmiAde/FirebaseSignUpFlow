package com.sanmiaderibigbe.firebasesignupflow

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)

        val firebaseAuth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance()


        RegisterBtn.setOnClickListener {
            val password = passwordInputLayout.editText?.text.toString()
            val email = emailInputLayout.editText?.text.toString()
            val username = userNameInputLayout.editText?.text.toString()
            val phoneNumber = phoneNumberInputLayout.editText?.text.toString()


            Toast.makeText(applicationContext, "$email + $password", Toast.LENGTH_SHORT).show()
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                when (it.isSuccessful) {
                    true -> {
                        val currentUser = firebaseAuth.currentUser
                        currentUser?.sendEmailVerification()?.addOnCompleteListener {
                            database.getReference("Users").child(currentUser.uid)
                                .setValue(User(username, email, phoneNumber)).addOnCompleteListener {
                                startActivity(
                                    Intent(
                                        applicationContext,
                                        MainActivity::class.java
                                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                )
                                finish()
                            }
                        }

                    }
                    else -> {
                        Toast.makeText(applicationContext, "E-mail or password is wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        loginBtn.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }


    }

}

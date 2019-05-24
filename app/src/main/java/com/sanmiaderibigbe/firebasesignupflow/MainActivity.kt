package com.sanmiaderibigbe.firebasesignupflow

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAuth = FirebaseAuth.getInstance()


        val currentUser =  mAuth.currentUser

        mAuth.currentUser?.reload()
        hello.text ="${currentUser?.email }  ${currentUser?.isEmailVerified}"


        button.setOnClickListener {
            mAuth.signOut()
            startActivity(
                Intent(
                    applicationContext,
                    LoginActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
            finish()
        }

        userlist.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    UserListActivity::class.java
                ))
        }
    }
}

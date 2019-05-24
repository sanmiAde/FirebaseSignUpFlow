package com.sanmiaderibigbe.firebasesignupflow

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*


class LoginActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        val liveData = ViewModelProviders.of(this).get(LoginViewModel::class.java!!)


        if (liveData.isSignedIn()) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }


        LoginButton.setOnClickListener {
            val email = emailInput.editText?.text.toString()
            val password = passwordInput.editText?.text.toString()
            Toast.makeText(this, "$email $password", Toast.LENGTH_SHORT).show()

            liveData.login(email, password).observe(this, Observer {
                when {
                    it.isSuccessful -> startActivity(
                        Intent(
                            applicationContext,
                            MainActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )

                   else -> {

                        Toast.makeText(this,  it.error()?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            })

        }


        RegisterButton.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

    }

}

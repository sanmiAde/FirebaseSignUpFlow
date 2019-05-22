package com.sanmiaderibigbe.firebasesignupflow

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {


    lateinit var firebaseAuth: FirebaseAuth
    lateinit var googleApiClient: GoogleApiClient
    lateinit var googleSignClient: GoogleApiClient
    lateinit var signInOptions: GoogleSignInOptions
    lateinit var signInClient: GoogleApiClient

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        LoginButton.setOnClickListener {
            val email = emailInput.editText?.text.toString()
            val password = passwordInput.editText?.text.toString()
            Toast.makeText(this, "$email $password", Toast.LENGTH_SHORT).show()
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    when (task.isSuccessful) {
                        true -> {
                            startActivity(
                                Intent(
                                    applicationContext,
                                    MainActivity::class.java
                                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            )
                            finish()
                        }
                        false -> {

                        }

                    }
                }
        }


        RegisterButton.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

    }

}

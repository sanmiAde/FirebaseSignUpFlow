package com.sanmiaderibigbe.firebasesignupflow

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel()  {

    //don't need to pass email at view model creation.
    private  val firebaseAuth = FirebaseAuth.getInstance()


    fun isSignedIn(): Boolean {
        return when {
            firebaseAuth.currentUser != null -> true
            else -> false
        }
    }

    fun login(email: String, password: String): OnCompleteLiveData {
        val onCompleteLiveData  = OnCompleteLiveData()
        loginTask(email, password)?.addOnCompleteListener(onCompleteLiveData)

        return onCompleteLiveData
    }

    private fun loginTask(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)

    }

}
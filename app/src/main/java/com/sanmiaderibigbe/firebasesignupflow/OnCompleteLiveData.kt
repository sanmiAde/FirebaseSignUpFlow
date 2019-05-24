package com.sanmiaderibigbe.firebasesignupflow

import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class OnCompleteLiveData : LiveData<Resource<Boolean>>(), OnCompleteListener<com.google.firebase.auth.AuthResult> {
    override fun onComplete(task: Task<AuthResult>) {
        value = if (task.isSuccessful) {
            Resource(true)
        } else {
            Resource(task.exception!!)
        }
    }
}
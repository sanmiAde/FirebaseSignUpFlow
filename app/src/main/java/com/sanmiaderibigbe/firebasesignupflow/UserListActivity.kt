package com.sanmiaderibigbe.firebasesignupflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.DataSnapshot
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.android.synthetic.main.activity_user_list.*


class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)



        val currentUser  = FirebaseAuth.getInstance().currentUser

        val viewModelFactory = UserListViewModelFactory(currentUser?.uid!!)

        val liveData = ViewModelProviders.of(this, viewModelFactory ).get(UserListViewModel::class.java!!)


        liveData.getUserLiveData().observe(this, Observer {
            if (it != null) {
                userlisttxt.text = it.username
            }
        })

    }
}

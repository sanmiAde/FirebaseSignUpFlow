package com.sanmiaderibigbe.firebasesignupflow

import android.annotation.TargetApi
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import java.util.*


@TargetApi(Build.VERSION_CODES.N)
class UserListViewModel( private val currentUserId : String ) : ViewModel() {

    private val HOT_STOCK_REF = FirebaseDatabase.getInstance().getReference("/Users").child(currentUserId)


    private val liveData = FirebaseQueryLiveData(HOT_STOCK_REF)

    private  val userLiveData : LiveData<User> = Transformations.map(liveData, Deserializer())

    fun getDataSnapshotLiveData(): LiveData<DataSnapshot> {
        return liveData
    }

    fun getUserLiveData() : LiveData<User> {
        return userLiveData
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private  class Deserializer : androidx.arch.core.util.Function<DataSnapshot, User> {
        override fun apply(dataSnapshot: DataSnapshot): User {
            return dataSnapshot.getValue(User::class.java)!!
        }
    }


}
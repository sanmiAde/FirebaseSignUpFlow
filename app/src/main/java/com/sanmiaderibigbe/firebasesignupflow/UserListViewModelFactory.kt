package com.sanmiaderibigbe.firebasesignupflow


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserListViewModelFactory( private val currentUserId : String ) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return  UserListViewModel( currentUserId) as T

    }
}
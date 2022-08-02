package com.example.notebookexample2.viewmodel


import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.notebookexample2.repo.AuthDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class SignInViewModel () : ViewModel() {
    val aRepo = AuthDaoRepository()


    fun signIn(email:String,password:String,context: Context){
        aRepo.signIn(email,password,context)
    }



    fun actionSignUp(view: View){
        aRepo.actionSignUp(view)
    }


    fun checkCurrentUser(context: Context){
        aRepo.checkCurrentUser( context)
    }

}
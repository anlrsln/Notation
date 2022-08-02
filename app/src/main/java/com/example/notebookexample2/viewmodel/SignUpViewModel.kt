package com.example.notebookexample2.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.notebookexample2.repo.AuthDaoRepository
import com.example.notebookexample2.repo.NoteDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class SignUpViewModel () :ViewModel() {
    val aRepo = AuthDaoRepository()


    fun signUp(email:String,password:String,confirmPassword:String,view: View,context: Context){
        aRepo.signUp(email,password, confirmPassword, view, context)
    }



}
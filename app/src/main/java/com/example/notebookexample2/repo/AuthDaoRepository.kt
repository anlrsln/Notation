package com.example.notebookexample2.repo

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.notebookexample2.R
import com.example.notebookexample2.activities.NoteActivity
import com.google.firebase.auth.FirebaseAuth

class AuthDaoRepository {

    var firebaseAuth = FirebaseAuth.getInstance()

    fun signIn(email:String, password:String,context: Context){
        if(email.isNotEmpty() && password.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    val intent = Intent(context, NoteActivity::class.java)
                    context.startActivity(intent)
                }else{
                    Toast.makeText(context,it.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(context,"Empty Fields Are Not Allowed !", Toast.LENGTH_LONG).show()
        }
    }

    fun actionSignUp(view: View){
        Navigation.findNavController(view).navigate(R.id.actionSignInToSignUp)
    }


    fun checkCurrentUser(context: Context){
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            val intent = Intent(context, NoteActivity::class.java)
            context.startActivity(intent)
        }
    }


    fun signUp(email:String, password:String, confirmPassword:String, view: View, context: Context){
        if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
            if(password==confirmPassword){
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        Navigation.findNavController(view).navigate(R.id.actionSignUpToSignIn)

                    }else{
                        Toast.makeText(context,it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }else
                Toast.makeText(context,"Passwords Are Not Matching !", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Empty Fields Are Not Allowed !", Toast.LENGTH_LONG).show()
        }
    }



}
package com.example.notebookexample2.authfragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.notebookexample2.R
import com.example.notebookexample2.activities.MainActivity
import com.example.notebookexample2.activities.NoteActivity
import com.example.notebookexample2.databinding.FragmentSignUpBinding
import com.example.notebookexample2.viewmodel.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

//@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding:FragmentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val tempModel : SignUpViewModel by viewModels()
            this.viewModel = tempModel
        }catch (e:Exception){
            Log.e("Error : ",e.toString())
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)




        // Tasarımdan fragment'a erişim
        binding.fragmentobject=this

        // Activity parçasına erişip, fragmentContext'e gönderdik.
        binding.fragmentContext = requireContext()

        return binding.root
    }


    fun signUp(email:String,password:String,confirmPassword:String,view: View,context: Context){
        viewModel.signUp(email, password, confirmPassword, view, context)
    }




}
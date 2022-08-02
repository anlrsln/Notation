package com.example.notebookexample2.authfragments



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.notebookexample2.R
import com.example.notebookexample2.activities.MainActivity
import com.example.notebookexample2.activities.NoteActivity
import com.example.notebookexample2.databinding.FragmentSignInBinding
import com.example.notebookexample2.viewmodel.SignInViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

//@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var binding : FragmentSignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: SignInViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try{
            // ViewModel Activity bağlantısı
            val tempViewModel : SignInViewModel by viewModels()
            viewModel=tempViewModel
        }catch (e:Exception){
            Log.e("Hata : ",e.toString())
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in,container,false)

        // Tasarımdan fragment'a erişim
        binding.fragmentObject=this

        // Fragment'tan tasarım dosyasına context gönderimi
        binding.fragmentContext = requireContext()

        // Anlık Kullanıcı kontrolü
        checkCurrentUser(requireContext())



        return binding.root
    }

    fun signIn(email:String,password:String,context: Context){
        viewModel.signIn(email,password,context)

    }

    fun actionSignUp(view:View){
        viewModel.actionSignUp(view)
    }


    fun checkCurrentUser(context: Context){
        viewModel.checkCurrentUser(context)
    }



}
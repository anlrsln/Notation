package com.example.notebookexample2.notefragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.notebookexample2.R
import com.example.notebookexample2.databinding.FragmentAddNoteBinding
import com.example.notebookexample2.viewmodel.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

//@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private lateinit var binding:FragmentAddNoteBinding
    private lateinit var viewModel:AddNoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val tempModel : AddNoteViewModel by viewModels()
            viewModel = tempModel
        }catch (e:Exception){
            Log.e("Error : ",e.toString())
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_note,container,false)

        binding.fragmentObject=this

        return binding.root
    }

    fun addNote(header:String,note:String,view:View){
        viewModel.addNote(header,note, view)
    }




}
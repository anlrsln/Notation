package com.example.notebookexample2.notefragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.example.notebookexample2.R
import com.example.notebookexample2.databinding.FragmentNoteDetailBinding
import com.example.notebookexample2.viewmodel.NoteDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

//@AndroidEntryPoint
class NoteDetailFragment : Fragment() {

    private lateinit var binding:FragmentNoteDetailBinding
    private lateinit var viewModel:NoteDetailViewModel
    private lateinit var activityPart : AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val tempModel:NoteDetailViewModel by viewModels()
            viewModel=tempModel
        }catch (e:Exception){
            Log.e("Error : ",e.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_note_detail,container,false)

        binding.fragmentObject = this
        activityPart = (activity as AppCompatActivity)

        binding.detailToolbar.title=""
        activityPart.setSupportActionBar(binding.detailToolbar)

        // Navigation fragmentlar arası veri transferi (NoteFragment'tan NoteDetailFragment'a note nesnesi transferi)
        val bundle : NoteDetailFragmentArgs by navArgs()
        val note = bundle.note
        binding.note = note


        activityPart.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.detail_toolbar,menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){
                    R.id.delete ->{
                        val view = activityPart.findViewById<View>(R.id.delete)
                        //Log.e("View",view.toString())
                        delete(note.note_id!!,view)
                        return true
                    }else ->{
                        return false
                    }
                }
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root
    }

    fun  delete(note_id:String,view: View){
        viewModel.delete(note_id,view)
    }

    fun update(note_id: String,header:String,note:String,view: View){
        viewModel.update(note_id, header, note,view)
    }





}
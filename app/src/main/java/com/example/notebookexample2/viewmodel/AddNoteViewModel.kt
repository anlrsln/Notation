package com.example.notebookexample2.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.notebookexample2.repo.NoteDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class AddNoteViewModel () :ViewModel() {
    var nRepo = NoteDaoRepository()

    fun addNote(header:String,note:String,view:View){
        nRepo.addNote(header,note, view)
    }

}
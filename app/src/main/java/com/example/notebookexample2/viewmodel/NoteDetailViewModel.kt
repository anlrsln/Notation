package com.example.notebookexample2.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.notebookexample2.repo.NoteDaoRepository


//@HiltViewModel
class NoteDetailViewModel () :ViewModel() {

    var nRepo = NoteDaoRepository()
    //val openInfoMLD: SingleLiveEvent<Int> by lazy { SingleLiveEvent() }


    fun delete(note_id:String,view: View){
        nRepo.delete(note_id,view)
    }
    fun update(note_id: String,header:String,note:String,view: View){
        nRepo.update(note_id, header, note,view)
    }






}
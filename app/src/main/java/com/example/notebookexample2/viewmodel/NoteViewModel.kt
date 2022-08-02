package com.example.notebookexample2.viewmodel


import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notebookexample2.classes.Notes
import com.example.notebookexample2.repo.NoteDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class NoteViewModel () : ViewModel(){

    var nRepo = NoteDaoRepository()

    var notesList = MutableLiveData<List<Notes>>()


    init{
        notesList = nRepo.getNotes()
        getAllNotes()
    }


    fun getAllNotes(){
        nRepo.getAllNotes()
    }

    fun search(searchingWord:String){
        nRepo.search(searchingWord)
    }

    fun logOut(activity: AppCompatActivity){
        nRepo.logOut(activity)
    }

    fun clickCardView(note:Notes,view:View){
        nRepo.clickCardView(note, view)
    }







}
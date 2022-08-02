package com.example.notebookexample2.repo

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.notebookexample2.R
import com.example.notebookexample2.activities.MainActivity
import com.example.notebookexample2.activities.NoteActivity
import com.example.notebookexample2.classes.Notes
import com.example.notebookexample2.notefragments.NoteFragmentDirections
import com.example.notebookexample2.utill.pass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class NoteDaoRepository {

    // Livedata listesini tanımladık
    var notesList : MutableLiveData<List<Notes>>

    // Firebase kullanıcı oturum erişimi, database erişimi ve referans
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser
    val database = FirebaseDatabase.getInstance()
    val refNotes = database.getReference("notes/users/${currentUser?.uid}")


    init {
        notesList = MutableLiveData()
    }


    // NoteFragemnt'a Databasedeki tüm verileri gönderir
    fun getAllNotes(){
        Log.e("Kullanıcı : ",currentUser?.uid.toString())
        val newList = ArrayList<Notes>()
        refNotes.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Tüm veriler snapshot nesnesine gelir
                for (n in snapshot.children){
                    // Gelecek veriler satır satır alınıp, Note classından nesnelere dönüşür
                    val note = n.getValue(Notes::class.java)
                    if(note!=null){
                        val key = n.key // Kayıtlı verilerin keyleri alınır
                        note.note_id=key
                        newList.add(note)
                    }
                }
                notesList.value=newList
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("Error : ",error.toException().toString())
            }
        })
    }

    // LiveData listemizi NoteViewModel'dan çağırabilmemizi sağlar
    fun getNotes() : MutableLiveData<List<Notes>>{
        return notesList
    }

    // NoteFragment toolbardaki searching işleminde kullanılır
    fun search(searchingWord:String){
        val newList = ArrayList<Notes>()
        refNotes.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Tüm veriler snapshot nesnesine gelir
                for (n in snapshot.children){
                    // Gelecek veriler satır satır alınıp, Note classından nesnelere dönüşür
                    val note = n.getValue(Notes::class.java)
                    if(note!=null){
                        if(note.header!!.lowercase().contains(searchingWord.lowercase())){
                            val key = n.key // Kayıtlı verilerin keyleri alınır
                            newList.add(note)
                        }
                    }
                }
                notesList.value=newList
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("Error : ",error.toException().toString())
            }
        })
    }

    // NoteFragment toolbardaki logout işleminde kullanılır
    fun logOut(activity: AppCompatActivity){
        firebaseAuth.signOut()
        val intent = Intent(activity,MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }


    // AddNoteFragment'ta yeni not oluşturmamızı sağlar
    fun addNote(header:String,note:String, view: View){
        val id=""
        val email = currentUser?.email
        val note = Notes(id,email, header,note)
        refNotes.push().setValue(note)
        Navigation.pass(R.id.addNoteToNotes,view)
    }


    // NoteDetailFragment'taki silme ve düzenleme işlemleri
    fun delete(note_id:String,view:View){
        refNotes.child(note_id).removeValue()
        Navigation.pass(R.id.actionDetailToNotes,view)
    }

    fun update(note_id: String,header:String,note:String,view: View){
        val notesMap = HashMap<String,Any>()
        notesMap["header"]=header
        notesMap["note"]=note
        refNotes.child(note_id).updateChildren(notesMap)
        Navigation.pass(R.id.actionDetailToNotes,view)
    }


    // NoteCardView tıklanma işlemi

    fun clickCardView(note:Notes,view:View){
        val pass = NoteFragmentDirections.actionNoteToDetail(note = note)
        Navigation.pass(pass,view)
    }



}
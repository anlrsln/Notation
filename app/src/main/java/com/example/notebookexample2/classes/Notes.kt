package com.example.notebookexample2.classes

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Notes(var note_id:String?="", val email:String?="",val header:String?="" ,val note:String?=""):Serializable {
}
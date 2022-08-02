package com.example.notebookexample2.utill

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation




fun Navigation.pass(id:Int,view:View){
        findNavController(view).navigate(id)
}


fun Navigation.pass(navDirections: NavDirections,view:View){
    findNavController(view).navigate(navDirections)
}
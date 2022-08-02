package com.example.notebookexample2.classes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notebookexample2.R
import com.example.notebookexample2.databinding.NoteCardViewBinding
import com.example.notebookexample2.notefragments.NoteFragment
import com.example.notebookexample2.notefragments.NoteFragmentDirections
import com.example.notebookexample2.viewmodel.NoteViewModel

class NoteAdapter(private val mContext: Context,
                  private val noteList:List<Notes>,
                  private val viewModel: NoteViewModel ):RecyclerView.Adapter<NoteAdapter.NoteCardViewHolder>(){


    inner class NoteCardViewHolder(val binding:NoteCardViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteCardViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = NoteCardViewBinding.inflate(layoutInflater,parent,false)
        return NoteCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteCardViewHolder, position: Int) {
        val note = noteList[position]
        holder.binding.textView.text="${note.header}"

        holder.binding.noteCardView.setOnClickListener {
            viewModel.clickCardView(note,it)
        }


    }

    override fun getItemCount(): Int {
        return noteList.size
    }


}
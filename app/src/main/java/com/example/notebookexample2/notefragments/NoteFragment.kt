package com.example.notebookexample2.notefragments


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.notebookexample2.R
import com.example.notebookexample2.activities.NoteActivity
import com.example.notebookexample2.classes.NoteAdapter
import com.example.notebookexample2.databinding.FragmentNoteBinding
import com.example.notebookexample2.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception



class NoteFragment : Fragment(),SearchView.OnQueryTextListener {
    private lateinit var binding:FragmentNoteBinding
    private lateinit var adapter:NoteAdapter
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        try {
            val tempModel : NoteViewModel by viewModels()
            viewModel=tempModel
        }catch (e:Exception){
            Log.e("Error : ",e.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_note,container,false)

        // Tasarımdaki fragment objesini bu fragmenta bağladık
        binding.fragmentObject=this

        // Fragment'ın bağlı olduğu activity parçasını aldık
        val activityPart = (activity as AppCompatActivity)

        // Toolbar başlığını verdik ve toolbar üzerinde işlem yapabilmemiz için setSupportActionBar() fonksiyonunu kullandık
        binding.noteToolbar.title = ""
        activityPart.setSupportActionBar(binding.noteToolbar)


        // Livedata türündeki listemizdeki güncellemeleri alabilmek için observe metodunu kullandık
        // ve anlık olarak adapter'a göndermesi için adapter nesnemizi burdan tanımladık
        viewModel.notesList.observe(viewLifecycleOwner){
            adapter = NoteAdapter(requireContext(),it,viewModel)
            binding.adapter=adapter
        }


        // Menü işlemlerimiz
        activityPart.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Menü bağlama işlemi
                menuInflater.inflate(R.menu.notes_toolbar_menu,menu)

                // Item'a eriştik ve search işlevini verdik
                val item = menu.findItem(R.id.search_item)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@NoteFragment)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId){
                    // LogOut işlemi
                    R.id.logOut -> {
                        logOut(activityPart)
                        return true
                    }else -> {
                        return false
                    }
                }
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)

        return binding.root
    }


    // Aranan kelimenin anlık ve son haline erişmek için iki metod kullandık
    override fun onQueryTextSubmit(query: String): Boolean {
        search(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        search(newText)
        return true
    }



    // ViewModel'dan metodlara erişim
    fun search(searchingWord:String){
        viewModel.search(searchingWord)
    }

    fun logOut(activity: AppCompatActivity){
        viewModel.logOut(activity)
    }


}
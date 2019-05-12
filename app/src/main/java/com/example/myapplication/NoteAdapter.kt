package com.example.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NoteAdapter(private val list: MutableList<Note>, private var context: Context) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) = MyViewHolder(LayoutInflater
            .from(context).inflate(R.layout.item_note, p0, false) as View)
    //    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


    override fun getItemCount() = list.size


    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        var note = list[p1]
        p0.txtTitle.text = note.title
        p0.txtText.text = note.text

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         var txtText: TextView
         var txtTitle: TextView
        init {
            txtText = view.findViewById(R.id.txtNoteText)
            txtTitle = view.findViewById(R.id.txtTitle)
        }
    }
}
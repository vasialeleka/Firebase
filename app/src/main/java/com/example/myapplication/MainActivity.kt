package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var ref: DatabaseReference
    var maxId: Int = 0
    lateinit var notes: String
    var listOfNotes:MutableList<Note>? = mutableListOf<Note>()
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        FirebaseApp.initializeApp(this)
        context = this
        ref = FirebaseDatabase.getInstance().getReference().child("Note")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                listOfNotes!!.clear()
                maxId = p0.childrenCount.toInt()
                for (item in 0..maxId - 1) {
                    if (!p0.child(item.toString()).child("text").toString().isEmpty() || p0.child(item.toString()).child("text").toString().length != 0) {
                        listOfNotes!!.add(Note(p0.child(item.toString()).child("title").getValue().toString(), p0.child(item.toString()).child("text").getValue().toString()))
                        recView.layoutManager = LinearLayoutManager(context)
                        recView.adapter = NoteAdapter(listOfNotes!!, context)
                    }
                }


            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.addNote -> startActivity(Intent(this, AddNote::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        /*   when (v) {
               btnSend -> if (txtName.text.isEmpty()) {
                   Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show()
               } else {
                   ref.child((maxId).toString()).setValue(txtName.text.toString())
                   txtName.text.clear()
               }
           }*/
    }

}

package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.app_bar.*

class AddNote : AppCompatActivity(), View.OnClickListener {
    lateinit var ref: DatabaseReference
    var maxId: Int = 0
    override fun onClick(v: View?) {
        when (v) {
            saveNote -> ref.child((maxId).toString()).setValue("new" + maxId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        setSupportActionBar(toolbar)
        if (toolbar != null) {
            val actionbar = supportActionBar
            actionbar!!.title = "Add Note"
            //set back button
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        //set actionbar title
        saveNote.setOnClickListener(this)
        ref = FirebaseDatabase.getInstance().getReference().child("Note")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                maxId = p0.childrenCount.toInt()
            }
        })


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

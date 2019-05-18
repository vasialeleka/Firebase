package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.app_bar.*

class AddNoteActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var ref: DatabaseReference
    var maxId: Int = 0
    var maxIdUser: Int = 0
    lateinit var mAuth: FirebaseAuth
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        setSupportActionBar(toolbar)
        if (toolbar != null) {
            val actionbar = supportActionBar
            actionbar!!.title = "Add Note"
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        mAuth = FirebaseAuth.getInstance()

        saveNote.setOnClickListener(this)
        user = mAuth.currentUser
        ref = FirebaseDatabase.getInstance().getReference().child("Note")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                maxId = p0.childrenCount.toInt()
            }
        })
        if (user != null) {
            ref.child(user!!.uid).addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    maxIdUser = p0.childrenCount.toInt()
                }
            })
        }

    }

    override fun onClick(v: View?) {
        when (v) {
            saveNote -> saveNote()
        }
    }

    private fun saveNote() {

        if (user == null) {
            if (txtTitle.text.isEmpty()) {
                Toast.makeText(applicationContext, "Enter Title of note", Toast.LENGTH_LONG).show()
            } else if (txtNote.text.isEmpty()) {
                Toast.makeText(applicationContext, "Enter your note", Toast.LENGTH_LONG).show()
            } else {
                ref.child("General").child((maxId).toString()).setValue(Note(txtTitle.text.toString(), txtNote.text.toString()))
                startActivity(Intent(this, MainActivity::class.java))
            }
        } else {
            if (txtTitle.text.isEmpty()) {
                Toast.makeText(applicationContext, "Enter Title of note", Toast.LENGTH_LONG).show()
            } else if (txtNote.text.isEmpty()) {
                Toast.makeText(applicationContext, "Enter your note", Toast.LENGTH_LONG).show()
            } else {
                ref.child(user!!.uid).child((maxIdUser).toString())
                    .setValue(Note(txtTitle.text.toString(), txtNote.text.toString()))
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

package com.example.myapplication

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_autorization.*
import kotlinx.android.synthetic.main.app_bar.*

class AutorizationActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mAuth: FirebaseAuth
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autorization)
        context = applicationContext
        mAuth = FirebaseAuth.getInstance()
        btnBack.setOnClickListener(this)
        btnRegistr.setOnClickListener(this)
        btnLogIn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btnBack -> super.onBackPressed()
            btnRegistr -> regisration()
            btnLogIn -> logIn()
        }
    }

    private fun logIn() {
        if (txtEmail.toString().isEmpty()) {
            Toast.makeText(context, "Enter email", Toast.LENGTH_SHORT).show()
        } else if (txtPassword.toString().isEmpty()) {
            Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show()
        } else {
            mAuth.signInWithEmailAndPassword(txtEmail.text.toString(), txtPassword.text.toString())
                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }

    private fun regisration() {
        if (txtEmail.toString().isEmpty()) {
            Toast.makeText(context, "Enter email", Toast.LENGTH_SHORT).show()
        } else if (txtPassword.toString().isEmpty()) {
            Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show()
        } else {
            mAuth.createUserWithEmailAndPassword(txtEmail.text.toString(), txtPassword.text.toString())
                .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }

}

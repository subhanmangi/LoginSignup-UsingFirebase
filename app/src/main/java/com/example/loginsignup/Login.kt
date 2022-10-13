package com.example.loginsignup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : Activity() {

    lateinit var loginButton: Button
    private lateinit var auth: FirebaseAuth
    lateinit var loginLabel: TextView
    lateinit var emailSignup: EditText
    lateinit var passwordSignup: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Initialize Firebase Auth
        auth = Firebase.auth

        loginButton = findViewById(R.id.registerButton)
        loginLabel = findViewById(R.id.loginLabel)
        emailSignup = findViewById(R.id.emailLogin)
        passwordSignup = findViewById(R.id.passwordLogin)



        loginLabel.setOnClickListener {
            val intent = Intent(this,Signup::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener {
            var email = emailSignup.text.toString()
            var password = passwordSignup.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("sign in", "signInWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("sign in", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }




        }

    }
}

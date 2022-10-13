package com.example.loginsignup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signup : Activity() {

    lateinit var fullname : EditText
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var signupButton : Button
    lateinit var registerLabel : TextView

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        fullname = findViewById(R.id.fullNameText)
        email = findViewById(R.id.emailRegister)
        password = findViewById(R.id.passwordRegister)
        signupButton = findViewById(R.id.registerButton)
        registerLabel = findViewById(R.id.RegisterLabel)

        // Initialize Firebase Auth
        auth = Firebase.auth

        signupButton.setOnClickListener {
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("sign up", "createUserWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(baseContext, "Sign up successful.",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("sign up", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }
    }
}
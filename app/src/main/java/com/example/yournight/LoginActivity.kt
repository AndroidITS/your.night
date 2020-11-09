package com.example.yournight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var usernameInput: String? = null
    var passwordInput: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener{
            usernameInput = findViewById<TextInputEditText>(R.id.usernameEditText).text.toString()
            passwordInput = findViewById<TextInputEditText>(R.id.passwordEditText).text.toString()

            if(usernameInput != null && passwordInput != null) {
                if(usernameInput!!.length > 0 && passwordInput!!.length > 0) {
                    auth.signInWithEmailAndPassword(usernameInput!!, passwordInput!!)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("succTag", "signInWithEmail:success")
                                val user = auth.currentUser
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("failTag", "signInWithEmail:failure", task.exception)
                                confirmLoginTextView.text = usernameInput + " " + passwordInput
                                // ...
                            }
                            // ...
                        }
                }
            }

        }

        createAccount.setOnClickListener {
            openCreateAccountActivity()
        }

    }


    fun openCreateAccountActivity() {
        val intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
    }
}
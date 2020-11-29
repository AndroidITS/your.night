package com.example.yournight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.passwordEditText


class CreateAccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var email: String? = null
    var password: String? = null
    val db = Firebase.firestore
    var nome: String? = null
    var cognome: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        FirebaseFirestore.setLoggingEnabled(true)
        auth = Firebase.auth

        registerButton.setOnClickListener {
            nome = nameEditText.text.toString()
            cognome = cognomeEditText.text.toString()
            email = emailEditText.text.toString()
            password = passwordEditText.text.toString()

            if(email != null && password != null && nome != null && cognome != null) {
                if(email!!.length > 0 && password!!.length > 0 && nome!!.length > 0 && cognome!!.length > 0) {
                    auth.createUserWithEmailAndPassword(email!!, password!!)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("createAccount", "createUserWithEmail:success")
                                val userAccess = auth.currentUser!!.uid
                                Log.d("createAccount", auth.currentUser.toString())
                                // Create a new user with a first and last name
                                val user = hashMapOf(
                                    "nome" to nome,
                                    "cognome" to cognome
                                )
                                // Add a new document with a generated ID
                                db.collection("users")
                                    .document(auth.currentUser!!.uid)
                                    .set(user)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d("testFireStore", "DocumentSnapshot added with ID")
                                        openLoginActivity()
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w("testFireStore", "Error adding document", e)
                                    }
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("createAccount", "createUserWithEmail:failure", task.exception)
                                confirmRegistrationTextView.text = "Registrazione NON avvenuta con successo"
                            }

                            // ...
                        }
                }
            }
        }
    }

    private fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
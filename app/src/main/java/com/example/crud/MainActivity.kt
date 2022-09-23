package com.example.crud

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Access a Cloud Firestore instance from your Activity

        val db = Firebase.firestore

        val txtNome: EditText = findViewById(R.id.txtNome)
        val txtEndereco: EditText = findViewById(R.id.txtEndereco)
        val txtBairro: EditText = findViewById(R.id.txtBairro)
        val txtCep: EditText = findViewById(R.id.txtCep)
        val btnCadastrar: Button = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener{
            // Create a new user with a first and last name
            val pessoa = hashMapOf(
                "nome" to txtNome.text.toString(),
                "endereco" to txtEndereco.text.toString(),
                "bairro" to txtBairro.text.toString(),
                "cep" to txtCep.text.toString()
            )

// Add a new document with a generated ID
            db.collection("cadastro")
                .add(pessoa)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

        }


    }

}
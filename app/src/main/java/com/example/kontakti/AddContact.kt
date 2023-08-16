package com.example.kontakti

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact)

        val addContactButton = findViewById<Button>(R.id.addContactButton)
        addContactButton.setOnClickListener {
            val newName = findViewById<EditText>(R.id.nameEditText).text.toString()
            val newPhoneNumber = findViewById<EditText>(R.id.phoneNumberEditText).text.toString()
            val newAddress = findViewById<EditText>(R.id.addressEditText).text.toString()
            val newEmail = findViewById<EditText>(R.id.emailEditText).text.toString()

            val newContact = Contact(newName, newPhoneNumber, newAddress, newEmail)
            (applicationContext as MyApp).contacts.add(newContact)

            setResult(RESULT_OK)
            finish()
        }
    }
}

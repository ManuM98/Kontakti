package com.example.kontakti

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kontakti.databinding.EditContactBinding

class EditContactActivity : AppCompatActivity() {
    private lateinit var binding: EditContactBinding
    private var editingPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_contact)
        binding = EditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contact = intent.getSerializableExtra("contact") as? Contact
        editingPosition = intent.getIntExtra("position", -1)

        if (contact != null) {
            binding.nameEditText.setText(contact.name)
            binding.phoneNumberEditText.setText(contact.phoneNumber)
            binding.addressEditText.setText(contact.address)
            binding.emailEditText.setText(contact.email)
        }

        binding.saveButton.setOnClickListener {
            val newName = binding.nameEditText.text.toString()
            val newPhoneNumber = binding.phoneNumberEditText.text.toString()
            val newAddress = binding.addressEditText.text.toString()
            val newEmail = binding.emailEditText.text.toString()

            val newContact = Contact(newName, newPhoneNumber, newAddress, newEmail)

            val contactsList = (application as MyApp).contacts

            if (editingPosition != -1) {
                contactsList[editingPosition] = newContact
            } else {
                contactsList.add(newContact)
            }

            setResult(RESULT_OK)
            finish()
        }

        binding.deleteButton.apply {
            visibility = if (editingPosition != -1) View.VISIBLE else View.GONE
            setOnClickListener {
                val contactsList = (application as MyApp).contacts

                if (editingPosition != -1 && editingPosition < contactsList.size) {
                    contactsList.removeAt(editingPosition)
                    setResult(RESULT_OK)
                    finish()
                }
            }
        }
    }
}

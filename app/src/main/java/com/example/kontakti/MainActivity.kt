package com.example.kontakti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import com.example.kontakti.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val contacts = mutableListOf<Contact>()
    private lateinit var adapter: Adapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = Adapter(this, contacts)
        binding.contactListView.adapter = adapter

        binding.addContactButton.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivityForResult(intent, ADD_CONTACT_REQUEST)
        }

        binding.contactListView.setOnItemClickListener { _, _, position, _ ->
            val selectedContact = contacts[position]
            val intent = Intent(this, EditContactActivity::class.java)
            intent.putExtra("contact", selectedContact)
            intent.putExtra("position", position)
            startActivityForResult(intent, EDIT_CONTACT_REQUEST)
        }

        binding.searchContactButton.setOnClickListener {
            // TODO: Implement contact search logic
        }

        binding.deleteContactButton.setOnClickListener {
            val selectedPosition = binding.contactListView.checkedItemPosition
            if (selectedPosition != AdapterView.INVALID_POSITION) {
                contacts.removeAt(selectedPosition)
                adapter.notifyDataSetChanged()
                binding.contactListView.clearChoices()
                adapter.clearSelected()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == ADD_CONTACT_REQUEST) {
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        private const val ADD_CONTACT_REQUEST = 2
        private const val EDIT_CONTACT_REQUEST = 1
    }
}
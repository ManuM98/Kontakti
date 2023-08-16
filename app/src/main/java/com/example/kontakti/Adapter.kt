package com.example.kontakti

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.kontakti.databinding.ListItemBinding

class Adapter(private val context: Context, private val contacts: List<Contact>) : BaseAdapter() {

    private val selectedPositions = mutableListOf<Int>()

    fun setSelected(position: Int) {
        if (selectedPositions.contains(position)) {
            selectedPositions.remove(position)
        } else {
            selectedPositions.add(position)
        }
        notifyDataSetChanged()
    }

    fun clearSelected() {
        selectedPositions.clear()
        notifyDataSetChanged()
    }

    override fun getCount(): Int = contacts.size

    override fun getItem(position: Int): Any = contacts[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val contact = getItem(position) as Contact
        val binding = convertView?.let {
            ListItemBinding.bind(it)
        } ?: ListItemBinding.inflate(LayoutInflater.from(context), parent, false)

        binding.contactName.text = contact.name
        binding.contactPhoneNumber.text = contact.phoneNumber
        binding.contactAddress.text = contact.address
        binding.contactEmail.text = contact.email

        if (selectedPositions.contains(position)) {
            binding.root.setBackgroundResource(R.color.selectedItemColor)
        } else {
            binding.root.setBackgroundResource(android.R.color.transparent)
        }

        return binding.root
    }
}

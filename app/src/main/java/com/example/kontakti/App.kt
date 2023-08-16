package com.example.kontakti

import android.app.Application

class MyApp : Application() {
    val contacts = mutableListOf<Contact>()
}
package com.example.contactlist

import android.app.Application
import com.example.contactlist.model.UsersService

class App: Application() {
    val userService = UsersService()
}
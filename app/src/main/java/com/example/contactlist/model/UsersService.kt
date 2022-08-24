package com.example.contactlist.model

import com.github.javafaker.Faker

typealias UsersListener = (users: List<User>) -> Unit

class UsersService {

    private var users = mutableListOf<User>()

    private val listeners = mutableSetOf<UsersListener>()

    init {
        val faker = Faker.instance()
        users = (1..20).map { User(
            id = it.toLong(),
            name = faker.name().name(),
            aboutMe = faker.company().name(),
        ) }.toMutableList()
    }

    fun getUsers(): List<User> {
        return users
    }

    fun deleteUser(user: User) {
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) {
            users.removeAt(indexToDelete)
        }
    }

     fun addListener(listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }
}
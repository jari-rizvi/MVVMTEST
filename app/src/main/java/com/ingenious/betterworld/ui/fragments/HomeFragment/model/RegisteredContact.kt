package com.ingenious.betterworld.ui.fragments.HomeFragment.model

import androidx.annotation.Keep

@Keep

data class RegisteredContact(
    val contactId: Int,
    val contactType: String,
    val image: String,
    val name: String
)
package com.ingenious.betterworld.ui.fragments.HomeFragment.model

import androidx.annotation.Keep

@Keep

data class Result(
    val checkOutToo: Boolean,
    val classDateTime: String,
    val classEndTime: String,
    val classRosterAttendeeID: Int,
    val classRosterID: Int,
    val classRosterName: String,
    val classStartTime: String,
    val registeredContacts: List<RegisteredContact>
)
package com.ingenious.betterworld.ui.fragments.HomeFragment.model

import androidx.annotation.Keep
import com.ingenious.betterworld.ui.fragments.HomeFragment.model.Result
@Keep
data class TestModel(
    val error: Any,
    val hasErrors: Boolean,
    val message: Any,
    val result: List<Result>,
    val timestamp: String
)

/*@Keep
class TestModel : ArrayList<Result>()*/

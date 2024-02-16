package com.ingenious.betterworld.ui.fragments.temp


import com.ingenious.betterworld.baseclasses.BaseViewModel
import com.ingenious.betterworld.data.remote.reporitory.MainRepository
import com.ingenious.betterworld.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TempViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

}
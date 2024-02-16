package com.ingenious.betterworld.ui.fragments.HomeFragment


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ingenious.betterworld.baseclasses.BaseViewModel
import com.ingenious.betterworld.data.remote.Resource
import com.ingenious.betterworld.data.remote.reporitory.MainRepository
import com.ingenious.betterworld.ui.fragments.HomeFragment.model.TestModel
import com.ingenious.betterworld.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {


    private val _getTestDataResponse = MutableLiveData<Resource<TestModel>>()
    val getTestDataResponse: LiveData<Resource<TestModel>>
        get() = _getTestDataResponse

    fun getTestData() {
        viewModelScope.launch {
            _getTestDataResponse.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    Timber.tag("87878787887").d("starta")

                    mainRepository.getTestData().let {
                        if (it.isSuccessful) {
                            _getTestDataResponse.postValue(Resource.success(it.body()!!))
                            Timber.tag("87878787887").d(it.body()!!.toString())
                        }  else if (it.code() == 500 || it.code() == 409 || it.code() == 502 || it.code() == 404 || it.code() == 400) {
                            Timber.tag("87878787887").d("secoonnddd")

//                            _getTestDataResponse.postValue(Resource.error(it.message(), null))
                            val jsonObj = JSONObject(it.errorBody()!!.charStream().readText())
                            _getTestDataResponse.postValue(Resource.error(jsonObj.getString("message")))
                        } else {
                            _getTestDataResponse.postValue(
                                Resource.error(
                                    "Some thing went wrong",
                                    null
                                )
                            )
                            Timber.tag("87878787887").d("third")

                        }
                    }
                } catch (e: Exception) {
                    Log.d("getTestDataResponse", "dataRecive: ${e.message}")
                    _getTestDataResponse.postValue(Resource.error("${e.message}", null))
                }
            } else{
                _getTestDataResponse.postValue(Resource.error("No internet connection", null))
            }
        }
    }

}
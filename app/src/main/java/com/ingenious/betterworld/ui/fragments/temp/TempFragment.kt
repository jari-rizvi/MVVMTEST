package com.ingenious.betterworld.ui.fragments.temp

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.ingenious.betterworld.R
import com.ingenious.betterworld.baseclasses.BaseFragment
import com.ingenious.betterworld.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TempFragment : BaseFragment<ActivitySplashBinding, TempViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: Class<TempViewModel>
        get() = TempViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun clickListener() {

    }

}
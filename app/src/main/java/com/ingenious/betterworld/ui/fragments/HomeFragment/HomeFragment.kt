package com.ingenious.betterworld.ui.fragments.HomeFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ingenious.betterworld.BR
import com.ingenious.betterworld.R
import com.ingenious.betterworld.baseclasses.BaseFragment
import com.ingenious.betterworld.data.remote.Resource
import com.ingenious.betterworld.databinding.FragmentHomeBinding
import com.ingenious.betterworld.ui.fragments.HomeFragment.model.Result
import com.ingenious.betterworld.utils.DialogHelperClass
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {


    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: Class<HomeViewModel>
        get() = HomeViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel


    lateinit var resultArrayList: ArrayList<Result>

    private lateinit var resultAdapter: ResultAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        categoriesRecyclerview()
        mViewModel.getTestData()


        if (!mViewModel.getTestDataResponse.hasActiveObservers()) {
            mViewModel.getTestDataResponse.observe(requireActivity()) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        loadingDialog.show()
                    }

                    Resource.Status.SUCCESS -> {
                        loadingDialog.dismiss()
                        it.data?.let { data ->

                            Log.d("TAG", "onViewCreated: ${data}")

                            resultArrayList.clear()

                            data.result.forEach  {
                                resultArrayList.add(it)
                            }

                            mViewDataBinding.recrec.adapter?.notifyDataSetChanged()




                        }

                    }
                    Resource.Status.ERROR -> {
                        loadingDialog.dismiss()
                        DialogHelperClass.errorDialog(requireContext(), it.message!!)
                    }
                }
                if (isAdded) {
                    mViewModel.getTestDataResponse.removeObservers(viewLifecycleOwner)
                }
            }
        }



    }


    private fun categoriesRecyclerview() {
        resultArrayList = ArrayList()

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mViewDataBinding.recrec.layoutManager = linearLayoutManager

        resultAdapter = ResultAdapter(resultArrayList)
        mViewDataBinding.recrec.adapter = resultAdapter
    }


}
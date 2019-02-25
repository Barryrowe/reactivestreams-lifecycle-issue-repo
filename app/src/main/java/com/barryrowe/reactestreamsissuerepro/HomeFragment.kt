package com.barryrowe.reactestreamsissuerepro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment: Fragment() {

    private lateinit var viewModel: HomeViewModel

    companion object {
        private const val TAG = "TestFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i(TAG, "OnActivityCreated Called")
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.testNaturalLiveData().observe(viewLifecycleOwner, Observer{
            Log.i(TAG, "====[FROM NATURAL LD]: $it")
            log_details.text = "${log_details.text} ${Date().time} - [PLAIN]: $it\n"
        })
        viewModel.testStreamLiveData().observe(viewLifecycleOwner, Observer{
            Log.i(TAG, "====[FROM OBSERVABLE]: $it")
            log_details.text = "${log_details.text} ${Date().time} - [RX2LD]: $it\n"
        })
    }
}
package com.barryrowe.reactestreamsissuerepro

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams.fromPublisher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.PublishSubject

class HomeViewModel : ViewModel() {

    fun testNaturalLiveData(): LiveData<String> {
        return MutableLiveData<String>().apply {
            value = "I AM A NATURAL LIVE DATA!!!"
        }
    }

    fun testStreamLiveData(): LiveData<String> {
        return fromPublisher(
            PublishSubject.create<String>()
                .startWith("I AM A STREAM LIVE DATA!!!")
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }
}
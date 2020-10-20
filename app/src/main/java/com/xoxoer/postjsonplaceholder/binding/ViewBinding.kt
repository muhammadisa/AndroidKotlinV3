package com.xoxoer.postjsonplaceholder.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.xoxoer.postjsonplaceholder.util.common.createAlert
import com.xoxoer.postjsonplaceholder.util.common.createLoading
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@BindingAdapter("errorHandler", "errorReasonHandler")
fun bindErrorAlert(
    view: View,
    error: ObservableField<Boolean>,
    errorReason: ObservableField<String>
) {
    when (error.get()) {
        true -> {
            view.context.createAlert(
                "Alert",
                errorReason.get()!!
            ).show()
            error.set(false)
            errorReason.set(null)
        }
        false -> {
        }
    }
}

@BindingAdapter("loadingHandler")
fun bindLoadingDialog(view: View, isLoading: MutableLiveData<Boolean>) {
    val loading = view.context.createLoading()
    isLoading.observeForever { stillLoading ->
        if (stillLoading) loading.show()
        else GlobalScope.launch {
            delay(2000)
            loading.dismiss()
        }
    }
}

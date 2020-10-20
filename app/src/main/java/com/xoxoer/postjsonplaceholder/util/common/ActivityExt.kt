package com.xoxoer.postjsonplaceholder.util.common

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.xoxoer.postjsonplaceholder.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

fun Activity.dismissKeyboard() {
    val view = this.currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

//inline fun <reified VM : ViewModel> ComponentActivity.viewModelInitialize(
//    viewModelProvider: ViewModelProvider.Factory,
//    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
//): Lazy<VM> {
//    val factoryPromise = factoryProducer ?: {
//        viewModelProvider
//    }
//    return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
//}
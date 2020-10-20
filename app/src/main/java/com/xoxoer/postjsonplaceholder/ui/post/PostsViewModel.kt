package com.xoxoer.postjsonplaceholder.ui.post

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xoxoer.lifemarklibrary.Lifemark
import com.xoxoer.postjsonplaceholder.model.PostsItem
import com.xoxoer.postjsonplaceholder.util.apisingleobserver.RxSingleHandler
import com.xoxoer.postjsonplaceholder.util.common.toWhereLikeFormat
import com.xoxoer.postjsonplaceholder.viewmodels.ViewModelContract
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    application: Application,
    lifemark: Lifemark,
    private val postRepository: PostRepository
) : ViewModel(), ViewModelContract {

    val searchPostQuery = ObservableField<String>()

    private val _postsSuccess = MutableLiveData<List<PostsItem>>()
    val postsSuccess: LiveData<List<PostsItem>>
        get() = _postsSuccess

    private val rxSingleHandler = RxSingleHandler(application, lifemark, this)
    override val isLoading = MutableLiveData<Boolean>()
    override val error = ObservableField<Boolean>()
    override val errorReason = ObservableField<String>()

    private fun onStart() {
        isLoading.value = true
    }

    private fun onFinish() {
        isLoading.value = false
    }

    fun retrievePosts() {
        postRepository.retrievePosts(
            { onStart() },
            { onFinish() },
            rxSingleHandler.handler(_postsSuccess)
        )
    }

    fun retrievePostsWithQuery() {
        postRepository.retrievePostsWithQuery(
            searchPostQuery.get()!!.toWhereLikeFormat(),
            { onStart() },
            { onFinish() },
            rxSingleHandler.handler(_postsSuccess)
        )
    }

}
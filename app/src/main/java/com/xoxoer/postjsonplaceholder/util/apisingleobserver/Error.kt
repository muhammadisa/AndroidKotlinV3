package com.xoxoer.postjsonplaceholder.util.apisingleobserver

class Error(
    var message: String = "",
    var errorCode: String = "",
    var throwable: Throwable? = null
)
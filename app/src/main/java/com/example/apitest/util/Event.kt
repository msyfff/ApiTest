package com.example.apitest.util

open class Event<out T>(private val content: T) {
    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenhHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenhHandled) {
            null
        }else {
            hasBeenhHandled = true
            content
        }
    }
    fun peekContent() : T = content
}
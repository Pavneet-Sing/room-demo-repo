package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.base

interface BindingAdapterI<T> {
    fun setSource(data: T)
}
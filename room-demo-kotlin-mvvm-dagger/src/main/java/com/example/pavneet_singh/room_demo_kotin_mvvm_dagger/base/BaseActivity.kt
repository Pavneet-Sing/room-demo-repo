package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.base

import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Pavneet_Singh on 2020-01-30.
 */

/**
 * To support dagger injections in child activities and abstracts the boilerplate dagger code
 */
open class BaseActivity : DaggerAppCompatActivity()
package com.tom.pokemon.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseActivity : AppCompatActivity() {
    /*
    @LayoutRes는 Layout resource reference를 예상하는 애노테이션이다. ex) R.layout.activity_main
    lazy 리턴 타입은 Lazy<T>이다.
    DataBindingUtil.setContentView<T>는 DataBinding을 할 수 있게 한다.
     */
    protected inline fun <reified T : ViewDataBinding> binding(@LayoutRes resId: Int): Lazy<T> =
        lazy { DataBindingUtil.setContentView<T>(this, resId) }

}
package com.fionicholas.footballapp.base

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var baseActivity: BaseActivity? = null

    fun setupToolbar(toolbar: Toolbar?, title: String, isChild: Boolean) {
        baseActivity?.setupToolbar(toolbar, title, isChild)
    }
}
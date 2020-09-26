package com.fionicholas.footballapp.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.fionicholas.footballapp.R
import com.kennyc.view.MultiStateView

fun MultiStateView.showLoadingState() {
    this.viewState = MultiStateView.ViewState.LOADING
}

fun MultiStateView.showContentState() {
    this.viewState = MultiStateView.ViewState.CONTENT
}


fun MultiStateView.showErrorState(
    errorMessage: String? = null,
    title: String? = null,
    drawable: Drawable? = null,
    errorButton: Pair<String, (() -> Unit)>? = null
) {
    this.viewState = MultiStateView.ViewState.ERROR

    errorMessage?.let {
        val tvError =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<TextView>(R.id.tv_error)
        tvError?.text = errorMessage
    }

    title?.let {
        val tvTitle =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<TextView>(R.id.tv_title)
        tvTitle?.text = title
    }

    drawable?.let {
        val imgError =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<ImageView>(R.id.img_error)
        imgError?.setImageDrawable(drawable)
    }

    errorButton?.let { pair ->
        val btnError =
            this.getView(MultiStateView.ViewState.ERROR)?.findViewById<Button>(R.id.btn_error)
        btnError?.text = pair.first
        btnError?.visibility = View.VISIBLE

        btnError?.setOnClickListener { pair.second.invoke() }
    }
}
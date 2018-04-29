package com.valentino.questionbank.utilities

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.view.View

interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View?) {
            view?.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View?) {
            view?.setOnClickListener({
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            })
        }
    })
}

fun defaultPrefs(context: Context): SharedPreferences
        = PreferenceManager.getDefaultSharedPreferences(context)

const val SESSION_ID = "session"

var SharedPreferences.session
    get() = getString(SESSION_ID, "")
    set(value: String) { edit().putString(SESSION_ID, value).apply() }

const val MODE_ID = "mode"

var SharedPreferences.mode
    get() = getString(MODE_ID, "")
    set(value: String) { edit().putString(MODE_ID, value).apply() }
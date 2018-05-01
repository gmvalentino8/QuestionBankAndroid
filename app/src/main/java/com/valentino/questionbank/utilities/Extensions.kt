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

fun prefs(context: Context): SharedPreferences
        = PreferenceManager.getDefaultSharedPreferences(context)

const val SESSION_ID = "session"

var SharedPreferences.session
    get() = getString(SESSION_ID, "")
    set(value: String) { edit().putString(SESSION_ID, value).apply() }

const val USER_ID = "user"

var SharedPreferences.user
    get() = getString(USER_ID, "")
    set(value: String) { edit().putString(USER_ID, value).apply() }
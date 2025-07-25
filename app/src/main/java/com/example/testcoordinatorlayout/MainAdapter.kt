package com.example.testcoordinatorlayout

import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.core.view.updateMarginsRelative
import androidx.core.view.updatePadding
import androidx.core.view.updatePaddingRelative
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MainAdapter: ListAdapter<Int, MainAdapter.Holder>(diffUtil) {

    inner class Holder(private val view: TextView): RecyclerView.ViewHolder(view) {

        fun bind(value: Int) {
            view.text = "Значение = $value"
        }
    }



    companion object {
        private val diffUtil = object: DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
                return p0 == p1
            }

            override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
                return p0 == p1
            }

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        return Holder(TextView(p0.context).apply {
            layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT).apply {
                updateMarginsRelative(start = 30, top = 60, end = 30)
            }
            updatePaddingRelative(start = 40, top = 40, bottom = 40)
            setBackgroundColor(ContextCompat.getColor(p0.context, R.color.black))
            setTextColor(ContextCompat.getColor(p0.context, R.color.white))

        })
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(getItem(p1))
    }
}
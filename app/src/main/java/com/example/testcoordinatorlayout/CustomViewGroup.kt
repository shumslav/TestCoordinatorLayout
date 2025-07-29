package com.example.testcoordinatorlayout

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.testcoordinatorlayout.databinding.CustomViewGroupBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.min

class CustomViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    private val binding = CustomViewGroupBinding.inflate(LayoutInflater.from(context), this)

    private var recyclerScroll: Int = 0

//    override fun setLayoutParams(params: ViewGroup.LayoutParams?) {
//        val newParams = (params as? AppBarLayout.LayoutParams)?.apply {
//            minimumHeight = 100.toPx()
//        } ?: params
//        super.setLayoutParams(newParams)
//    }

    init {
        minimumHeight = 100.toPx()
    }

    private var listener = object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            recyclerScroll += dy
            Log.d("CustomViewGroup", "onScrolled: $recyclerScroll")
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) = with(binding) {
        Log.d("CustomViewGroup", "onOffsetChanged: $verticalOffset totalScrollRange = ${appBarLayout.totalScrollRange}")
        toolbar.translationY = min(-verticalOffset.toFloat(), binding.stories.height.toFloat())
//        binding.search.translationY = if (recyclerScroll > 0) {
//            max(0f, -verticalOffset.toFloat())
//        }
//        else {
//
//        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }

    fun setupRecycler(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(listener)
    }

    fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}
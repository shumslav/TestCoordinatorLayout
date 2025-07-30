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

    private var heightToolbar: Int = 0
        set(value) {
            field = value
            setupMinimumHeight()
        }
    private var heightSearch: Int = 0
        set(value) {
            field = value
            setupMinimumHeight()
        }

    init {
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        toolbar.addOnLayoutChangeListener { view, i, i2, i3, i4, i5, i6, i7, i8 ->
            heightToolbar = view.height
        }

        search.addOnLayoutChangeListener { view, i, i2, i3, i4, i5, i6, i7, i8 ->
            heightSearch = view.height
        }
    }

    private fun setupMinimumHeight() {
        minimumHeight = heightToolbar + heightSearch
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) = with(binding) {
        Log.d("CustomViewGroup", "onOffsetChanged: $verticalOffset totalScrollRange = ${appBarLayout.totalScrollRange}")
        toolbar.translationY = min(-verticalOffset.toFloat(), binding.stories.height.toFloat())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }
}
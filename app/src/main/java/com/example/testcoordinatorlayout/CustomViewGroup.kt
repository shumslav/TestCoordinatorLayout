package com.example.testcoordinatorlayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.testcoordinatorlayout.databinding.CustomViewGroupBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.min

class CustomViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    private val binding = CustomViewGroupBinding.inflate(LayoutInflater.from(context), this)

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) = with(binding) {
        Log.d("CustomViewGroup", "onOffsetChanged: $verticalOffset totalScrollRange = ${appBarLayout.totalScrollRange}")
//        binding.root.y = appBarLayout.totalScrollRange.toFloat() - verticalOffset
        toolbar.translationY = min(-verticalOffset.toFloat(), binding.stories.height.toFloat())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)
    }
}
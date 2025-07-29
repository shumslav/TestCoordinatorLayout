package com.example.testcoordinatorlayout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcoordinatorlayout.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        MainAdapter {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.rvCatalog.adapter = adapter
        binding.rvCatalog.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.submitList((0..100).toList())

        setupRecyclerView()

//        binding.main.setOnTouchListener { view, event ->
//            binding.rvCatalog.onTouchEvent(event)
//        }
    }

    private fun setupRecyclerView() = with(binding) {
//        rvCatalog.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                Log.d("TAG", "onScrolled: $dy")
//                onChangeVerticalScroll(dy)
//            }
//        })
    }

    private fun onChangeVerticalScroll(dy: Int) {
        if (dy > 0)
            onScrollDown(dy)
        else
            onScrollUp(abs(dy))

    }

    private fun onScrollUp(dy: Int) = with(binding) {
        var lastScroll = dy
        if (stories.translationY != 0f) {
            val distance = abs(stories.translationY.toInt())
            val canDistance = min(lastScroll, distance)
//            stories.translationY += canDistance
//            search.translationY += canDistance
//            rvCatalog.translationY += canDistance
            lastScroll -= canDistance
        }
    }

    private fun onScrollDown(dy: Int) = with(binding) {
        var lastScroll = dy
        var isWasStoriesScrolled: Boolean
        if (stories.y != toolbar.y) {
            val distance = (stories.y - toolbar.y).toInt()
            val canDistance = min(lastScroll, distance)
            stories.translationY -= canDistance
            search.translationY -= canDistance
            rvCatalog.translationY -= canDistance
            lastScroll -= canDistance
            isWasStoriesScrolled = canDistance == distance
        } else {
            isWasStoriesScrolled = true
        }
        val searchDistance = search.y + search.height
        if (searchDistance != 0f && isWasStoriesScrolled) {
            val distance = searchDistance.toInt()
            val canDistance = min(lastScroll, distance)
            toolbar.y -= canDistance
            search.y -= canDistance
            rvCatalog.y -= canDistance
        }
    }
}
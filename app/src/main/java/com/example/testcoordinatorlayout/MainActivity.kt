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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy {
        MainAdapter {
            binding.main.progress = 0.5f
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

        setupMotionLayout()

        Log.d("TAG", "Ids")
        Log.d("TAG", "all_expanded_started_set = ${R.id.all_expanded_started_set}")
        Log.d("TAG", "without_stories_set = ${R.id.without_stories_set}")
        Log.d("TAG", "catalog_only_set = ${R.id.catalog_only_set}")
    }

    private fun reduceMotionEvent(event: MotionEvent?): Boolean = binding.run {
        val isCanScrollVerticalUp = rvCatalog.canScrollVertically(-1)
        val isLastState = main.currentState == R.id.catalog_only_set

        return when {
            !isLastState -> {
                main.onTouchEvent(event)
                false
            }

            isCanScrollVerticalUp -> {
                main.onTouchEvent(event)
                false
            }

            else -> false
        }
    }


    private fun setupMotionLayout() = with(binding) {
        main.addTransitionListener(object : MotionLayout.TransitionListener {

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                Log.d("TAG", "onTransitionStarted")
                Log.d("TAG", "p1 = $p1  p2 = $p2")
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
//                Log.d("TAG", "onTransitionChange")
//                Log.d("TAG", "p1 = $p1 p2 = $p2 p3= $p3")
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                Log.d("TAG", "onTransitionCompleted")
                Log.d("TAG", "p1 = $p1")
//                Log.d("TAG", "current state = ${main.currentState}")
//                val nextTransition = when (p1) {
//                    R.id.all_expanded_started_set -> R.id.without_stories_transition
//
//                    R.id.without_stories_set -> if (lastState == R.id.all_expanded_started_set)
//                        R.id.toolbar_and_search_only_transition
//                    else
//                        R.id.without_stories_transition
//
//                    R.id.toolbar_and_search_only_set -> if (lastState == R.id.without_stories_set)
//                        R.id.catalog_only_transition
//                    else
//                        R.id.toolbar_and_search_only_transition
//
//                    R.id.catalog_only_set -> R.id.catalog_only_transition
//
//                    else -> R.id.without_stories_transition
//                }
//                main.setTransition(nextTransition)
//                lastState = p1
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                Log.d("TAG", "onTransitionTrigger")
                Log.d("TAG", "p1 = $p1 p2 = $p2 p3= $p3")
            }
        })
    }
}
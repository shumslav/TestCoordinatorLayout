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
        MainAdapter {}
    }

    private val categoriesAdapter by lazy {
        CategoriesAdapter {
            binding.rvCatalog.smoothScrollToPosition(it)
            binding.content.setExpanded(false, true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.rvCatalog.adapter = adapter
        binding.rvCatalog.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.categories.adapter = categoriesAdapter
        binding.categories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        categoriesAdapter.submitList((0..100).toList())
        adapter.submitList((0..100).toList())
    }
}
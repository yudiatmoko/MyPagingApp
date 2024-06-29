package com.iyam.mypagingapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.iyam.mypagingapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var userAdapter: UserAdapter
    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(
            binding.main
        ) { v, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        setupRecyclerView()
        loadUserData()
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                userAdapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter()
        layoutManager = LinearLayoutManager(this)
        binding.rvUserList.layoutManager = layoutManager
        binding.rvUserList.apply {
            adapter = userAdapter
            setHasFixedSize(true)
        }
    }
}

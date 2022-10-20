package id.piusanggoro.skorbola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import id.piusanggoro.skorbola.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.plusOneButtonA.setOnClickListener {
            viewModel.incrementScore(true)
        }
        binding.plusOneButtonB.setOnClickListener {
            viewModel.incrementScore(false)
        }

        val scoreA_Observer = Observer<Int> { newValue ->
            binding.scoreViewA.text = newValue.toString()
        }
        val scoreB_Observer = Observer<Int> { newValue ->
            binding.scoreViewB.text = newValue.toString()
        }

        viewModel.scoreA.observe(this, scoreA_Observer)
        viewModel.scoreB.observe(this, scoreB_Observer)
    }
}
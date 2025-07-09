package com.example.handclash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.handclash.databinding.ActivityGamePageBinding
import com.example.handclash.pop_up.first_guide_pop_up

class game_page : AppCompatActivity() {

    private lateinit var binding: ActivityGamePageBinding
    private var playerChoice: String? = ""
    private var monsterChoice: String? = ""
    private val handler = Handler(Looper.getMainLooper())
    private var countDown = 3
    private val updateInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val showPopUp = first_guide_pop_up()
        showPopUp.show(supportFragmentManager, "first_guide_pop_up")

        val monsterHealthBar = binding.monsterHealthBar
        val playerHealthBar = binding.playerHealthBar
        monsterHealthBar.max = 1000
        monsterHealthBar.progress = 1000
        playerHealthBar.max = 1000
        playerHealthBar.progress = 1000

        val choice = listOf("rock", "paper", "scissor")

        binding.rockBtn.setOnClickListener {
            playerChoice = "rock"
            monsterChoice = choice.random()
            processBattle()
        }

        binding.paperBtn.setOnClickListener {
            playerChoice = "paper"
            monsterChoice = choice.random()
            processBattle()
        }

        binding.scissorBtn.setOnClickListener {
            playerChoice = "scissor"
            monsterChoice = choice.random()
            processBattle()
        }
    }

    private fun processBattle() {
        val monsterHealthBar = binding.monsterHealthBar
        val playerHealthBar = binding.playerHealthBar

        if (playerChoice == monsterChoice) {
            Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show()
        } else if (playerChoice == "rock" && monsterChoice == "scissor" ||
            playerChoice == "paper" && monsterChoice == "rock" ||
            playerChoice == "scissor" && monsterChoice == "paper"
        ) {
            monsterHealthBar.progress = (monsterHealthBar.progress - 100)
            Toast.makeText(this, "You Attack!", Toast.LENGTH_SHORT).show()
        } else {
            playerHealthBar.progress = (playerHealthBar.progress - 100)
            Toast.makeText(this, "You Got Hit!", Toast.LENGTH_SHORT).show()
        }

        checkGameOver()
    }

    private fun checkGameOver() {
        val playerHealth = binding.playerHealthBar.progress
        val monsterHealth = binding.monsterHealthBar.progress

        when {
            playerHealth <= 0 -> {
                Toast.makeText(this, "You Lose!", Toast.LENGTH_LONG).show()
                // Tambahin navigasi atau pop-up Game Over di sini
            }
            monsterHealth <= 0 -> {
                Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).show()
                // Tambahin navigasi atau pop-up Victory di sini
            }
        }
    }
}

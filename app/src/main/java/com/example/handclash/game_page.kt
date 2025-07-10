package com.example.handclash

import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.handclash.databinding.ActivityGamePageBinding
import com.example.handclash.pop_up.first_guide_pop_up
import com.example.handclash.pop_up.loser_pop_up
import com.example.handclash.pop_up.pause_pop_up
import com.example.handclash.pop_up.winner_pop_up

class game_page : AppCompatActivity() {

    private lateinit var binding: ActivityGamePageBinding
    private var playerChoice: String? = ""
    private var monsterChoice: String? = ""
    private val handler = Handler(Looper.getMainLooper())
    private var countDown = 3
    private val updateInterval: Long = 1000
    private var guideReaded = false
    private lateinit var player_choice: ImageView
    private lateinit var monster_choice: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!guideReaded){
            val showPopUp = first_guide_pop_up()
            showPopUp.show(supportFragmentManager, "first_guide_pop_up")
            guideReaded = true
        }

        player_choice = binding.playerChoice
        monster_choice = binding.monsterChoice

        binding.pauseBtn.setOnClickListener{
            val showPopUp = pause_pop_up()
            showPopUp.show(supportFragmentManager, "pause_pop_up")
        }

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

//        player_choice.setImageDrawable(null)
//        monster_choice.setImageDrawable(null)

        if (playerChoice == monsterChoice) {
            if (playerChoice == "rock"){
                player_choice.setImageResource(R.drawable.icon_rock)
                monster_choice.setImageResource(R.drawable.icon_rock)
            } else if (playerChoice == "paper"){
                player_choice.setImageResource(R.drawable.icon_paper)
                monster_choice.setImageResource(R.drawable.icon_paper)
            } else if (playerChoice == "scissor"){
                player_choice.setImageResource(R.drawable.icon_scissor)
                monster_choice.setImageResource(R.drawable.icon_scissor)
            }

            Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show()
        } else if (playerChoice == "rock" && monsterChoice == "scissor" ||
            playerChoice == "paper" && monsterChoice == "rock" ||
            playerChoice == "scissor" && monsterChoice == "paper"
        ) {
            if (playerChoice == "rock"){
                player_choice.setImageResource(R.drawable.icon_rock)
                monster_choice.setImageResource(R.drawable.icon_scissor)
            } else if (playerChoice == "paper"){
                player_choice.setImageResource(R.drawable.icon_paper)
                monster_choice.setImageResource(R.drawable.icon_rock)
            } else if (playerChoice == "scissor"){
                player_choice.setImageResource(R.drawable.icon_scissor)
                monster_choice.setImageResource(R.drawable.icon_paper)
            }
            monsterHealthBar.progress = (monsterHealthBar.progress - 100)
            Toast.makeText(this, "You Attack!", Toast.LENGTH_SHORT).show()
        } else {
            if (playerChoice == "rock"){
                player_choice.setImageResource(R.drawable.icon_rock)
                monster_choice.setImageResource(R.drawable.icon_paper)
            } else if (playerChoice == "paper"){
                player_choice.setImageResource(R.drawable.icon_paper)
                monster_choice.setImageResource(R.drawable.icon_scissor)
            } else if (playerChoice == "scissor"){
                player_choice.setImageResource(R.drawable.icon_scissor)
                monster_choice.setImageResource(R.drawable.icon_rock)
            }

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
                val showPopUp = loser_pop_up()
                showPopUp.show(supportFragmentManager, "loser_pop_up")

            }
            monsterHealth <= 0 -> {
                val showPopUp = winner_pop_up()
                showPopUp.show(supportFragmentManager, "winner_pop_up")
            }
        }
    }
}

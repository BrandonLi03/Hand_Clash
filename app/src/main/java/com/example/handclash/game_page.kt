package com.example.handclash

import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
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
    private lateinit var player_choice: ImageView
    private lateinit var monster_choice: ImageView
    private var monsterbaseMaxHP = 1000
    private var playerbaseMaxHP = 1000
    private val monsterhpIncrement = 200
    private val playerhpIncrement = 190

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player_choice = binding.playerChoice
        monster_choice = binding.monsterChoice

        binding.pauseBtn.setOnClickListener{
            val showPopUp = pause_pop_up()
            showPopUp.show(supportFragmentManager, "pause_pop_up")
        }

        val monsterHealthBar = binding.monsterHealthBar
        val playerHealthBar = binding.playerHealthBar
        monsterHealthBar.max = monsterbaseMaxHP
        monsterHealthBar.progress = monsterbaseMaxHP
        playerHealthBar.max = playerbaseMaxHP
        playerHealthBar.progress = playerbaseMaxHP

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

        val monsterHealth = binding.monsterHp
        val playerHealth = binding.playerHp

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

        monsterHealth.text = binding.monsterHealthBar.progress.toString()
        playerHealth.text = binding.playerHealthBar.progress.toString()

        checkGameOver()
    }

    private fun checkGameOver() {
        val playerHealth = binding.playerHealthBar.progress
        val monsterHealth = binding.monsterHealthBar.progress

        when {
            playerHealth <= 0 -> {
                val showPopUp = loser_pop_up()
                showPopUp.show(supportFragmentManager, "loser_pop_up")
                noNextLevel()
            }
            monsterHealth <= 0 -> {
                val showPopUp = winner_pop_up()
                showPopUp.show(supportFragmentManager, "winner_pop_up")
                nextLevel()
            }
        }
    }

    private fun nextLevel() {
        monsterbaseMaxHP += monsterhpIncrement
        playerbaseMaxHP += playerhpIncrement

        binding.monsterHealthBar.max = monsterbaseMaxHP
        binding.monsterHealthBar.progress = monsterbaseMaxHP
        binding.monsterHp.text = monsterbaseMaxHP.toString()
        binding.playerHealthBar.max = playerbaseMaxHP
        binding.playerHealthBar.progress = playerbaseMaxHP
        binding.playerHp.text = playerbaseMaxHP.toString()
    }

    private fun noNextLevel() {
        binding.monsterHealthBar.max = monsterbaseMaxHP
        binding.monsterHealthBar.progress = monsterbaseMaxHP
        binding.monsterHp.text = monsterbaseMaxHP.toString()
        binding.playerHealthBar.max = playerbaseMaxHP
        binding.playerHealthBar.progress = playerbaseMaxHP
        binding.playerHp.text = playerbaseMaxHP.toString()
    }
}

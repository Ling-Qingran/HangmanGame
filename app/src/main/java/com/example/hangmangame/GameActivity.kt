package com.example.hangmangame

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.hangmangame.databinding.ActivityGameBinding
import org.w3c.dom.Text

class GameActivity: AppCompatActivity() {
    private lateinit var binding:ActivityGameBinding
    private val gameManager=GameManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newGameButton.setOnClickListener {
            startNewGame();
        }
        val gameState=gameManager.startNewGame()
        updateUI(gameState)
        for(views in binding.lettersLayout.children){
            if(views is TextView){
                views.setOnClickListener{
                    val gameState=gameManager.playGame(views.text[0])
                    updateUI(gameState)
                    views.visibility=View.GONE
                }
            }
            
            
        }

    }

    //activities when starting a new turn
    private fun startNewGame() {
        binding.gameLostTextView.visibility= View.GONE
        binding.gameWonTextView.visibility=View.GONE
        val gameState=gameManager.startNewGame()
        binding.lettersLayout.visibility=View.VISIBLE
        for(views in binding.lettersLayout.children){
            views.visibility=View.VISIBLE
        }
        updateUI(gameState)
    }


    //update the user interface with game state
    private fun updateUI(gameState: GameState) {
        when(gameState){
            is GameState.GameLost-> showGameLostUI(gameState.wordToGuess);
            is GameState.GameWin -> showGameWinUI(gameState.wordToGuess)
            is GameState.GameRunning-> showGameRunningUI(gameState.underScoreWords,gameState.lettersUsed,gameState.drawable)

        }
    }

    private fun showGameWinUI(wordToGuess: String) {
        binding.wordTextView.text=wordToGuess;
        binding.gameWonTextView.visibility=View.VISIBLE
        binding.lettersLayout.visibility=View.GONE
    }

    private fun showGameLostUI(wordToGuess: String) {
        binding.wordTextView.text=wordToGuess
        binding.gameLostTextView.visibility=View.VISIBLE
        binding.lettersLayout.visibility=View.GONE
        binding.imageView.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.game08))

    }

    private fun showGameRunningUI(underScoreWords:String, lettersUsed: String, drawable:Int){
        binding.wordTextView.text=underScoreWords
        binding.lettersUsedTextView.text=lettersUsed
        binding.imageView.setImageDrawable(ContextCompat.getDrawable(this,drawable))
    }

}
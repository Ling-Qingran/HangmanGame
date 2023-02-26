package com.example.hangmangame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.hangmangame.databinding.ActivityGameBinding

class GameActivity: AppCompatActivity() {
    private lateinit var binding:ActivityGameBinding
    private val gameViewModel: GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newGameButton.setOnClickListener {
            startNewGame();
        }
        //gameViewModel.startNewGame()
//        gameViewModel.currentWordToGuess= intent.getStringExtra("wordToGuess").toString();
//        gameViewModel.currentUnderscoreWord= intent.getStringExtra("underScore").toString()
        updateUI(gameViewModel.currentGameState)
        for(views in binding.lettersLayout.children){
            if(views is TextView){
                views.setOnClickListener{
                    gameViewModel.playGame(views.text[0])
                    updateUI(gameViewModel.currentGameState)
                    views.visibility=View.GONE
                }
            }
            
            
        }

    }

    private fun loadGame(){
        when(gameViewModel.currentGameState){
            0->{

            }
        }
    }

    //activities when starting a new turn
    private fun startNewGame() {
        binding.gameLostTextView.visibility= View.GONE
        binding.gameWonTextView.visibility=View.GONE
        gameViewModel.startNewGame()
        binding.lettersLayout.visibility=View.VISIBLE
        for(views in binding.lettersLayout.children){
            views.visibility=View.VISIBLE
        }
        updateUI(gameViewModel.currentGameState)
    }


    //update the user interface with game state
    private fun updateUI(state: Int) {
        when(state){
            0-> showGameLostUI(gameViewModel.currentWordToGuess);
            1-> showGameWinUI(gameViewModel.currentWordToGuess);
            2-> showGameRunningUI(gameViewModel.currentUnderscoreWord,gameViewModel.currentLettersUsed,gameViewModel.currentDrawable);

        }
    }

    private fun showGameWinUI(wordToGuess: String) {
        binding.wordTextView.text=wordToGuess.uppercase();
        binding.gameWonTextView.visibility=View.VISIBLE
        binding.lettersLayout.visibility=View.GONE
    }

    private fun showGameLostUI(wordToGuess: String) {
        binding.wordTextView.text=wordToGuess.uppercase()
        binding.gameLostTextView.visibility=View.VISIBLE
        binding.lettersLayout.visibility=View.GONE
        binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.game08))

    }

    private fun showGameRunningUI(underScoreWords:String, lettersUsed: String, drawable:Int){
        binding.wordTextView.text=underScoreWords
        binding.lettersUsedTextView.text=lettersUsed
        binding.imageView.setImageDrawable(ContextCompat.getDrawable(this,drawable))
    }

    companion object{
        fun newIntent(packageContext: Context, wordToGuess: String,underScoreWords: String): Intent {
            return Intent(packageContext,GameActivity::class.java).apply {
                putExtra("wordToGuess",wordToGuess)
                putExtra("underScore",underScoreWords)
            }
        }
    }

}
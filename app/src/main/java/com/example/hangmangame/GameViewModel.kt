package com.example.hangmangame

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel:ViewModel() {
    private var lettersUsed: String=""
    private var currTries: Int = 0
    private var drawable: Int=R.drawable.game01;
    private lateinit var wordToGuess: String;
    private lateinit var underscoreWord: String;

    var currentLettersUsed:String
        get() = lettersUsed
        set(value) {lettersUsed=value}

    var currentTries:Int
        get() = currTries
        set(value) {currTries=value}
    var currentDrawable:Int
        get() = drawable
        set(value) {drawable=value }
    var currentWordToGuess: String
        get() = wordToGuess
        set(value) {wordToGuess=value}
    var currentUnderscoreWord:String
        get() = underscoreWord
        set(value) {underscoreWord=value}

    fun startNewGame():GameState{
        lettersUsed="";
        currTries=0;
        drawable=R.drawable.game08
        wordToGuess=GameConstants.words[Random.nextInt(GameConstants.words.size)]
        underscoreWord=GameManager().generateUnderScore(wordToGuess.length)
        return getGameState()
    }
    private fun getGameState():GameState{
        return if(underscoreWord == wordToGuess){
            GameState.GameWin(wordToGuess)
        } else if(currTries==GameConstants.maxTries){
            GameState.GameLost(wordToGuess)
        }else{
            drawable=GameManager().getDrawable(currTries)
            GameState.GameRunning(lettersUsed,underscoreWord,drawable)
        }
    }
    fun playGame(letter: Char):GameState{
        if(lettersUsed.contains(letter)){
            return GameState.GameRunning(lettersUsed,underscoreWord,drawable)
        }
        lettersUsed+=letter;
        val indexes=ArrayList<Int>();
        wordToGuess.forEachIndexed { index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        if(indexes.size ==0){
            currTries++;
        }
        var updatedUnderScore=""+underscoreWord;
        indexes.forEach { index ->
            val sb = StringBuilder(updatedUnderScore).also { it.setCharAt(index, letter) }
            updatedUnderScore = sb.toString()
        }
        underscoreWord=updatedUnderScore
        return getGameState()

    }

}
package com.example.hangmangame

import kotlin.random.Random


class GameManager {
    private var lettersUsed: String="";
    private val maxTries: Int=GameConstants.maxTries;
    private var currTries: Int=0;
    private var drawable: Int=R.drawable.game01;
    private lateinit var wordToGuess: String;
    private lateinit var underscoreWord: String;


    //restart a new game, clear all values set before
    fun startNewGame():GameState{
        lettersUsed="";
        currTries=0;
        drawable=R.drawable.game08
        wordToGuess=GameConstants.words[Random.nextInt(GameConstants.words.size)]
        underscoreWord=generateUnderScore(wordToGuess.length)
        return getGameState()
    }

    fun playGame(letter: Char):GameState{
        if(lettersUsed.contains(letter)){
            return GameState.GameRunning(lettersUsed,underscoreWord,drawable)
        }
        lettersUsed+=letter;
        var indexes=ArrayList<Int>();
        for(i in wordToGuess.indices){
            if(wordToGuess[i] == letter){
                indexes.add(i);
            }
        }
        if(indexes.size ==0){
            currTries++;
        }
        var updatedUnderScore=""+underscoreWord;
        val sb=StringBuilder(updatedUnderScore);
        for(i in 0 until indexes.size){
            sb.setCharAt(indexes[i],letter);
        }
        underscoreWord=updatedUnderScore
        return getGameState()

    }

    private fun getDrawable():Int{
        return when(currTries){
            0->R.drawable.game01
            1->R.drawable.game02
            2->R.drawable.game03
            3->R.drawable.game04
            4->R.drawable.game05
            5->R.drawable.game06
            6->R.drawable.game07
            7->R.drawable.game08
            else -> {R.drawable.game08}
        }
    }

    private fun generateUnderScore(wordSize: Int):String{
        val sb=StringBuilder()
        for (index in 0..wordSize-1){
            sb.append("-");
        }
        return sb.toString();
    }

    private fun getGameState():GameState{
        return if(underscoreWord == wordToGuess){
            GameState.GameWin(wordToGuess)
        } else if(currTries==maxTries){
            GameState.GameLost(wordToGuess)
        }else{
            drawable=getDrawable()
            GameState.GameRunning(lettersUsed,underscoreWord,drawable)
        }
    }
}
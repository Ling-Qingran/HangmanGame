package com.example.hangmangame

import androidx.lifecycle.ViewModel

class GameViewModel:ViewModel() {
    private val wordsBank=GameConstants.words
    private var lettersUsed: String=""
    private var currTries: Int = 0
    private var drawable: Int=R.drawable.game01;
    private lateinit var wordToGuess: String;
    private lateinit var underscoreWord: String;

    var

}
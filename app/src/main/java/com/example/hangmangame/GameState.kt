package com.example.hangmangame

import android.graphics.drawable.AdaptiveIconDrawable


//game states enum
sealed class GameState {
    class GameWin(val wordToGuess: String):GameState()
    class GameLost(val wordToGuess: String):GameState()
    class GameRunning(val lettersUsed: String, val underScoreWords:String, val drawable: Int):GameState()
}
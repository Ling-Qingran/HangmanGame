package com.example.hangmangame

import kotlin.random.Random


class GameManager {


    //restart a new game, clear all values set before
//    fun startNewGame():GameState{
//        lettersUsed="";
//        currTries=0;
//        drawable=R.drawable.game08
//        wordToGuess=GameConstants.words[Random.nextInt(GameConstants.words.size)]
//        underscoreWord=generateUnderScore(wordToGuess.length)
//        return getGameState()
//    }

//    fun playGame(letter: Char):GameState{
////        if(lettersUsed.contains(letter)){
////            return GameState.GameRunning(lettersUsed,underscoreWord,drawable)
////        }
//        lettersUsed+=letter;
//        val indexes=ArrayList<Int>();
//        wordToGuess.forEachIndexed { index, char ->
//            if (char.equals(letter, true)) {
//                indexes.add(index)
//            }
//        }
//
//        if(indexes.size ==0){
//            currTries++;
//        }
//        var updatedUnderScore=""+underscoreWord;
//        indexes.forEach { index ->
//            val sb = StringBuilder(updatedUnderScore).also { it.setCharAt(index, letter) }
//            updatedUnderScore = sb.toString()
//        }
//        underscoreWord=updatedUnderScore
//        return getGameState()
//
//    }

    fun getDrawable(currTries:Int):Int{
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

    fun generateUnderScore(wordSize: Int):String{
        val sb=StringBuilder()
        for (index in 0..wordSize-1){
            sb.append("-");
        }
        return sb.toString();
    }

//    private fun getGameState():GameState{
//        return if(underscoreWord == wordToGuess){
//            GameState.GameWin(wordToGuess)
//        } else if(currTries==maxTries){
//            GameState.GameLost(wordToGuess)
//        }else{
//            drawable=getDrawable()
//            GameState.GameRunning(lettersUsed,underscoreWord,drawable)
//        }
//    }
}
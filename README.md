Tic Tac Toe
===========
There are two implementations of this exercise: Java and Android.

* [Java Implementation](#java-implementation)
* [Android Implementation](#android-implementation)

## Java implementation
The Java implementation is located inside the `tictactoe-java` folder.  To run it, clone this repository, import `tictactoe-java` folder into IntelliJ, and right click the `Main.java` and hit `Run`.

### Objectives

1. Develop models of the game (e.g. Player and Board)
2. Develop logic to determine the next move for best outcome
3. Develop logic to validate a move (e.g. not out of bound, not taken, and etc.)
4. Develop logic to determine if there is a winner for a game

### Game States
Once you build and run it, it is fully automated until it reaches either of the following three states: 

1. You win 
2. You lose
3. Tie



## Android implementation
The Android implementation is located inside the `tictactoe-android` folder.  To run it, clone this repository, import `tictactoe-android` folder into Android Studio, and hit `Run`.

### Objectives

1. Created a mobile app to consumed the logics and modesl built on the Java Implementation
2. Enabled the game to be user interactive

### Features and Highlights

1. The app implements Model-View-Presenter (MVP) design pattern
2. Custom item view background color on `board_item.xml`, yet maintaining Material Design's default ripple effects while being pressed
3. Mimic the color theme of hipmunk

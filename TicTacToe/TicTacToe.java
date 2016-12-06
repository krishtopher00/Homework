/**
* Author: Krish Bhavnani
* Date: 12/5/2016
* This is a 2 player tic tac toe program
*@version 1.0
*/

import java.util.Scanner;
public class TicTacToe{
  public static void main(String[] args){
    int[][] board = boardMaker();
    playGame(board);
  }
  public static int[][] boardMaker(){
    /**
    * This method creates a grid-shaped 2D array of any size
    * Uses a for loop to populate the array with values 0 - the size of the array
    */
    Scanner s = new Scanner(System.in);
    System.out.println("What size board would you like to play on? (Enter 5 for a 5 by 5 board)");
    int size = s.nextInt();
    int[][] board = new int[size][size];
    int val = 0;
    for(int i = 0; i < size; i ++){
      for(int x = 0; x < size; x++){
        board[i][x] = val;
        val++;
      }
    }
    return board;
  }


    public static void boardPrinter(int[][] arr){
      /*
      This method prints an array in a fancy fashion
      It starts with a dashed line at the top then each number of the
      array separated by a pipe character and the appropriate amount
      of spaces to make each number the same amount of digits
      It then separates each row with a dashed line
      Anything greater than a 9 by 9, the dashed line will be too short

      @param arr 2d array of ints to print
      */
  for(int y = 0; y < arr[0].length * 2 + arr[0].length * 2 + arr[0].length; y ++)
    System.out.print("-");
  System.out.println();
  for(int i = 0; i < arr.length; i++){
    System.out.print("|");
    for(int j = 0; j < arr[i].length; j++){
      System.out.print(" ");
      for (int o = 0; o < digits(arr.length * arr.length ) - digits(arr[i][j]); o ++){
        System.out.print(" ");
      }

      if(arr[i][j] == -1){
        System.out.print("X |");
      }else if(arr[i][j] == -2){
        System.out.print("O |");
      }else{
        System.out.print(arr[i][j] + " " + "|");
      }
    }
    System.out.println();

    for(int y = 0; y < arr[i].length * 2 + arr[i].length * 2 + arr[0].length; y ++)
      System.out.print("-");
    System.out.println();
  }
}


  public static int digits(int x){
    /*
      Recursive helper function to find amount of digits in a numberArray using int division
    @param x int to take the amount of digits of
    */
    if(x < 10){
      return 1;
    }else{
      return 1 + digits(x / 10);
    }
  }

  public static boolean checkPosition(int position, int[][] board){
    /*
    * This method is used when the user selects a position to place their mark
    * First it checks if that number is even in the range of the highest and lowest possible number
    * Then it checks if that space is already taken
    * If either one of these are false, the method will return false, meaning that the place
    * the player has chosen is not valid
    * @param position the placeholder number position of where the user wants to player
    * @param board the board on which the user is looking at
    * @return true if the space is valid and false if it isnt
    */
    if(position > (board.length * board.length - 1) || position < 0){
      return false;
    }
    int row = position / board.length;
    int column = position % board.length;
    if(board[row][column] == -1 || board[row][column] == -2){
      return false;
    }else{
      return true;
    }
  }
  public static void playGame(int[][] board){
    /*
    * This method plays the game
    * The first part checks whether the game is over or not and if not, allows player 1 to place their
    * symbol. If the chosen space is available (using checkPosition), they can use it but if it isnt, it prompts
    * them to enter another position
    * This second part checks whether the game is over or not and if not, allows player 2 to place their
    * symbol. If the chosen space is available (using checkPosition), they can use it but if it isnt, allows them
    * to enter another position
    * Checks if the game is over after each person goes
    * @param board the tic tac toe board
    */
    boolean gameOver = false;
    while(!gameOver){
    gameOver = checkWin(board);
    Scanner s = new Scanner(System.in);
      boolean input = false;
      boardPrinter(board);
      while(input == false){
        System.out.println("Player 1, where would you like to place your symbol?");
        int pos = s.nextInt();
        if(checkPosition(pos, board) == false){
          System.out.println("That is not a valid input");
        } else{
          input = true;
          int row = pos / board.length;
          int column = pos % board.length;
          board[row][column] = -1;
        }
      }
      gameOver = checkWin(board);
      if(!gameOver){
        boardPrinter(board);
        input = false;
        while(input == false){
          System.out.println("Player 2, where would you like to place your symbol?");
          int pos = s.nextInt();
          if(checkPosition(pos, board) == false){
            System.out.println("That is not a valid input");
          } else{
            input = true;
            int row = pos / board.length;
            int column = pos % board.length;
            board[row][column] = -2;
          }
        }
      }
      gameOver = checkWin(board);
    }
  }


  public static boolean checkWin(int[][] board){
    /*
    *
    *
    *
    *
    *
    *
    *
    */
    boolean gameOver = false;
    int counter = 0;
    int winCoord1 = -3;
    int winCoord2 = -3;
    //row
    for(int x = 0; x < board.length; x ++){
      counter = 0;
      for(int i = 0; i < board[0].length - 1; i ++){
        if(board[x][i] == board[x][i + 1]){
          counter++;
          winCoord1 = x;
          winCoord2 = i;
        }
      }
      if(counter == board[0].length - 1){
        gameOver = true;
        if(board[winCoord1][winCoord2] == -1){
          System.out.println("Player 1 wins!");
        }else{
          System.out.println("Player 2 wins");
        }
      }
    }

      // column WORKS
      for(int j = 0; j < board[0].length; j ++){
        counter = 0;
        for(int k = 0; k < board.length - 1; k ++){
          if(board[k][j] == board[k + 1][j]){
            counter++;
            winCoord1 = k;
            winCoord2 = j;
          }
        }
        if(counter == board.length - 1){
          gameOver = true;
          if(board[winCoord1][winCoord2] == -1){
            System.out.println("Player 1 wins!");
          }else{
            System.out.println("Player 2 wins");
          }
        }
      }

      //top right to bot left diag
      counter = 0;
      for(int a = 0; a < board.length - 1; a ++){
          if(board[a][a] == board[a + 1][a + 1]){
            counter++;
            winCoord1 = a;
          }
        }
        if(counter == board.length - 1){
          gameOver = true;
          if(board[winCoord1][winCoord1] == -1){
            System.out.println("Player 1 wins!");
          }else{
            System.out.println("Player 2 wins");
          }
        }

        // bot left to top right diag
        int pos = board.length - 1;
        counter = 0;
        for(int b = 0; b < board[0].length-1; b ++){
            if(board[pos][b] == board[pos - 1][b  + 1]){
              counter++;
              winCoord1 = pos;
              winCoord2 = b;
            }
          pos--;
          }
          if(counter == board.length - 1){
            gameOver = true;
            if(board[winCoord1][winCoord2] == -1){
              System.out.println("Player 1 wins!");
            }else{
              System.out.println("Player 2 wins");
            }
          }


        // tie
        counter = 0;
        for(int t = 0; t < board.length; t ++){
          for(int u = 0; u < board[0].length; u++){
            if(board[t][u] == -1 || board[t][u] == -2){
              counter ++;
            }
          }
        }
        if(counter == board.length * board[0].length){
          gameOver = true;
        System.out.println("Tie!");
        }
        return gameOver;
      }
}

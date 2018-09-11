package com.yairn.madandroid.madtic_tac_toe;

import java.util.Random;

public class TicTacToe {

    private Symbols[] board;

    public TicTacToe() {
        reset();
    }

    public boolean setPlayerMove(int pos) {
        if(board[pos] == Symbols.Blank && !isGameOver()) {
            board[pos] = Symbols.X;
            return true;
        }
        return false;
    }

    public int setComputerMove() {
        Random r = new Random();
        int pos = r.nextInt(9);

        if(isGameOver())
            return -1;

        while(board[pos] != Symbols.Blank) {
            pos = r.nextInt(9);
        }

        board[pos] = Symbols.O;
        return pos;
    }

    public boolean isGameOver() {
        return whoWon() != Results.inProcess;
    }

    public Results whoWon(){

        Results result = checkHorizontalVertical();

        if(result == Results.inProcess) {
            result = checkDiagonal();
            if (result == Results.inProcess) {
                result = checkDraw();
            }
        }

        return result;
    }

    private Results checkHorizontalVertical() {
        for(int index = 0; index < 3; index++) {
            if(board[index * 3] == Symbols.X && board[(index * 3) + 1] == Symbols.X && board[(index * 3) + 2] == Symbols.X) {
                return Results.User;
            } else if(board[index] == Symbols.X && board[index + 3] == Symbols.X && board[index + 6] == Symbols.X) {
                return Results.User;
            } else if(board[index * 3] == Symbols.O && board[(index * 3) + 1] == Symbols.O && board[(index * 3) + 2] == Symbols.O) {
                return Results.Computer;
            } else if(board[index] == Symbols.O && board[index + 3] == Symbols.O && board[index + 6] == Symbols.O) {
                return Results.Computer;
            }
        }

        return Results.inProcess;
    }

    private Results checkDiagonal() {
        if(board[0] == Symbols.X && board[4] == Symbols.X && board[8] == Symbols.X) {
            return Results.User;
        } else if(board[2] == Symbols.X && board[4] == Symbols.X && board[6] == Symbols.X) {
            return Results.User;
        } else if(board[0] == Symbols.O && board[4] == Symbols.O && board[8] == Symbols.O) {
            return Results.Computer;
        } else if(board[2] == Symbols.O && board[4] == Symbols.O && board[6] == Symbols.O) {
            return Results.Computer;
        }

        return Results.inProcess;
    }

    private Results checkDraw() {
        for(Symbols symbol: board) {
            if(symbol == Symbols.Blank)
                return Results.inProcess;
        }

        return Results.Draw;
    }


    public void reset() {
        board = new Symbols[9];

        for(int index = 0; index < board.length; index++) {
            board[index] = Symbols.Blank;
        }
    }
}

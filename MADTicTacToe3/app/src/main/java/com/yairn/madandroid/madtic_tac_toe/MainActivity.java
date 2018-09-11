package com.yairn.madandroid.madtic_tac_toe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TicTacToe game;
    private Button[] buttons;

    private TextView statusText;
    private Button resetButton;

    private boolean isHumanTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new TicTacToe();

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        statusText = findViewById(R.id.statusText);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setEnabled(false);

        buttons = new Button[] {button0, button1, button2,
                                button3, button4, button5,
                                button6, button7, button8};
        resetUI();
        isHumanTurn = true;
    }


    public void onClickButton0(View view) {
        playerMove(view, 0);
    }

    public void onClickButton1(View view) {
        playerMove(view, 1);
    }

    public void onClickButton2(View view) {
        playerMove(view, 2);
    }

    public void onClickButton3(View view) {
        playerMove(view, 3);
    }

    public void onClickButton4(View view) {
        playerMove(view, 4);
    }

    public void onClickButton5(View view) {
        playerMove(view, 5);
    }

    public void onClickButton6(View view) {
        playerMove(view, 6);
    }

    public void onClickButton7(View view) {
        playerMove(view, 7);
    }

    public void onClickButton8(View view) {
        playerMove(view, 8);
    }

    private void updateUI() {
        Results result = game.whoWon();

        switch (result) {
            case Draw:
                statusText.setText("Draw!");
                gameOverUIUpdate();
                break;
            case User:
                statusText.setText("You Win!");
                gameOverUIUpdate();
                break;
            case Computer:
                statusText.setText("You Lose!");
                gameOverUIUpdate();
                break;
            default:
                statusText.setText("Keep Playing!");
        }
    }

    private void playerMove(View view, int pos) {
        if(isHumanTurn) {
            if(game.setPlayerMove(pos)) {
                isHumanTurn = false;
                view.setEnabled(false);
                view.setBackgroundResource(R.drawable.x);
                updateUI();
                computerMove();
            }
        }
    }

    private void computerMove() {
        int pos;

        if(!isHumanTurn) {
            pos = game.setComputerMove();
            if(pos > -1) {
                buttons[pos].setBackgroundResource(R.drawable.o);
                buttons[pos].setEnabled(false);
            }
            updateUI();
            isHumanTurn = true;
        }
    }


    private void gameOverUIUpdate() {
        for(Button button: buttons) {
            button.setEnabled(false);
        }

        resetButton.setEnabled(true);
    }

    public void onClickResetButton(View view) {
        if(game.isGameOver())
        {
            game.reset();
            resetUI();
            isHumanTurn = true;
        }
    }

    private void resetUI() {
        for(Button button: buttons) {
            button.setEnabled(true);
            button.setBackgroundColor(Color.GRAY);

        }

        statusText.setText("Keep Playing!");
        resetButton.setEnabled(false);
    }
}

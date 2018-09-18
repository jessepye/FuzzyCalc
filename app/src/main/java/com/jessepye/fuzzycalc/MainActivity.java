//TODO: don't allow rotation
//TODO: hide title bar
//TODO: fix fonts and sizes
//TODO: add deg/rad button
//TODO: fix superscript on sin^-1 etc.
//TODO: finish adding buttons (...after groking the eval method)
//TODO: figure out how to add a logo
//TODO: don't use fixed text sizes


package com.jessepye.fuzzycalc;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName(); //used for Logs

    private boolean currentlyGuessing = false;

    Button btn_sqr, btn_backsp, btn_clr,
            btn_exp, btn_opn_paren, btn_cls_paren, btn_div,
            btn_7, btn_8, btn_9, btn_mul,
            btn_4, btn_5, btn_6, btn_sub,
            btn_1, btn_2, btn_3, btn_add,
            btn_0, btn_dot, btn_ent;

    TextView calculationWindow;
    TextView resultWindow;
    TextView guessWindow;

    //At first, I thought that storing the calculation as a string was clunky
    //e.g. when a user types "sin(" then "del" it should delete the whole sin token, rather than '(' then 'n' then 'i' etc...
    //I thought I would store the calculation input as an ArrayList of button inputs
    //But then I remembered that I wanted the user to be able to paste a string into the calculation window from outside the app
    //Thus everything has to be able to parse/understand pure string inputs
    //Maybe I'll come back to this later...
    /*
    ArrayList<Button> calculationWindowList = new ArrayList<Button>();
    ArrayList<Button> guessWindowList = new ArrayList<Button>();
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //TODO: vibrate on PRESS rather than on CLICK to feel more responsive
        final Vibrator vibe = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        final int vibeTime = 8;

        btn_clr = findViewById(R.id.btn_clr);
        btn_backsp = findViewById(R.id.btn_backsp);
        btn_exp = findViewById(R.id.btn_exp);
        btn_sqr = findViewById(R.id.btn_sqr);
        btn_opn_paren = findViewById(R.id.btn_opn_paren);
        btn_cls_paren = findViewById(R.id.btn_cls_paren);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_dot = findViewById(R.id.btn_dot);
        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);
        btn_ent = findViewById(R.id.btn_ent);
        calculationWindow = (TextView) findViewById(R.id.calculationWindow);
        //calculationWindow.setMovementMethod(new ScrollingMovementMethod());
        resultWindow = (TextView) findViewById(R.id.resultWindow);
        guessWindow = (TextView) findViewById(R.id.guessWindow);

        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);

                if(currentlyGuessing){
                    if(guessWindow.getText().toString().isEmpty()){
                        calculationWindow.setText("");
                        resultWindow.setText("");
                        currentlyGuessing = false;
                        calculationWindow.setBackgroundColor(getResources().getColor(R.color.fieldSelected));
                        guessWindow.setBackgroundColor(getResources().getColor(R.color.fieldNotSelected));

                    }
                    else {
                        guessWindow.setText("");
                    }
                }
                else{
                    calculationWindow.setText("");
                    resultWindow.setText("");
                    guessWindow.setText("");
                }
            }
        });

        //TODO: allow the user to delete many characters quickly by holding the backspace button
        btn_backsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                if (currentlyGuessing) {
                    Log.v(TAG, "Guess window is currently: " + guessWindow.getText().toString());
                    if (guessWindow.getText() != null && guessWindow.getText().length() > 0) {
                        guessWindow.setText(guessWindow.getText().toString().substring(0, guessWindow.getText().length() - 1));
                    }
                } else {
                    Log.v(TAG, "Calculation window is currently: " + calculationWindow.getText().toString());
                    if (calculationWindow.getText() != null && calculationWindow.getText().length() > 0) {
                        calculationWindow.setText(calculationWindow.getText().toString().substring(0, calculationWindow.getText().length() - 1));
                    }
                }
            }
        });

        btn_sqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("^(2)");
            }
        });

        btn_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("^");
            }
        });

        btn_opn_paren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("(");
            }
        });

        btn_cls_paren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput(")");
            }
        });

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("0");
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("2");
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("9");
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput(".");
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput(" + ");
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput(" - ");
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput(" * ");
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput(" / ");
            }
        });

        btn_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                if (!currentlyGuessing) {
                    currentlyGuessing = true;
                    calculationWindow.setBackgroundColor(getResources().getColor(R.color.fieldNotSelected));
                    guessWindow.setBackgroundColor(getResources().getColor(R.color.fieldSelected));
                    guessWindow.setTypeface(null, Typeface.ITALIC);
                    guessWindow.setText(getString(R.string.guess_hint));
                } else {
                    if (guessIsCloseEnough()) {
                        resultWindow.setText("" + eval(calculationWindow.getText().toString())); //TODO: omg figure out why I need the "" +
                        currentlyGuessing = false;
                        calculationWindow.setBackgroundColor(getResources().getColor(R.color.fieldSelected)); //TODO: probably should be using colors.xml or similar
                        guessWindow.setBackgroundColor(getResources().getColor(R.color.fieldNotSelected));
                    }
                }
            }
        });
    }

    private boolean guessIsCloseEnough() {
        if (guessWindow.getText().toString().isEmpty() || calculationWindow.getText().toString().isEmpty()) {
            return false;
        }
        double guess = Double.parseDouble(guessWindow.getText().toString());
        double result = eval(calculationWindow.getText().toString());
        Log.v(TAG, "Comparing guess " + guess + " against real answer " + result);
        if (result < 0) {
            return guess * 1.25 < result && result < guess * 0.75;
        } else {
            return guess * 0.75 < result && result < guess * 1.25;
        }
    }

    private void handleButtonInput(String s) {
        Log.v(TAG, "Calling handleButtonInput: " + s);
        if (currentlyGuessing) {
            Log.v(TAG,"Comparing "+guessWindow.getText().toString()+" against "+getString(R.string.guess_hint));
            if(guessWindow.getText().toString().equals(getString(R.string.guess_hint))) {
                guessWindow.setTypeface(null, Typeface.NORMAL);
                guessWindow.setText(s);
            }
            else {
                guessWindow.append(s);
            }
        } else {
            //Did we just finish a calculation? If so, "1" "2" "3" clear the screen and start a new one; "+" "-" clear the screen, set calculationWindow to last answer, then append the "+"
            if(!calculationWindow.getText().toString().isEmpty() && !resultWindow.getText().toString().isEmpty() && !guessWindow.getText().toString().isEmpty()){
                if(s.equals(" + ") || s.equals(" - ") || s.equals(" * ") || s.equals(" / ")){
                    calculationWindow.setText(resultWindow.getText().toString()+s);
                    resultWindow.setText("");
                    guessWindow.setText("");
                }
                else{
                    calculationWindow.setText(s);
                    resultWindow.setText("");
                    guessWindow.setText("");
                }
            }
            else {
                calculationWindow.append(s);
            }
        }
    }

    //TODO: grok this and implement a better version
    private static double eval(final String str) {
        if (str.isEmpty()) {
            return Double.NaN;
        }
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
//TODO: don't allow rotation
//TODO: hide title bar
//TODO: pretty colors
//TODO: fix fonts and sizes
//TODO: add deg/rad button
//TODO: fix superscript on sin^-1 etc.
//TODO: finish adding buttons (...after groking the eval method)
//TODO: figure out how to add a logo
//TODO: don't use fixed text sizes


package com.jessepye.fuzzycalc;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName(); //used for Logs

    private boolean currentlyGuessing = false;

    Button btn_clr,
            btn_backsp,
            btn_sin, btn_cos, btn_tan, btn_exp,
            btn_opn_paren, btn_cls_paren, btn_div,
            btn_7, btn_8, btn_9, btn_mul,
            btn_4, btn_5, btn_6, btn_sub,
            btn_1, btn_2, btn_3, btn_add,
            btn_0, btn_dot, btn_ent;

    TextView calculationWindow;
    TextView resultWindow;
    TextView guessWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Button> calculationWindowList = new ArrayList<Button>();
        ArrayList<Button> guessWindowList = new ArrayList<Button>();

        btn_clr = findViewById(R.id.btn_clr);
        btn_backsp = findViewById(R.id.btn_backsp);
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_tan = findViewById(R.id.btn_tan);
        btn_exp = findViewById(R.id.btn_exp);
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
        calculationWindow.setMovementMethod(new ScrollingMovementMethod());
        resultWindow = (TextView) findViewById(R.id.resultWindow);
        guessWindow = (TextView) findViewById(R.id.guessWindow);

        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.setText("");
                resultWindow.setText("");
                guessWindow.setText("");

                currentlyGuessing=false;
                calculationWindow.setBackgroundColor(getResources().getColor(R.color.fieldSelected));
                guessWindow.setBackgroundColor(getResources().getColor(R.color.fieldNotSelected));

            }
        });

        btn_backsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentlyGuessing){
                    Log.v(TAG,"Guess window is currently: "+guessWindow.getText().toString());
                    if (guessWindow.getText() != null && guessWindow.getText().length() > 0) {
                        guessWindow.setText( guessWindow.getText().toString().substring(0, guessWindow.getText().length() - 1) );
                    }
                }else{
                    Log.v(TAG,"Calculation window is currently: "+calculationWindow.getText().toString());
                    if (calculationWindow.getText() != null && calculationWindow.getText().length() > 0) {
                        calculationWindow.setText( calculationWindow.getText().toString().substring(0, calculationWindow.getText().length() - 1) );
                    }
                }
            }
        });

        btn_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("sin(");
            }
        });

        btn_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("cos(");
            }
        });

        btn_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("tan(");
            }
        });

        btn_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("^");
            }
        });

        btn_opn_paren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("(");
            }
        });

        btn_cls_paren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput(")");
            }
        });

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("0");
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("2");
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput("9");
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput(".");
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput(" + ");
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput(" - ");
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput(" * ");
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonInput(" / ");
            }
        });

        btn_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentlyGuessing) {
                    currentlyGuessing = true;
                    calculationWindow.setBackgroundColor(getResources().getColor(R.color.fieldNotSelected));
                    guessWindow.setBackgroundColor(getResources().getColor(R.color.fieldSelected));
                    guessWindow.setText("");
                } else {
                    if(guessIsCloseEnough()) {
                        resultWindow.setText("" + eval(calculationWindow.getText().toString())); //TODO: omg figure out why I need the "" +
                        currentlyGuessing=false;
                        calculationWindow.setBackgroundColor(getResources().getColor(R.color.fieldSelected)); //TODO: probably should be using colors.xml or similar
                        guessWindow.setBackgroundColor(getResources().getColor(R.color.fieldNotSelected));
                    }
                }
            }
        });
    }

    private boolean guessIsCloseEnough() {
        if(guessWindow.getText().toString().isEmpty() || calculationWindow.getText().toString().isEmpty()){
            return false;
        }
        double guess = Double.parseDouble(guessWindow.getText().toString());
        double result = eval(calculationWindow.getText().toString());
        Log.v(TAG,"Comparing guess " + guess + " against real answer " + result);
        if(result<0) {
            return guess*1.25 < result && result < guess*0.75;
        } else{
            return guess*0.75 < result && result < guess*1.25;
        }
    }

    private void handleButtonInput(String s) {
        Log.v(TAG, "Calling handleButtonInput: " + s);
        if (currentlyGuessing) {
            guessWindow.append(s);
        } else {
            calculationWindow.append(s);
        }
    }

    //TODO: grok this and implement a better version
    private static double eval(final String str) {
        if (str.isEmpty()){
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
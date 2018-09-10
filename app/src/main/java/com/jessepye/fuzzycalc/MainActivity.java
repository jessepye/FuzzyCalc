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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName(); //used for Logs

    private boolean currentlyGuessing = false;

    Button  btn_clr,
            btn_sin, btn_cos, btn_tan, btn_exp,
            btn_opn_paren, btn_cls_paren, btn_div,
            btn_7  , btn_8  , btn_9  , btn_mul,
            btn_4  , btn_5  , btn_6  , btn_sub,
            btn_1  , btn_2  , btn_3  , btn_add,
            btn_0  , btn_dot,          btn_ent;

    TextView calculationWindow;
    TextView resultWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Map<Button, Integer> buttonMap = new HashMap<>()

        btn_clr = (Button) findViewById(R.id.btn_clr);
        btn_sin = (Button) findViewById(R.id.btn_sin);
        btn_cos = (Button) findViewById(R.id.btn_cos);
        btn_tan = (Button) findViewById(R.id.btn_tan);
        btn_exp = (Button) findViewById(R.id.btn_exp);
        btn_opn_paren = (Button) findViewById(R.id.btn_opn_paren);
        btn_cls_paren = (Button) findViewById(R.id.btn_cls_paren);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_ent = (Button) findViewById(R.id.btn_ent);
        calculationWindow = (TextView) findViewById(R.id.calculationWindow);
        calculationWindow.setMovementMethod(new ScrollingMovementMethod());
        resultWindow = (TextView) findViewById(R.id.resultWindow);

        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.setText("");
            }
        });

        btn_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("sin(");
            }
        });

        btn_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("cos(");
            }
        });

        btn_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("tan(");
            }
        });

        btn_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("^");
            }
        });

        btn_opn_paren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("(");
            }
        });

        btn_cls_paren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append(")");
            }
        });

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("0");
                //Log.v(TAG, "Writing 0 to calculationWindow");
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("1");
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("2");
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("3");
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("4");
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("5");
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("6");
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("7");
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("8");
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("9");
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append(".");
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("+");
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("-");
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("*");
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.append("/");
            }
        });

        btn_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resultWindow.setText( "" + eval(calculationWindow.getText().toString()) ) ; //TODO: omg figure out why I need the ""+

                //We need to scroll to the bottom of the textView:
                final int scrollAmount = calculationWindow.getLayout().getLineTop(calculationWindow.getLineCount()) - calculationWindow.getHeight();
                // if there is no need to scroll, scrollAmount will be <=0
                if (scrollAmount > 0)
                    calculationWindow.scrollTo(0, scrollAmount);
                else
                    calculationWindow.scrollTo(0, 0);
            }
        });
    }



    //TODO: grok this and implement a better version
    private static double eval(final String str) {
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

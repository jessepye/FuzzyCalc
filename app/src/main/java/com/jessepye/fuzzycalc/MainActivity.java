//TODO: don't allow rotation
//TODO: hide title bar
//TODO: fix fonts and sizes
//TODO: fix superscript on x^2
//TODO: finish (-)
//TODO: figure out how to add a logo
//TODO: don't use fixed text sizes


package com.jessepye.fuzzycalc;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getName(); //used for Logs



    private boolean currentlyGuessing = false;

    Button btn_sqr, btn_backsp, btn_clr,
            btn_exp, btn_opn_paren, btn_cls_paren, btn_div,
            btn_7, btn_8, btn_9, btn_mul,
            btn_4, btn_5, btn_6, btn_sub,
            btn_1, btn_2, btn_3, btn_add,
            btn_0, btn_dot, btn_neg, btn_ent;

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
        btn_neg = findViewById(R.id.btn_neg);
        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);
        btn_ent = findViewById(R.id.btn_ent);
        calculationWindow = (TextView) findViewById(R.id.calculationWindow);
        //calculationWindow.setMovementMethod(new ScrollingMovementMethod());
        resultWindow = (TextView) findViewById(R.id.resultWindow);
        guessWindow = (TextView) findViewById(R.id.guessWindow);

        /*
        btn_clr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    vibe.vibrate(vibeTime);
                    return true;
                }
                return false;
            }
        });
  */

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

        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(vibeTime);
                handleButtonInput("-");
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
                        DecimalFormat df = new DecimalFormat("#.##########");
                        resultWindow.setText("" + df.format(eval(calculationWindow.getText().toString()))); //TODO: omg figure out why I need the "" +
                        currentlyGuessing = false;
                        calculationWindow.setBackgroundColor(getResources().getColor(R.color.fieldSelected)); //TODO: probably should be using colors.xml or similar
                        guessWindow.setBackgroundColor(getResources().getColor(R.color.fieldNotSelected));

                        //TODO: I wonder if this ObjectAnimator should be declared somewhere else?
                        ObjectAnimator colorFade = ObjectAnimator.ofObject(resultWindow, "backgroundColor", new ArgbEvaluator(), getResources().getColor(R.color.correctGuessColor), getResources().getColor(R.color.fieldNotSelected));
                        colorFade.setDuration(750);
                        colorFade.start();
                    }
                    else{
                        ObjectAnimator colorFade = ObjectAnimator.ofObject(guessWindow, "backgroundColor", new ArgbEvaluator(), getResources().getColor(R.color.incorrectGuessColor), getResources().getColor(R.color.fieldSelected));
                        colorFade.setDuration(750);
                        colorFade.start();
                    }
                }
            }
        });
    }


    //TODO: figure out how to implement this (one method for all buttons)
    // see: https://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android/25905182
    /*
    public boolean onTouch(View v, MotionEvent event){
        Log.v("onTouch","Calling onTouch method");
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            final Vibrator vibe = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
            final int vibeTime = 8;
            vibe.vibrate(vibeTime);
            return true;
        }
        return false;
    }
    */
    private boolean guessIsCloseEnough() {
        if (guessWindow.getText().toString().isEmpty() || calculationWindow.getText().toString().isEmpty()) {
            return false;
        }
        double guess = Double.parseDouble(guessWindow.getText().toString());
        double result = eval(calculationWindow.getText().toString());
        Log.v(TAG, "Comparing guess " + guess + " against real answer " + result);

        double errorRatio = Math.abs((guess-result)/result);
        double absoluteError = Math.abs(guess-result);
        Log.v(TAG,"errorRatio = "+errorRatio+"; absoluteError = "+absoluteError);
        return (errorRatio<=0.10 || absoluteError<=1);
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
            //Did we just finish a calculation?
            // If so, "1" "2" "3" clear the screen and start a new one;
            // "+" "-" clear the screen, set calculationWindow to last answer, then append the "+" "-"
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

    private static double eval(String str){
        //TODO: do input validation, e.g. make sure parenthesis are valid, nothing like ())(

        double firstNumber;
        boolean firstNumberIsNegative=false;
        double secondNumber;
        boolean secondNumberIsNegative=false;

        //used to track individual numbers e.g. firstNumber=Double.parseDouble(str.subString(beginIndex,endIndex))
        int beginIndex = 0;
        int endIndex = 0;

        //used when keeping track of what to "cut out," before calling eval recursively
        int beginCalcIndex = 0;

        Log.v(TAG, "Calling eval with: " + str);
        if(str.indexOf('(')!=-1){
            return Double.NaN;
        }
        else if(str.indexOf('√')!=-1){
            return Double.NaN;
        }
        else if(str.indexOf('^')!=-1){
            return Double.NaN;
        }

        //Do the multiplication and division; only * / + - symbols remain at this point
        //Examples to consider:
        // 12*34
        // 12 * 34
        // 12 + 34 * 56
        // 12 + - 34 * 56      shouldn't have to worry about the '-' in this one, will get taken care of in the '+' '-' evaluator below
        // 12 + 34 * - 56      this time I'll have to take care of the '-'
        else if(str.indexOf('*')!=-1 || str.indexOf('/')!=-1){

            boolean doingMultiplication;
            //find the first instance of * or / in the string
            if(str.indexOf('*')==-1 || (str.indexOf('/')!=-1 && str.indexOf('/')<str.indexOf('*')) ) {
                endIndex=str.indexOf('/');
                doingMultiplication=false;
            }
            else{
                endIndex=str.indexOf('*');
                doingMultiplication=true;
            }

            //Now work backwards to find firstNumber
            beginIndex=endIndex-1;

            //  first jump over any whitespace
            while(str.charAt(beginIndex)==' ') beginIndex--;

            //  now find the beginning of the digits
            //Log.v(TAG,String.valueOf(beginIndex>0)+String.valueOf(str.charAt(beginIndex)>='0')+String.valueOf(str.charAt(beginIndex)<='9'));
            while( beginIndex>0 && ((str.charAt(beginIndex)>='0' && str.charAt(beginIndex)<='9') || str.charAt(beginIndex)=='.')) beginIndex--;

            //  finally grab the value of firstNumber.  Will this ever be negative? Don't think so, if it is, the '+' '-' portion of eval below should take care of it
            firstNumber=Double.parseDouble(str.substring(beginIndex,endIndex));

            //now keep track of where firstNumber began, and start looking for secondNumber
            beginCalcIndex=beginIndex;
            endIndex++; //jump past the '*' or the '-'
            beginIndex=endIndex;

            while(str.charAt(beginIndex)==' ' || str.charAt(beginIndex)=='+' || str.charAt(beginIndex)=='-'){
                if(str.charAt(beginIndex)=='-') secondNumberIsNegative = !secondNumberIsNegative;
                beginIndex++;
            }

            //Now to find the end of secondNumber
            endIndex=beginIndex;
            while(endIndex<str.length() && ((str.charAt(endIndex)>='0' && str.charAt(endIndex)<='9') || str.charAt(endIndex)=='.')) endIndex++;

            //finally grab the value of secondNumber
            if(endIndex>=str.length()){
                secondNumber=Double.parseDouble(str.substring(beginIndex));
            }
            else{
                secondNumber=Double.parseDouble(str.substring(beginIndex,endIndex));
            }
            if(secondNumberIsNegative) secondNumber = -secondNumber;

            if(doingMultiplication)
                return eval(str.substring(0,beginCalcIndex)+String.valueOf(firstNumber*secondNumber)+str.substring(endIndex));
            else
                return eval(str.substring(0,beginCalcIndex)+String.valueOf(firstNumber/secondNumber)+str.substring(endIndex));

        }

        // there's only + and - left; compute the first operation and run eval again.
        // (this one gets ugly because the - symbol is overloaded to show subtraction and negatives)
        else if(str.indexOf('+')!=-1 || str.indexOf('-')!=-1){


            //first move past any whitespace at the beginning
            while(str.charAt(beginIndex)==' ') beginIndex++;

            //does the number lead with a - ?
            if(str.charAt(beginIndex)=='-'){
                firstNumberIsNegative = !firstNumberIsNegative;
                beginIndex++;
            }

            //sometimes there is whitespace after the -
            while(str.charAt(beginIndex)==' ') beginIndex++;

            //now find endIndex of the first number;
            endIndex=beginIndex;
            while(endIndex<str.length() && ((str.charAt(endIndex)>='0'&&str.charAt(endIndex)<='9') || str.charAt(endIndex)=='.')) endIndex++;

            //now we can "gobble up" any trailing whitespace after the first number
            while(endIndex<str.length() && (str.charAt(endIndex)==' ')) endIndex++;

            //finally we can get the value of firstNumber

            //if firstNumber goes to the end of the string, we are done, return that number.
            if(endIndex>=str.length()){
                firstNumber=Double.parseDouble(str.substring(beginIndex));
                if (firstNumberIsNegative) firstNumber = -firstNumber;
                return firstNumber;
            }
            else{
                firstNumber=Double.parseDouble(str.substring(beginIndex,endIndex));
                if(firstNumberIsNegative) firstNumber = -firstNumber;
            }

            //Now find the beginning of the secondNumber
            // Some examples:
            // 12 + 23
            // 12 - 23
            // 12 + -23
            // 12 - -23
            beginIndex=endIndex;
            while(str.charAt(beginIndex)==' ' || str.charAt(beginIndex)=='+' || str.charAt(beginIndex)=='-'){
                if(str.charAt(beginIndex)=='-') secondNumberIsNegative = !secondNumberIsNegative;
                beginIndex++;
            }

            //Now to find the end of secondNumber
            endIndex=beginIndex;
            while(endIndex<str.length() && ((str.charAt(endIndex)>='0' && str.charAt(endIndex)<='9') || str.charAt(endIndex)=='.')) endIndex++;

            //finally grab the value of secondNumber
            if(endIndex>=str.length()){
                secondNumber=Double.parseDouble(str.substring(beginIndex));
            }
            else{
                secondNumber=Double.parseDouble(str.substring(beginIndex,endIndex));
            }
            if(secondNumberIsNegative) secondNumber = -secondNumber;

            //FINALLY add the two numbers and run eval again recursively
            double result=firstNumber+secondNumber;
            if(endIndex<=str.length()-1) return eval(String.valueOf(result)+str.substring(endIndex));
            else return result;
        }

        //no symbols, only digits left:
        else if(str.indexOf('0')!=-1 ||
                str.indexOf('1')!=-1 ||
                str.indexOf('2')!=-1 ||
                str.indexOf('3')!=-1 ||
                str.indexOf('4')!=-1 ||
                str.indexOf('5')!=-1 ||
                str.indexOf('6')!=-1 ||
                str.indexOf('7')!=-1 ||
                str.indexOf('8')!=-1 ||
                str.indexOf('9')!=-1){
            return Double.parseDouble(str);
        }

        //At this point, we don't have any recognised symbols or digits
        return Double.NaN;
    }

    //TODO: grok this and implement a better version
    private static double evalOld(final String str) {
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
package com.jessepye.fuzzycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName(); //used for Logs

    Button btn_0, btn_1, btn_ent;
    TextView calculationWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_ent = (Button) findViewById(R.id.btn_ent);
        calculationWindow = (TextView) findViewById(R.id.calculationWindow);
        calculationWindow.setMovementMethod(new ScrollingMovementMethod());

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.setText(calculationWindow.getText() + "0");
                //Log.v(TAG, "Writing 0 to calculationWindow");
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.setText(calculationWindow.getText() + "1");
            }
        });

        btn_ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationWindow.setText(calculationWindow.getText() + "\n");

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
}

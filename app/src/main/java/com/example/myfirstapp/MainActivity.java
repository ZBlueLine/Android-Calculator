package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Character.isDigit;
import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity
{
    private boolean Eps(double a, double b)
    {
        double eps = 1e-8;
        if(abs(a-b)<eps)
            return true;
        else
            return false;
    }

    //?????????
    private String calculate(String a, String b)
    {
        int pos = 0;
        char bmeth = 'e';
        char ameth = '+';
        double na, nb;
        if(!isDigit(a.charAt(0)))
        {
            ameth = a.charAt(0);
            a = a.substring(1, a.length());
        }
        bmeth = b.charAt(0);

        b = b.substring(1, b.length());
        if(bmeth != 'e')
        {
            na = new Double(a);
            if(ameth == '-')
                na = -na;

            nb = new Double(b);
            if(bmeth == '+')
                return ""+(na+nb);

            if(bmeth == '-')
                return ""+(na-nb);

            if(bmeth == 'X')
                return ""+(na*nb);

            if(bmeth == '/')
                return ""+(na/nb);
        }
        return "err";
    }

    //?????????????
    private String countsum(String a,String b)
    {
        String temp = b.substring(1, b.length());
        double douB = new Double(temp);
        if(b.charAt(0)=='/'&&Eps(douB, 0.0))
            return "err";
        return calculate(a, b);
    }

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Zzy's Calculator");
    }

    public void clicknumber(View view)
    {
        Button button = (Button) view;
        TextView Counttext = findViewById(R.id.Count_textView);
        String number = Counttext.getText().toString();
        number += button.getText().toString();
        Counttext.setText(number);
    }

    public void Point(View view){
        Button button = (Button) view;
        TextView Counttext = findViewById(R.id.Count_textView);
        String number = Counttext.getText().toString();
        for(int i = 0; i < number.length(); ++i)
        {
            if (number.charAt(i) == '.')
            {
                return;
            }
        }
        if(number.length() == 0||(number.length()==1&&!isDigit(number.charAt(0))))
        {
            Counttext.setText(number+"0.");
        }
        else
        {
            number += button.getText().toString();
            Counttext.setText(number);
        }
    }

    public void clearnumber(View view)
    {
        TextView Counttext = findViewById(R.id.Count_textView);
        TextView ram = findViewById(R.id.Ram);
        Counttext.setText("");
        ram.setText("");
    }


    public void Backspace(View view)
    {
        TextView Counttext = findViewById(R.id.Count_textView);
        String number = Counttext.getText().toString();
        if(number.length() == 0)
            return;
        number = number.substring(0, number.length()-1);
        Counttext.setText(number);
    }

    public void sum(View view)
    {
        TextView ram = findViewById(R.id.Ram);
        TextView Counttext = findViewById(R.id.Count_textView);
        String a = ram.getText().toString();
        String b = Counttext.getText().toString();
        if(a.length()!=0&&b.length()!=0)
        {
            if(isDigit(b.charAt(0)))
            {
                ram.setText(b);
                Counttext.setText("");
                return;
            }
            else if(b.length()==1)
            {
                Counttext.setText("");
                return;
            }
            ram.setText(countsum(a, b));
            Counttext.setText("");
        }
        return;
    }

    public void Cul(View view)
    {
        TextView ram = findViewById(R.id.Ram);
        TextView Counttext = findViewById(R.id.Count_textView);
        String sum = ram.getText().toString();
        String number = Counttext.getText().toString();
        Button button = (Button) view;
        String Operator = button.getText().toString();
        String ans = "";
        if (number.length() == 0 && sum.length() == 0)
        {
            return;
        }
        else if (sum.length() == 0 && number.length() != 0)
        {
            ram.setText(number);
            Counttext.setText(Operator);
        }
        else if(sum.length() != 0 && number.length() == 0)
        {
            if(sum == "err")
            {
                return;
            }
            else
            {
                Counttext.setText(Operator);
            }
        }
        else if(sum.length()!=0&&number.length()!=0)
        {
            if(isDigit(number.charAt(0)))
            {
                ram.setText(number);
                Counttext.setText(Operator);
            }
            else if(number.length() != 1)
            {
                ans = countsum(sum, number);
                ram.setText(ans);
                if(ans == "err")
                    Counttext.setText("");
                else
                    Counttext.setText(Operator);
            }
        }
    }
}

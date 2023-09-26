package com.rohit.prefixinfixpostfixconverter;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
public class I2PP extends AppCompatActivity {
    public Set<Character> symSet;
    public I2PP(){
        symSet = new LinkedHashSet<>();
        symSet.add('+');
        symSet.add('*');
        symSet.add('/');
        symSet.add('-');
        symSet.add('^');
        symSet.add(')');
        symSet.add('(');
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i2_pp);
        setSupportActionBar(findViewById(R.id.my_toolbar));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Infix to Prefix/Postfix Converter");
        EditText in=findViewById(R.id.infixIn);
        TextView pre=findViewById(R.id.prefixOut);
        TextView post=findViewById(R.id.postfixOut);
        Button convert=findViewById(R.id.convert);
        convert.setOnClickListener(view -> {
            String infix=in.getText().toString();
            pre.setText(convertI2Pre(infix));
            post.setText(convertI2Post(infix));
        });
    }
    public String convertI2Pre(String in)
    {
        String rev = reverse(in);
        int len = rev.length();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i<len; i++)
        {
            if(rev.charAt(i) == '(')
                str.append(""+')');
            else if (rev.charAt(i) == ')')
                str.append(""+'(');
            else
                str.append("").append(rev.charAt(i));
        }
        return convertI2Post(str.toString());
    }
    public String convertI2Post(String in)
    {
        Stack<Character> sym = new Stack<>();
        StringBuilder strOut = new StringBuilder();
        int len = in.length();
        for (int i = 0; i < len; i++) {
            if (symSet.contains(in.charAt(i))) {
                if (in.charAt(i) == '^') {
                    if (!sym.empty() && sym.peek() == '^') {
                        strOut.append('^');
                    } else {
                        sym.push('^');
                    }
                } else if (in.charAt(i) == '*' || in.charAt(i) == '/') {
                    if (!sym.empty()) {
                        while (!sym.empty() && (sym.peek() == '^' || sym.peek() == '*' || sym.peek() == '/')) {
                            strOut.append(sym.pop());
                        }
                        sym.push(in.charAt(i));
                    } else {
                        sym.push(in.charAt(i));
                    }
                } else if (in.charAt(i) == '+' || in.charAt(i) == '-') {
                    if (!sym.empty()) {
                        while (!sym.empty() && (sym.peek() == '^' || sym.peek() == '*' || sym.peek() == '/'
                                || in.charAt(i) == '+' || in.charAt(i) == '-')) {
                            if (sym.peek() == '(') {
                                break;
                            }
                            strOut.append(sym.pop());
                        }
                        sym.push(in.charAt(i));
                    } else {
                        sym.push(in.charAt(i));
                    }
                } else if (in.charAt(i) == ')') {
                    if (!sym.empty()) {
                        while (!sym.empty() && sym.peek() != '(') {
                            strOut.append(sym.pop());
                        }
                        if (!sym.empty() && sym.peek() == '(') {
                            sym.pop(); // Remove the corresponding '('
                        }
                    }
                }
                else if (in.charAt(i) == '(')
                {
                    sym.push(in.charAt(i));
                }
            } else {
                strOut.append(in.charAt(i));
            }
        }
        while (!sym.empty()) {
            strOut.append(sym.pop());
        }
        return strOut.toString();
    }
    public String reverse(String pre)
    {
        StringBuilder str = new StringBuilder();
        int len = pre.length();
        for (int i = 0; i<len;i++) {
            str.insert(0, "" + pre.charAt(i));
        }
        return str.toString();
    }
}








/*
package com.rohit.prefixinfixpostfixconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class I2PP extends AppCompatActivity {

    public Set<Character> symSet;
    public I2PP(){
        symSet = new LinkedHashSet<Character>();
        symSet.add('+');
        symSet.add('*');
        symSet.add('/');
        symSet.add('-');
        symSet.add('^');
        symSet.add(')');
        symSet.add('(');
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i2_pp);
        EditText in=findViewById(R.id.infixIn);
        TextView pre=findViewById(R.id.prefixOut);
        TextView post=findViewById(R.id.postfixOut);
        Button convert=findViewById(R.id.convert);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String infix=in.getText().toString();
                pre.setText(convertI2Pre(infix));
                post.setText(convertI2Post(infix));
            }
        });
    }
    public String convertI2Pre(String in)
    {
        String rev = reverse(in);
        int len = rev.length();
        String str = "";
        for (int i = 0; i<len; i++)
        {
            if(rev.charAt(i) == '(')
                str += ')';
            else if (rev.charAt(i) == ')')
                str += '(';
            else
                str += rev.charAt(i);
        }
        return convertI2Post(str);
    }
    public String convertI2Post(String in)
    {
        Stack<Character> sym = new Stack<>();
        String strOut = "";
        int len = in.length();
        for(int i = 0; i< len;i++)
        {
            if(symSet.contains(in.charAt(i)))
            {
                if(in.charAt(i) == '^')
                {
                    if(sym.peek() == '^')
                    {
                        strOut = strOut + '^';
                    }
                    else {
                        sym.push('^');
                    }
                }
                else if(in.charAt(i) == '*' || in.charAt(i) == '/' )
                {
                        if (sym.peek() == '^' || sym.peek() == '*' || sym.peek() == '/') {
                            while(sym.peek() != '+' || sym.peek() != '-' || sym.peek() != '(') {
                                strOut = strOut + sym.pop();
                            }
                            sym.push(in.charAt(i));
                        }
                        else{
                            sym.push(in.charAt(i));
                        }
                }
                else if(in.charAt(i) == '+' || in.charAt(i) == '-' )
                {
                    if (sym.peek() == '^' || sym.peek() == '*' || sym.peek() == '/' ||in.charAt(i) == '+' || in.charAt(i) == '-' ) {
                        while(sym.peek() != '(') {
                            strOut = strOut + "" + sym.pop();
                        }
                        sym.push(in.charAt(i));
                    }
                    else{
                        sym.push(in.charAt(i));
                    }
                }
                else if (in.charAt(i) == ')') {
                    while(sym.peek() != '('){
                        strOut = strOut + "" + sym.pop();
                    }
                    sym.pop();
                }
            }
            else {
                strOut = strOut +"" + in.charAt(i);
            }
        }
        while(!sym.empty())
        {
            strOut += ""+ sym.pop();
        }
        return strOut;
    }
    public String reverse(String pre)
    {
        String str = "";
        int len = pre.length();
        for (int i = 0; i<len;i++) {
            str ="" + pre.charAt(i) +  str;
        }
        return str;
    }
}
 */

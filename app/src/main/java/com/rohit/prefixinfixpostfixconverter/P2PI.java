package com.rohit.prefixinfixpostfixconverter;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EmptyStackException;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
public class P2PI extends AppCompatActivity {
    public Set<Character> symSet;
    public P2PI(){
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
        setContentView(R.layout.activity_p2_pi);
        setSupportActionBar(findViewById(R.id.my_toolbar));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Postfix to Prefix/Infix Converter");
        EditText post=findViewById(R.id.postfixIn);
        TextView pre=findViewById(R.id.prefixOut);
        TextView in=findViewById(R.id.infixOut);
        Button convert=findViewById(R.id.convert);
        convert.setOnClickListener(view -> {
            String postfix=post.getText().toString();
            if(postfix.isEmpty()) {
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            }
            
            else
            {
                pre.setText(convertP2Pre(postfix));
                in.setText(convertP2In(postfix));
            }
        });
    }
    public String convertP2Pre(String postfix)
    {
        Stack<String> st = new Stack<>();
        int len = postfix.length();
        for (int i = 0; i<len;i++) {
            if(symSet.contains(postfix.charAt(i))){
                String m = "";
               try{ m = st.pop()+m;
                m = st.pop()+m;
               }catch(EmptyStackException e) {
                   Toast.makeText(this, "Enter a valid postfix expression", Toast.LENGTH_SHORT).show();
                }
                   m = postfix.charAt(i) + m;
                st.push(m);
            }
            else{
                st.push(""+postfix.charAt(i));
            }
        }
        return st.pop();
    }
    public String convertP2In(String postfix)
    {
        Stack<String> st = new Stack<>();
        int len = postfix.length();
        for (int i = 0; i<len;i++) {
            if(symSet.contains(postfix.charAt(i))){
                String m = "";
                m = st.pop()+")"+m;
                m = postfix.charAt(i)+m;
                m = "("+st.pop()+m;
                st.push(m);
            }
            else{
                st.push(""+postfix.charAt(i));
            }
        }
        return st.pop();
    }
}
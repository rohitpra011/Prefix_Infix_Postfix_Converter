package com.rohit.prefixinfixpostfixconverter;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
public class P2IP extends AppCompatActivity {
    public Set<Character> sym;
    public P2IP(){
        sym = new LinkedHashSet<Character>();
        sym.add('+');
        sym.add('*');
        sym.add('/');
        sym.add('-');
        sym.add('^');
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2_ip);
        setSupportActionBar(findViewById(R.id.my_toolbar));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Prefix to Infix/Postfix Converter");
        EditText pre=findViewById(R.id.prefixIn);
        TextView inf=findViewById(R.id.infixOut);
        TextView post=findViewById(R.id.postfixOut);
        Button convert=findViewById(R.id.convert);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prefix=pre.getText().toString();
                String infix = convertPre2I(prefix);
                inf.setText(infix);
                post.setText(convertPre2Post(prefix));
            }
        });
    }
    public String convertPre2Post(String pre){
        Stack<String> st = new Stack<>();
        String revpre = reverse(pre);
        int len = revpre.length();
        for (int i = 0; i<len;i++) {
            if(sym.contains(revpre.charAt(i))){
                String m = st.pop()+""+st.pop()+""+revpre.charAt(i);
                st.push(m);
            }
            else{
                st.push(""+revpre.charAt(i));
            }
        }
        return st.pop();
    }
    public String convertPre2I(String pre){
        Stack<String> st = new Stack<>();
        String revpre = reverse(pre);
        int len = revpre.length();
        for (int i = 0; i<len;i++) {
            if(sym.contains(revpre.charAt(i))){
                String m = "("+st.pop()+""+revpre.charAt(i)+""+st.pop()+")";
                st.push(m);
            }
            else{
                st.push(""+revpre.charAt(i));
            }
        }
        return st.pop();
    }
    public String reverse(String pre)
    {
        String str = "";
        int len = pre.length();
        for (int i = 0; i<len;i++) {
            str = pre.charAt(i) + str;
        }
        return str;
    }
}
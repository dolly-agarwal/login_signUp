package com.android.login;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = "Home";
        if (user != null) {
            // Name, email address, and profile photo Url
             email = user.getEmail();
            EditText ed = new EditText(this);
            ed.setText(email);
            ed.setTextColor(ContextCompat.getColor(this, R.color.white));
        }

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setTitle("Home");
            ColorDrawable colorDrawable
                    = new ColorDrawable(Color.parseColor("#4A4848"));
            actionBar.setBackgroundDrawable(colorDrawable);

        }
        Button signout = (Button)findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance()
                        .signOut(Home.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(new Intent(Home.this, MainActivity.class));
                                finish();
                            }
                        });
            }
        });
    }
}
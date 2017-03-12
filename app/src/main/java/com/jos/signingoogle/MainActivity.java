package com.jos.signingoogle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity {
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton gsib;

    private static int RC_SIGN_IN_GOOGLE = 0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gsib = (SignInButton) findViewById(R.id.b_google);

    }

    @Override
    public void onStart() {
        super.onStart();

        //SIGN IN WITH GOOGLE
        //gsib.setColorScheme(SignInButton.COLOR_LIGHT);
        //gsib.setSize(SignInButton.SIZE_STANDARD);
        gsib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.b_google:
                        goAccount();
                        break;
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private void goAccount(){
        Intent i = new Intent(MainActivity.this,gAccount.class);
        startActivity(i);
    }
}
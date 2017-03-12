package com.jos.signingoogle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class gAccount extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks{

    private TextView gNameV,gEmailV;
    private Button bSignOut;
    private String gName,gEmail;
    private GoogleSignInAccount account;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;

    private static int RC_SIGN_IN_GOOGLE = 0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_account);


        gNameV = (TextView) findViewById(R.id.g_name);
        gEmailV = (TextView) findViewById(R.id.g_email);
        bSignOut = (Button) findViewById(R.id.b_signout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInWithGoogle();
    }

    @Override
    protected void onStart() {
        super.onStart();


        bSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN_GOOGLE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInWithGoogle(result);
        }
    }

    //GOOGLE API ABSTRACT METHODS INTERFACE ONCONNECTIONLISTENER
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Toast.makeText(this,"Signed in with Google successfully",Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"Signed in with Google successfully",Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"Sign in failed, connection failed", Toast.LENGTH_SHORT)
                .show();
    }

    private void signInWithGoogle(){
        Intent signInGoogleIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInGoogleIntent,RC_SIGN_IN_GOOGLE);
    }
    private void handleSignInWithGoogle(GoogleSignInResult result){
        if (result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();
            gEmail = acct.getEmail();
            gName = acct.getDisplayName();

            gEmailV.append(gEmail);
            gNameV.append(gName);
        }
        else {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Toast.LENGTH_SHORT);
                        gAccount.this.finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            Toast.makeText(getApplicationContext(), "Error questing Google Sign In", Toast.LENGTH_SHORT);
            thread.start();
        }
    }
    private void signOut(){

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                revokeGoogleAccess();
            }
        });
    }
    private void revokeGoogleAccess(){
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                finish();
            }
        });
    }
}
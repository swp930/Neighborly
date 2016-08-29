package com.neighborly.swapnilpatil.neighborly;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.facebook.AccessTokenSource;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG ="LoginActivity";
    private static final int RC_SIGN_IN = 1;
    LoginButton loginButton;
    SignInButton googleSignInButton;
    Button logoutButton;
    TextView detailsText;
    CallbackManager callbackManager;
    ProfileTracker profileTracker;
    AccessTokenTracker accessTokenTracker;
    AQuery aQuery;
    GoogleApiClient mGoogleApiClient;
    public boolean isGoogleLogin = false;
    public boolean isFacebookLogin = false;
    static String name ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);
        googleSignInButton =(SignInButton)findViewById(R.id.sign_in_button);
        logoutButton = (Button)findViewById(R.id.logout);

        detailsText = (TextView)findViewById(R.id.login_data);
        loginButton.setReadPermissions(Arrays.asList("user_friends","email","public_profile"));

        aQuery = new AQuery(this);

        if(!isFacebookLogin) {
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    isFacebookLogin = true;
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    Log.v(TAG, "response: " + response.toString());

                                    // Application code
                                    try {
                                        String email = object.getString("email");
                                        String birthday = object.getString("birthday");
                                        LoginActivity.name = object.getString("name");
                                        Log.d(TAG, "fb user details : " + " name " + name + " email " + email);
                                        detailsText.setText("name : " + name + " email: " + email);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email,gender,birthday");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {
                    Log.e(TAG, "fb error is " + error.toString());

                }
            });
        } else{
            Intent fbIntent = new Intent(LoginActivity.this,MainActivity.class);
            fbIntent.putExtra("username",name);
            startActivity(fbIntent);
        }
        googleSignInButton.setOnClickListener(this);
        logoutButton.setOnClickListener(this);
        mGoogleApiClient = getGoogleApiClient();
    }

    private GoogleApiClient getGoogleApiClient() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInButton.setSize(SignInButton.SIZE_WIDE);
        googleSignInButton.setScopes(gso.getScopeArray());
            return new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }


    @Override
    public void onClick(View v) {
        if(!mGoogleApiClient.isConnecting()){
            switch (v.getId()){
                case R.id.sign_in_button:{
                    signIn();
                    break;
                }
                case R.id.logout:{
                    signOut();
                    break;
                }
            }
        }
    }

    private void signOut() {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Log.d(TAG,status.toString());
                        googleSignInButton.setVisibility(View.VISIBLE);
                        logoutButton.setVisibility(View.INVISIBLE);
                        detailsText.setText("");
                        // ...
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(isFacebookLogin)
            callbackManager.onActivityResult(requestCode,resultCode,data);
        else if(isGoogleLogin) {
            if (resultCode == RC_SIGN_IN) {
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleResult(signInResult);
            }
        }
    }

    private void handleResult(GoogleSignInResult signInResult) {
        Log.d(TAG, "handleSignInResult:" + signInResult.isSuccess());
        if (signInResult.isSuccess()) {
            isGoogleLogin = true;
            // Signed in successfully, show authenticated UI.

            GoogleSignInAccount acct = signInResult.getSignInAccount();
            updateUI(acct);
            googleSignInButton.setVisibility(View.INVISIBLE);
            logoutButton.setVisibility(View.VISIBLE);
          
        } else {
            // Signed out, show unauthenticated UI.
            Log.e(TAG,"Sign in  not successful");
        }
    }

    private void updateUI(GoogleSignInAccount acct) {
        Bundle bundle = new Bundle();
        Log.d(TAG,"google user details : "+ " username : "+ acct.getDisplayName()+" email " +acct.getEmail() );
        detailsText.setText("name: "+ acct.getDisplayName()+" email: "+acct.getEmail());
        if(!NeighborlyApplication.isFirstTimeLogin() ){
         bundle.putString("name", acct.getDisplayName());
            NeighborlyApplication.setFirstTimeLogin(true);
            Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
            intent.putExtra("user_name",bundle);
            startActivity(intent);
        }
        else{
            Intent newIntent = new Intent(LoginActivity.this,MainActivity.class);
            newIntent.putExtra("username",acct.getDisplayName());
            startActivity(newIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult opr =Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if(opr.isDone()){
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>(){

                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleResult(googleSignInResult);
                }
            });
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        loginButton.clearPermissions();

    }
}

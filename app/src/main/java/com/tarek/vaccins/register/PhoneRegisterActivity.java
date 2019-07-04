package com.tarek.vaccins.register;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tarek.vaccins.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PhoneRegisterActivity extends AppCompatActivity {

    public  static final String TAG = "PhoneAuth";


    private EditText mPhoneNumber, code ;
    private Button sendCode , resendCode , verifyCode  ;
    private String phoneVerificatioId ;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks ;
    private PhoneAuthProvider.ForceResendingToken resendToken ;
    private FirebaseAuth fbAuth ;
    private LinearLayout linearCode ;
    String phoneNumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);

        mPhoneNumber = findViewById(R.id.phoneNumber);



        code = findViewById(R.id.code);
        sendCode = findViewById(R.id.sendCode);
        resendCode = findViewById(R.id.resendCode);
        verifyCode = findViewById(R.id.verifyCode);
        linearCode = findViewById(R.id.linear_enter_code_phone_activity);


        resendCode.setEnabled(false);
        verifyCode.setEnabled(false);


        fbAuth = FirebaseAuth.getInstance();


        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*
                if (TextUtils.isEmpty(mPhoneNumber.getText())){
              //      if (mPhoneNumber.getText().toString().equals("")){
              //  if(mPhoneNumber.){
                        Toast.makeText(PhoneRegisterActivity.this,"pleasse enter phone number",Toast.LENGTH_LONG).show();
                }else {
                        Toast.makeText(PhoneRegisterActivity.this,"sahit",Toast.LENGTH_LONG).show();
                }

*/
                sendCode(view);
                linearCode.setVisibility(View.VISIBLE);

            }
        });
        verifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyCode(view);

            }
        });
        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendCode(view);

            }
        });


        findViewById(R.id.img_from_phone_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhoneRegisterActivity.this,WelcomeActivity.class));
            }
        });

    }


    public void sendCode(View view){


        String phNumber = mPhoneNumber.getText().toString();

        setUpVerificationCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phNumber,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks);
        resendCode.setVisibility(View.VISIBLE);
     //   Toast.makeText(PhoneRegisterActivity.this,"message send",Toast.LENGTH_LONG).show();

    }

    public  void resendCode (View view){

        String phNumber = mPhoneNumber.getText().toString();

        setUpVerificationCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phNumber,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks,
                resendToken);
        Toast.makeText(PhoneRegisterActivity.this,"resend code",Toast.LENGTH_LONG).show();

    }


    private void SignWithPhoneAuthCredential(PhoneAuthCredential credential) {

        fbAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {

                    phoneNumber = mPhoneNumber.getText().toString();
                    Toast.makeText(PhoneRegisterActivity.this,"my phone number is "+phoneNumber,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PhoneRegisterActivity.this,RegisterActivity.class);
                    intent.putExtra("phone", phoneNumber);
                    startActivity(intent);

                    finish();
                    code.setText("");
                    resendCode.setEnabled(false);
                    verifyCode.setEnabled(false);

                    //   final FirebaseUser user = task.getResult().getUser();
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user != null){
                        final DatabaseReference mUserDB = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()); // to point into it in database
                        mUserDB.addListenerForSingleValueEvent(new ValueEventListener() { // adding lister for fetching in data
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(!dataSnapshot.exists()){
                                    Map<String,Object> userMap = new HashMap<>(); // to collecte data & put it in database
                                    userMap.put("phone",user.getPhoneNumber());
                                    userMap.put("name",user.getPhoneNumber());
                                    mUserDB.updateChildren(userMap);
                                }
                                userIsLoggedIn();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }else if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                    Toast.makeText(PhoneRegisterActivity.this,"code invalid ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setUpVerificationCallbacks() {

        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {


                resendCode.setEnabled(false);
                verifyCode.setEnabled(true);
                code.setText("");
                SignWithPhoneAuthCredential(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                if (e instanceof FirebaseAuthInvalidCredentialsException){
                    Log.d(TAG , "invalid credential");
                }else if(e instanceof FirebaseTooManyRequestsException){
                    Log.d(TAG, "SMS Quota Exceed");
                }
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationId, token);

                phoneVerificatioId = verificationId ;
                resendToken = token ;
                sendCode.setEnabled(false);
                resendCode.setEnabled(true);
                verifyCode.setEnabled(true);

            }
        };
    }


    public void verifyCode(View view){
        String codiv = code.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificatioId,codiv);
        SignWithPhoneAuthCredential(credential);
        Toast.makeText(PhoneRegisterActivity.this,"verify code",Toast.LENGTH_LONG).show();

    }


    public void signOut(View view){
        fbAuth.signOut();
        code.setText("");
        sendCode.setEnabled(true);

    }

    private void userIsLoggedIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
            return;
        }
    }
}

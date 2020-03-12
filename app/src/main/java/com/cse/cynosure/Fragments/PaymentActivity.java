package com.cse.cynosure.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cse.cynosure.MainActivity;
import com.cse.cynosure.Payment.PaytmPayment;
import com.cse.cynosure.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PaymentActivity extends AppCompatActivity  {
    private int currentStep = 0,lam=0;
    private LinearLayout layout1,layout2,layout3;
    private StepView stepView;
    private Button first,second;
    RadioGroup rg;
    private String check;
    private EditText name,phone,clg,email;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        stepView=findViewById(R.id.step_view);
        email=findViewById(R.id.email);
        clg=findViewById(R.id.college);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        rg=findViewById(R.id.radioGroup);
        first=findViewById(R.id.firstContinue);
        second=findViewById(R.id.secondContinue);
        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        layout3=findViewById(R.id.layout3);
        stepView.setStepsNumber(3);
        stepView.go(0, true);


        findViewById(R.id.payBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(PaymentActivity.this, PaytmPayment.class);
                    Bundle b=new Bundle();
                    b.putString("amount", String.valueOf(450));
                    b.putString("phone",phone.getText().toString());
                    b.putString("name",name.getText().toString());
                    b.putString("Accommodation",check);
                    b.putString("email",email.getText().toString());
                    b.putString("college",clg.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                    Animatoo.animateDiagonal(PaymentActivity.this);
                    finish();

            }

        });

        layout1.setVisibility(View.VISIBLE);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(name.getText().toString()) &&!TextUtils.isEmpty(phone.getText().toString()) &&
                        !TextUtils.isEmpty(email.getText().toString())){
                    if (currentStep < stepView.getStepCount() - 1) {
                        currentStep=1;
                        stepView.go(currentStep, true);
                        layout1.setVisibility(View.GONE);
                        layout2.setVisibility(View.VISIBLE);
                    } else {
                        stepView.done(true);
                    }
                }else if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Enter Username");
                    name.setFocusable(true);
                }else if(TextUtils.isEmpty(phone.getText().toString())){
                    phone.setError("Enter Phone number");
                    phone.setFocusable(true);
                }
                else if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Enter Email");
                    email.setFocusable(true);
                }

            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(clg.getText().toString()) && lam!=0){
                    if (currentStep < stepView.getStepCount() - 1) {
                        currentStep=2;
                        stepView.go(currentStep, true);
                        layout1.setVisibility(View.GONE);
                        layout2.setVisibility(View.GONE);
                        layout3.setVisibility(View.VISIBLE);
                    }else if(lam==0) {
                        Toast.makeText(PaymentActivity.this,"Please Confirm Accomodation",Toast.LENGTH_LONG).show();
                    } else if(TextUtils.isEmpty(clg.getText())){
                        clg.setError("Enter college name");
                    }
                    else {
                        stepView.done(true);
                    }
                }
            }
        });

    }


    public void onRClick(View view) {
        boolean checked=((RadioButton)view).isChecked();
        int id=rg.getCheckedRadioButtonId();
        radioButton=findViewById(id);
        Log.d("RadioButton",String.valueOf(id));
        if(checked){
            lam=1;
        }else{
            lam=0;
        }
        if(id==2131362152){
            check="true";
        }else{
            check="false";
        }

    }

    @Override
    protected void onStart() {
        checkConnection();
        super.onStart();
    }

    private void checkConnection(){
        ConnectivityManager manager=(ConnectivityManager) Objects.requireNonNull(PaymentActivity.this).getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
        if(null!=activeNetwork){

        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(PaymentActivity.this);
            builder.setTitle("No Internet Connection");
            builder.setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    checkConnection();
                }
            });
            builder.setMessage("Please Check ur connection").show();

        }
    }
}

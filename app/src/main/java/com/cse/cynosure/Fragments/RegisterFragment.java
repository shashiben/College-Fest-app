package com.cse.cynosure.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.cse.cynosure.R;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    Button b;
    public RegisterFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.fragment_register, container, false);
        b=view.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PaymentActivity.class));
                //addNotification();
            }
        });
        SpannableString con2=new SpannableString("Bhargav(Male)");
        con2.setSpan(new UnderlineSpan(),0,con2.length(),0);
        TextView b=view.findViewById(R.id.bhargav);
        b.setText(con2);
        SpannableString con=new SpannableString("Anjum(Female)");
        con.setSpan(new UnderlineSpan(),0,con.length(),0);
        TextView a=view.findViewById(R.id.anjum);
        a.setText(con);
        view.findViewById(R.id.anjum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/917337051379");
            }
        });
        view.findViewById(R.id.bhargav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/919490491773");
            }
        });
        return view;
    }
    private void whatsapp(String s) {
        ConnectivityManager manager=(ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
        if(null!=activeNetwork){
            Uri uri=Uri.parse(s);
            Intent i=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }else{
            Toast.makeText(getActivity(),"No internet Connection",Toast.LENGTH_LONG).show();
        }

    }

}

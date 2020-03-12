package com.cse.cynosure.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.cynosure.Adapter.NotificationAdapter;
import com.cse.cynosure.Model.NotificationModel;
import com.cse.cynosure.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    RelativeLayout layout;
    List<NotificationModel> modelList;
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notification, container, false);
        layout=view.findViewById(R.id.lolol);

        recyclerView=view.findViewById(R.id.recycleriew);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        modelList=new ArrayList<>();
        adapter=new NotificationAdapter(modelList,getActivity());
        recyclerView.setAdapter(adapter);

        getNotifications();
        return view;
    }


    private void getNotifications() {

        DatabaseReference db= FirebaseDatabase.getInstance().getReference("Notify");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    NotificationModel model=ds.getValue(NotificationModel.class);
                    modelList.add(model);
                    Log.d("cynotech",model.getTime());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onStart() {
        checkConnection();

        super.onStart();
    }
    private void checkConnection(){
        ConnectivityManager manager=(ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
        if(null!=activeNetwork){

        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setTitle("No Internet Connection").setCancelable(false);
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

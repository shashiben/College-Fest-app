package com.cse.cynosure.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.cse.cynosure.Adapter.FunEventAdapter;
import com.cse.cynosure.Adapter.HomeAdapter;
import com.cse.cynosure.Adapter.sponsors_adapter;
import com.cse.cynosure.Model.FunEventModel;
import com.cse.cynosure.Model.Home_model;
import com.cse.cynosure.Model.Sponsors_Model;
import com.cse.cynosure.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements View.OnClickListener  {

    private static ViewPager mPager;

    private CirclePageIndicator indicator;
    private static int cp=0;
    private static  int np=0;
    private ImageView mo;
    private ImageView one;

    private BottomSheetDialog bottomSheetDialog;
    private RecyclerView recyclerView,funeventsRV;
    private String names[]={"Pubg","Photo Contest","Robo Car Racing","Treasure Hunt","Hand-Foot tap","Meme Contest"};
    private int logos[] = {R.drawable.ace_institute, R.drawable.chillies,R.drawable.momilk};
    private int funeventlogos[] = {R.drawable.pubg, R.drawable.photo_contest, R.drawable.robocar,
            R.drawable.treasure_hunt, R.drawable.hand_foot,R.drawable.meme_contest};

    private ArrayList<Home_model> homeModelArrayList;
    private int[] myImageList=new int[]{R.drawable.slide1,R.drawable.iot,R.drawable.brochure};
    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        homeModelArrayList=new ArrayList<>();
        homeModelArrayList=populateList();
        mo=view.findViewById(R.id.messOpt);
        recyclerView=view.findViewById(R.id.recycleriew);
        funeventsRV=view.findViewById(R.id.funeventsrecyclerview);
        funeventsRV.setHasFixedSize(true);

        recyclerView.setHasFixedSize(true);
        view.findViewById(R.id.locateus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map=new Intent(Intent.ACTION_VIEW);
                map.setData(Uri.parse("https://maps.app.goo.gl/C6nwvDqYfpgg8KDt5"));
                Intent chooser=Intent.createChooser(map,"Launch Map");
                startActivity(chooser);
            }
        });
        view.findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/Cynosure2k20"));
                startActivity(i);
            }
        });
        view.findViewById(R.id.insta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/svuce_cynosure2020?igshid=zh9mp5vqn7dn"));
                startActivity(i);
            }
        });
        view.findViewById(R.id.twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/club_coding?s=08"));
                startActivity(i);
            }
        });
        view.findViewById(R.id.yt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCt6zvDyrdSCivo-6sPsJDwA"));
                startActivity(i);
            }
        });
        mPager =  view.findViewById(R.id.pager);
        mPager.setAdapter(new HomeAdapter(getActivity(),homeModelArrayList));
        indicator =view.findViewById(R.id.indicator);
        init();
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        funeventsRV.setLayoutManager(manager);


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<FunEventModel> hmm=kim();
        FunEventAdapter adapter2=new FunEventAdapter(hmm,getActivity());
        funeventsRV.setAdapter(adapter2);

        ArrayList<Sponsors_Model> ui = prepareData();
        sponsors_adapter adapter = new sponsors_adapter(getActivity(),ui);
        recyclerView.setAdapter(adapter);

        showBD();
        mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(getView());
            }
        });

        return view;
    }

    private ArrayList<FunEventModel> kim() {
        ArrayList<FunEventModel> limage = new ArrayList<>();
        for(int i=0;i<funeventlogos.length;i++){
            FunEventModel androidVersion = new FunEventModel();
            androidVersion.setImage(funeventlogos[i]);
            androidVersion.setName(names[i]);
            limage.add(androidVersion);
        }
        return limage;
    }

    private void showBD() {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet,null);
        one=view.findViewById(R.id.messenger);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/Cynosure2k20"));
                startActivity(i);            }
        });
        view.findViewById(R.id.iop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("https://wa.me/917997217156");
                Intent i=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });
        view.findViewById(R.id.mail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto","cynosure2k20@gmail.com",null));
                startActivity(Intent.createChooser(i,"Choose an Email app to send mail"));
            }
        });
        bottomSheetDialog=new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(view);

    }

    private ArrayList<Home_model> populateList() {
        ArrayList<Home_model> list=new ArrayList<>();
        for (int i=0;i<3;i++){
            Home_model model=new Home_model();
            model.setImage_drawable(myImageList[i]);
            list.add(model);
        }
        return list;
    }


    public void showDialog(View view){
        bottomSheetDialog.show();
    }

    private void init() {

        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        np =homeModelArrayList.size();

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (cp == np) {
                    cp = 0;
                }
                mPager.setCurrentItem(cp++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                cp = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
    private ArrayList<Sponsors_Model> prepareData(){

        ArrayList<Sponsors_Model> limage = new ArrayList<>();
        for(int i=0;i<logos.length;i++){
            Sponsors_Model androidVersion = new Sponsors_Model();
            androidVersion.setImage(logos[i]);
            limage.add(androidVersion);
        }
        return limage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.messenger:
                Log.d("BDS","Ok");
        }
    }
}


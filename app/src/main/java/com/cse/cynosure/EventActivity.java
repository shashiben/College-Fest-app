package com.cse.cynosure;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public class EventActivity extends AppCompatActivity {
    String no;
    CollapsingToolbarLayout layout;
    Toolbar toolbar;
    ImageView eventpic;
    TextView phanith,jaswanth;
    LinearLayout ppt,cwc,qc,quiz,bdc,mp,tt,dc,ww,ab;
    NestedScrollView nestedScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Intent i=getIntent();
        phanith=findViewById(R.id.phanith);
        jaswanth=findViewById(R.id.jaswanth);
        findViewById(R.id.shashi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/917997217156");
            }
        });
        findViewById(R.id.maneesh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/917981776225");
            }
        });
        findViewById(R.id.hrushi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918374360545");
            }
        });
        findViewById(R.id.surya).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/919182236166");
            }
        });
        findViewById(R.id.phanithDharuvi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918247505790");
            }
        });
        findViewById(R.id.rohith).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/917013596966");
            }
        });
        phanith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918247505790");
            }
        });
        jaswanth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918919151312");
                
            }
        });
        findViewById(R.id.srinivas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/917780564567");
            }
        });
        findViewById(R.id.likith).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918897533993");
            }
        });
        findViewById(R.id.mohith).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918074627392");
            }
        });
        findViewById(R.id.kolla).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/919121073730");
            }
        });
        findViewById(R.id.hemanth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918309647569");
            }
        });
        findViewById(R.id.ps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918919249790");
            }
        });
        findViewById(R.id.jaswanth2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918919151312");
            }
        });
        findViewById(R.id.golla).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918074683888");
            }
        });
        findViewById(R.id.ashok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/919502841753");
            }
        });
        findViewById(R.id.bhargav).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/919490491773");
            }
        });
        findViewById(R.id.ashok2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/919502841753");
            }
        });
        findViewById(R.id.divya).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918639771688");
            }
        });
        findViewById(R.id.sharan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/918875742355");
            }
        });
        findViewById(R.id.aasif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/916300030716");
            }
        });
        findViewById(R.id.prashanth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/919912195484");
            }
        });
        findViewById(R.id.vvenkat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whatsapp("https://wa.me/919493053387");
            }
        });
        findViewById(R.id.mailcyno).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto","cynosure2k20@gmail.com",null));
                startActivity(Intent.createChooser(i,"Choose an Email app to send mail"));
            }
        });

        nestedScrollView=findViewById(R.id.nestedscrollview);
        qc=findViewById(R.id.qc);
        ab=findViewById(R.id.ab);
        mp=findViewById(R.id.mock);
        ww=findViewById(R.id.ww);
        quiz=findViewById(R.id.quiz);
        tt=findViewById(R.id.tt);
        bdc=findViewById(R.id.bdc);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout=findViewById(R.id.collapsingtoolbar);
        layout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBlack));
        ppt=findViewById(R.id.ppt);
        dc=findViewById(R.id.dc);
        cwc=findViewById(R.id.cwc);
        cwc.setVisibility(View.GONE);
        eventpic=findViewById(R.id.eventpic);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ppt.setVisibility(View.GONE);
        no=i.getStringExtra("Kaizoku");
        assert no != null;
        switch (no){
            case "Prashanth":
                layout.setTitle("Technical Hacking");
                eventpic.setImageResource(R.drawable.techhack);
                findViewById(R.id.techhack).setVisibility(View.VISIBLE);
                break;
            case "Varun":
                layout.setTitle("Mr Engineer");
                layout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBlack));
                eventpic.setImageResource(R.drawable.mrengg);
                findViewById(R.id.meng).setVisibility(View.VISIBLE);
                break;
            case "Phanith":
                eventpic.setImageResource(R.drawable.ab);
                layout.setTitle("Algo-Builder");
                layout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBlack));
                ppt.setVisibility(View.GONE);
                ab.setVisibility(View.VISIBLE);
                cwc.setVisibility(View.GONE);
                break;
            case "Srinivas":
                eventpic.setImageResource(R.drawable.quizzotica);
                layout.setTitle("Quizzotica");
                cwc.setVisibility(View.GONE);
                ppt.setVisibility(View.GONE);
                quiz.setVisibility(View.VISIBLE);
                break;
            case "Shashi Kumar":
                eventpic.setImageResource(R.drawable.ppt);
                layout.setTitle("Slide Deck");
                cwc.setVisibility(View.GONE);
                ppt.setVisibility(View.VISIBLE);
                break;
            case "Kiran Kumar":
                eventpic.setImageResource(R.drawable.praxisplacement);
                layout.setTitle("Praxis Placement");
                mp.setVisibility(View.VISIBLE);
                cwc.setVisibility(View.GONE);
                ppt.setVisibility(View.GONE);
                break;
            case "Ashok":
                eventpic.setImageResource(R.drawable.webwreathe);
                layout.setTitle("Web Wreath");
                cwc.setVisibility(View.GONE);
                ww.setVisibility(View.VISIBLE);
                ppt.setVisibility(View.GONE);
                break;
            case "Rohith":
                eventpic.setImageResource(R.drawable.querycrackers);
                layout.setTitle("Query Crackers");
                cwc.setVisibility(View.GONE);
                ppt.setVisibility(View.GONE);
                qc.setVisibility(View.VISIBLE);
                break;
            case "Bhargav":
                eventpic.setImageResource(R.drawable.dazzlecoding);
                layout.setTitle("Dazzle Coding");
                dc.setVisibility(View.VISIBLE);
                cwc.setVisibility(View.GONE);
                ppt.setVisibility(View.GONE);
                break;
            case "Sai Kiran":
                eventpic.setImageResource(R.drawable.techtambola);
                layout.setTitle("Tech Tambola");
                tt.setVisibility(View.VISIBLE);
                cwc.setVisibility(View.GONE);
                ppt.setVisibility(View.GONE);
                break;
            case "Hrushikesh":
                eventpic.setImageResource(R.drawable.cwc_logo);
                layout.setTitle("Caper with Cipher");
                cwc.setVisibility(View.VISIBLE);
                ppt.setVisibility(View.GONE);
                break;
            case "Dheeraj":
                eventpic.setImageResource(R.drawable.bdc);
                layout.setTitle("BrainDrainCrew");
                cwc.setVisibility(View.GONE);
                ppt.setVisibility(View.GONE);
                bdc.setVisibility(View.VISIBLE);
                break;
        }

    }

    private void whatsapp(String s) {
        ConnectivityManager manager=(ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
        if(null!=activeNetwork){
            Uri uri=Uri.parse(s);
            Intent i=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }else{
            Toast.makeText(EventActivity.this,"No internet Connection",Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}

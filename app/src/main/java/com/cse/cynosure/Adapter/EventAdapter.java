package com.cse.cynosure.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.cse.cynosure.EventActivity;
import com.cse.cynosure.Model.EventModel;
import com.cse.cynosure.R;

import java.util.List;

public class EventAdapter extends PagerAdapter {
    private List<EventModel> modelList;
    private Context context;

    public EventAdapter(List<EventModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        final View view = layoutInflater.inflate(R.layout.event_view, container, false);
        container.addView(view);

        TextView quotes = view.findViewById(R.id.event);
        quotes.setText(modelList.get(position).getEventdesc());
        final TextView names = view.findViewById(R.id.name);
        names.setText(modelList.get(position).getConame());
        TextView quotes_types = view.findViewById(R.id.event_type);
        quotes_types.setText(modelList.get(position).getEventname());
        final ImageView profile = view.findViewById(R.id.profileimage);
        profile.setImageResource(modelList.get(position).getUserPic());
        final TextView name2=view.findViewById(R.id.name2);
        name2.setText(modelList.get(position).getConame2());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, EventActivity.class);
                i.putExtra("Kaizoku",names.getText());
                context.startActivity(i);


            }
        });
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View) object);
        container=null;
    }
}

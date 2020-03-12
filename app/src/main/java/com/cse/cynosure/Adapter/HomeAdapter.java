package com.cse.cynosure.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.cse.cynosure.Model.Home_model;
import com.cse.cynosure.R;

import java.util.ArrayList;

public class HomeAdapter extends PagerAdapter {


    private ArrayList<Home_model> imageModelArrayList;
    private LayoutInflater inflater;
    private Context context;
    private sponsors_adapter adapter;


    public HomeAdapter(Context context, ArrayList<Home_model> imageModelArrayList) {
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        final View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);


        final ImageView imageView = imageLayout.findViewById(R.id.image);
        imageView.setImageResource(imageModelArrayList.get(position).getImage_drawable());

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }



}
package com.cse.cynosure.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cse.cynosure.Adapter.EventAdapter;
import com.cse.cynosure.Model.EventModel;
import com.cse.cynosure.R;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {
    private List<EventModel> modelList;

    private EventAdapter viewPagerAdapter;

    private ViewPager viewPager;
    public EventsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_events, container, false);
       modelList=new ArrayList<>();
       viewPager=view.findViewById(R.id.viewpager);
        String desc[] = {"Build the best to be the best!","Get ready for a rollercoaster ride into intense world of quizzing",
                "A call to emulate your interview competence",
                "A good & innovative idea becomes a great implementation only when you present it",
                "Develop the Dextrous Design",
                "Update Talents set aim=\"Explore your technical skills\" where skills=\"DBMS\""," Coding with twist!!",
                "Luck plus brain always wins!",
                "Dive into it-Decode it-Delight in it",
        "We are only in for Bugs","Best Platform to Enrich your technical and team communication ","Show your hacking skills"};
        String names1[] = {"Phanith", "Srinivas", "Kiran Kumar",
                "Shashi Kumar", "Ashok", "Rohith","Bhargav", "Sai Kiran", "Hrushikesh","Dheeraj","Varun","Prashanth"};
        String names2[] = {"Jaswanth", "Madhuri", "Yamuna",
                "Monika", "Mounisha", "Sushma","Jaya Prakash", "Kavya", "Tasleem","Pavani","Samarendra","Swetha Chowdary"};
        String event_name[] = {"Algo-Builder", "Quizzotica", "Praxis Placement","Slide Deck",
                "Web Wreathe", "Query Crackers","Dazzle Coding", "Tech Tambola", "Caper With Cipher","BrainDrain Crew","Mr Engineer","Technical Hacking"};
        int images[] = {R.drawable.ab, R.drawable.quizzotica, R.drawable.praxisplacement,
                R.drawable.ppt, R.drawable.webwreathe, R.drawable.querycrackers,
                R.drawable.dazzlecoding, R.drawable.techtambola, R.drawable.cwc_logo,R.drawable.bdc,R.drawable.mrengg,R.drawable.techhack};
        for (int i = 0 ; i < images.length ; i++)
        {
            EventModel viewPagerModel = new EventModel();
            viewPagerModel.setConame(names1[i]);
            viewPagerModel.setConame2(names2[i]);
            viewPagerModel.setEventdesc(desc[i]);
            viewPagerModel.setEventname(event_name[i]);
            viewPagerModel.setUserPic(images[i]);
            modelList.add(viewPagerModel);
        }
        viewPagerAdapter = new EventAdapter(modelList,getActivity());
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAdapter(viewPagerAdapter);

        return view;
    }
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 1) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else {
                view.setAlpha(0);
            }
        }
    }


}

package com.cse.cynosure.Adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cse.cynosure.Model.NotificationModel;
import com.cse.cynosure.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    private List<NotificationModel> modelList;
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    private Context context;
    private int mExpand=0;
    public NotificationAdapter(List<NotificationModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.notification_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String t=modelList.get(position).getText();
        holder.text.setText(t);
        String ti=modelList.get(position).getTime();
        Calendar calendar= Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(ti));
        String pTime= DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
        holder.time.setText(getTimeAgo(Long.parseLong(ti),context));

        String im=modelList.get(position).getImage();
        if(!im.equals("noImage")){
            Picasso.get().load(im).into(holder.image);
        }else{
            holder.expand.setVisibility(View.GONE);
        }


        if(modelList.get(position).getImage().equals("noImage")){
            holder.expand.setVisibility(View.GONE);
        }else{
            holder.expand.setVisibility(View.VISIBLE);
        }
        holder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mExpand==0){
                    holder.expand.setImageResource(R.drawable.expand_less);
                    holder.image.setVisibility(View.VISIBLE);
                    mExpand=1;
                }else {
                    holder.expand.setImageResource(R.drawable.expand_more);
                    holder.image.setVisibility(View.GONE);

                    mExpand=0;

                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView expand;
        TextView text,time;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            expand=itemView.findViewById(R.id.expand);
            time=itemView.findViewById(R.id.tim);
            text=itemView.findViewById(R.id.ijk);
            image=itemView.findViewById(R.id.srcImage);
        }
    }
    public static String getTimeAgo(long time, Context ctx) {
        if (time < 1000000000000L) {
            time *= 1000;
        }
        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        }  else {
            return diff / DAY_MILLIS + " days ago";
        }
    }
}

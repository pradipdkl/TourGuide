package com.example.tourguide.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourguide.R;
import com.example.tourguide.globalURL.URL;
import com.example.tourguide.model.guideModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_Guide extends RecyclerView.Adapter<Adapter_Guide.GuideViewHolder> {

    Context mcontext;
    List<guideModel> guideList;
    public Adapter_Guide(Context mcontext, List<guideModel> guideList)
    {
        this.mcontext = mcontext;
        this.guideList = guideList;
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_guideinfo, parent, false);
        return new GuideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder holder, int position) {
    guideModel guide = guideList.get(position);

        String imgpath = URL.imagePath + guide.getImage();
        Log.e("Image path is :" ,"Image path is" + imgpath);
        Picasso.get().load(imgpath).into(holder.imageView);

        holder.tvguidename.setText(guide.getName());
        holder.tvguideaddress.setText(guide.getLocation());
        holder.tvcontact.setText(guide.getContactnum());
        holder.tvlanguage.setText(guide.getLanguage());
        holder.tvdescription.setText(guide.getDescription());
    }

    @Override
    public int getItemCount() {
        return guideList.size();
    }

    public class GuideViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvguidename, tvguideaddress, tvcontact, tvdescription, tvlanguage;

        public GuideViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.guideimg);
            tvguidename = itemView.findViewById(R.id.guidename);
            tvguideaddress = itemView.findViewById(R.id.guideaddress);
            tvcontact = itemView.findViewById(R.id.guidecontact);
            tvlanguage = itemView.findViewById(R.id.guidelanguage);
            tvdescription = itemView.findViewById(R.id.guidedescription);

        }
    }
}

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
import com.example.tourguide.model.hotelModel;
import com.squareup.picasso.Picasso;

import java.util.List;



public class Adapter_Hotel extends RecyclerView.Adapter<Adapter_Hotel.HotelViewHolder> {

    Context mcontext;
    List<hotelModel> hotelList;
    public Adapter_Hotel(Context mcontext, List<hotelModel> hotelList) {
        this.mcontext = mcontext;
        this.hotelList = hotelList;
    }
    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_hotel,parent,false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        hotelModel hotel = hotelList.get(position);

        String imgpath = URL.imagePath + hotel.getImage();
        Log.e("Image path is :" ,"Image path is" + imgpath);
        Picasso.get().load(imgpath).into(holder.imageView);

        holder.tvhotelname.setText(hotel.getName());
        holder.tvhotellocation.setText(hotel.getLocation());
        holder.tvhotelprice.setText(hotel.getPrice());
        holder.tvhoteldescription.setText(hotel.getDescription());
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvhotelname, tvhotellocation, tvhotelprice, tvhoteldescription;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.placeimg);
            tvhotelname = itemView.findViewById(R.id.tvhotelname);
            tvhotellocation = itemView.findViewById(R.id.tvhotellocation);
            tvhotelprice = itemView.findViewById(R.id.tvhotelprice);
            tvhoteldescription = itemView.findViewById(R.id.tvhoteldescription);

        }
    }
}

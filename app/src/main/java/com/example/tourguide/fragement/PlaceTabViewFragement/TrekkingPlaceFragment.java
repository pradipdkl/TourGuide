package com.example.tourguide.fragement.PlaceTabViewFragement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tourguide.API.placesAPI;
import com.example.tourguide.Adapter.places.AdapterTrekkingPlace;
import com.example.tourguide.R;
import com.example.tourguide.globalURL.URL;
import com.example.tourguide.model.places.trekkingplaceModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class TrekkingPlaceFragment extends Fragment {

    RecyclerView TrekkingPlacesRecycleView;

    List<trekkingplaceModel> trekkingplaceModelList;
    AdapterTrekkingPlace adapterTrekkingPlace;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_trekking_place, container, false);
        TrekkingPlacesRecycleView = view.findViewById(R.id.trekkingplacesrecycleview);
        loadtrekkingplace();
        return view;
    }

    private void loadtrekkingplace() {

        placesAPI placesAPI = URL.getInstance().create(placesAPI.class);
        Call<List<trekkingplaceModel>> trekkingPlaceDetails = placesAPI.getTrekkingPlaceDetails();
        try {
            Response<List<trekkingplaceModel>> trekkingPlaceReponse = trekkingPlaceDetails.execute();

            trekkingplaceModelList = trekkingPlaceReponse.body();
            adapterTrekkingPlace = new AdapterTrekkingPlace(getContext(), trekkingplaceModelList);
            TrekkingPlacesRecycleView.setAdapter(adapterTrekkingPlace);
            TrekkingPlacesRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
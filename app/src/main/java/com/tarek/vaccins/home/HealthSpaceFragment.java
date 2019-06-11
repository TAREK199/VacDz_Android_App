package com.tarek.vaccins.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.tarek.vaccins.R;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.HealthSpace;

import java.util.ArrayList;
import java.util.List;


public class HealthSpaceFragment extends Fragment {

    private View view ;
    private RecyclerView recyclerView ;
    private List<HealthSpace> healthAdvices ;
    private CarouselView carouselView ;
    int[] sampleImage = {R.drawable.img10, R.drawable.img12, R.drawable.img13, R.drawable.img14, R.drawable.img15};

    SharedPrefManager sharedPrefManager ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_health_space,container,false);


        carouselView = view.findViewById(R.id.carousselView_home);

        carouselView.setPageCount(sampleImage.length);
        carouselView.setImageListener(imageListener);

        sharedPrefManager = new SharedPrefManager(getActivity());


        String tok = sharedPrefManager.getFireBaseToken();

      //  Toast.makeText(getActivity(),"firebase token : "+tok,Toast.LENGTH_LONG).show();

//        Log.d("my firebase token",tok);


        viewData();
        return view;
    }


    private void viewData() {
        recyclerView = view.findViewById(R.id.recycle_health_space);
        healthAdvices = new ArrayList<>();

        healthAdvices.add(new HealthSpace("Recherche",R.drawable.child));
        healthAdvices.add(new HealthSpace("Recherche",R.drawable.child));
        healthAdvices.add(new HealthSpace("Recherche",R.drawable.child));
        healthAdvices.add(new HealthSpace("Recherche",R.drawable.child));
        healthAdvices.add(new HealthSpace("Recherche",R.drawable.child));
        healthAdvices.add(new HealthSpace("Recherche",R.drawable.child));
        healthAdvices.add(new HealthSpace("Recherche",R.drawable.child));


        HealthSpaceAdapter healthSpaceAdapter = new HealthSpaceAdapter(getActivity(),healthAdvices);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(healthSpaceAdapter);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImage[position]);
        }
    };


}

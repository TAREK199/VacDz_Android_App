package com.tarek.vaccins.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tarek.vaccins.R;
import com.tarek.vaccins.edit.EditFatherProfile;

public class FatherProfileFragment extends Fragment {


    private View view ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_father_profile,container,false);

        view.findViewById(R.id.btn_edit_father_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditFatherProfile.class));
            }
        });

        return view;

    }
}

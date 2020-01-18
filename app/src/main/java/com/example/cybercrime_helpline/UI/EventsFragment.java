package com.example.cybercrime_helpline.UI;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cybercrime_helpline.R;

public class EventsFragment extends Fragment {
    Button btnorganizeevents,btnshowevents;



    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_events, container, false);
        btnorganizeevents = root.findViewById(R.id.btnorganizeevents);
        btnshowevents = root.findViewById(R.id.btnshowevents);

        btnorganizeevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OrganizeEventActivity.class);
                startActivity(intent);
            }
        });

        btnshowevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ViewEventActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}

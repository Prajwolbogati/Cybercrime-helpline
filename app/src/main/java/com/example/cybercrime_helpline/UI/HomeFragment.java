package com.example.cybercrime_helpline.UI;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.cybercrime_helpline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RelativeLayout btnnewshome, btnlawshome, btneventshome, btncaseshome;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btncaseshome = view.findViewById(R.id.btncasehome);
        btneventshome = view.findViewById(R.id.btneventshome);
        btnlawshome = view.findViewById(R.id.btnlawshome);
        btnnewshome = view.findViewById(R.id.btnnewshome);

        btnlawshome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "View Rules", Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(getActivity(), ViewRulesActivity.class);
                startActivity(intent);
            }
        });
        btneventshome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mFragment = null;
                mFragment = new EventsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, mFragment).commit();
            }
        });

        btncaseshome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Total Case", Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(getActivity(), CountCaseActivity.class);
                startActivity(intent);

            }
        });
        btnnewshome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "View News", Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(getActivity(), ViewNewsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}


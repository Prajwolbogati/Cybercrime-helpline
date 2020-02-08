package com.example.cybercrime_helpline.UI;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cybercrime_helpline.R;

public class CasesFragment extends Fragment {
    Button btnaddcase,btnshowcases;


    public CasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cases, container, false);
        btnaddcase = root.findViewById(R.id.btnaddcase);
        btnshowcases = root.findViewById(R.id.btnshowcases);

        btnaddcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FileCaseActivity.class);
                startActivity(intent);
            }
        });

        btnshowcases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ViewCaseActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}

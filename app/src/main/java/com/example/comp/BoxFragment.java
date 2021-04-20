package com.example.comp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class BoxFragment extends Fragment {

    RecyclerView recyclerView_box;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_box, container, false);

        recyclerView_box = view.findViewById(R.id.recycle_view_box);

        return view;
    }
}
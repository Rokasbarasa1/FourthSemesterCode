package com.example.version2myrecipe.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.version2myrecipe.R;

public class fragmentTagExpanded extends Fragment {


    public fragmentTagExpanded() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tag_expanded, container, false);

        return rootView;
    }
}
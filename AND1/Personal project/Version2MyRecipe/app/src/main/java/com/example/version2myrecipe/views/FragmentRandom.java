package com.example.version2myrecipe.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.version2myrecipe.R;

public class FragmentRandom extends Fragment {
    private FragmentTransaction ft;

    public FragmentRandom() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_random, container, false);
    }
}
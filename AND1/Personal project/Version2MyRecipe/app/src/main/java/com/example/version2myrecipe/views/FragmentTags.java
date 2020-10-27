package com.example.version2myrecipe.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.adapter.TagAdapter;
import com.example.version2myrecipe.viewModels.TagsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentTags extends Fragment implements TagAdapter.OnListItemClickListener {
    Animation rotateOpen;
    Animation rotateClose;
    Animation fromBottom;
    Animation toBottom;
    FloatingActionButton add_btn;
    FloatingActionButton trash_btn;
    FloatingActionButton create_btn;
    boolean clicked = false;
    RecyclerView tagList;
    TagAdapter tagAdapter;
    TagsViewModel tagsViewModel;

    public FragmentTags() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tags, container, false);

        //Tag List
        initTagsRecyclerView(rootView);

        //Expandable button
        setUpExpandableFloatingButton(rootView);
        return rootView;
    }

    private void initTagsRecyclerView(View rootView){
        tagsViewModel = ViewModelProviders.of(this).get(TagsViewModel.class);
        tagsViewModel.init();
        tagsViewModel.getTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                tagAdapter.notifyDataSetChanged();
            }
        });
        tagList = rootView.findViewById(R.id.rv);
        tagList.hasFixedSize();
        tagList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        tagAdapter = new TagAdapter(tagsViewModel.getTags().getValue(), this);
        tagList.setAdapter(tagAdapter);
    }

    private void setUpExpandableFloatingButton(final View rootView){
        add_btn = rootView.findViewById(R.id.add_btn);
        trash_btn = rootView.findViewById(R.id.trash_btn);
        create_btn = rootView.findViewById(R.id.create_btn);
        rotateOpen = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.rotate_close_anim);
        fromBottom = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.from_bottom_anim);
        toBottom = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.to_bottom_anim);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });
        trash_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(getActivity(), ActivityLogin.class);
                menuIntent.putExtra("name", 21);
                startActivity(menuIntent);
            }
        });
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(), "Clicked create button", Toast.LENGTH_SHORT).show();
                Intent menuIntent = new Intent(getActivity(), ActivityCreateRecipe.class);
                startActivity(menuIntent);
            }
        });
    }

    private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        if(!clicked)
            clicked = true;
        else
            clicked = false;
    }

    @SuppressLint("RestrictedApi")
    private void setVisibility(Boolean clicked) {
        if(!clicked){
            trash_btn.setVisibility(View.VISIBLE);
            create_btn.setVisibility(View.VISIBLE);
        } else {
            trash_btn.setVisibility(View.INVISIBLE);
            create_btn.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(Boolean clicked) {
        if(!clicked){
            trash_btn.startAnimation(fromBottom);
            create_btn.startAnimation(fromBottom);
            add_btn.startAnimation(rotateOpen);
        }else {
            trash_btn.startAnimation(toBottom);
            create_btn.startAnimation(toBottom);
            add_btn.startAnimation(rotateClose);
        }
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getContext(), ""+ position,Toast.LENGTH_SHORT).show();
    }
}
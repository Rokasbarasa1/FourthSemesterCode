package com.example.version2myrecipe.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.version2myrecipe.R;
import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.adapter.AdapterTag;
import com.example.version2myrecipe.viewModels.TagsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FragmentTags extends Fragment implements AdapterTag.OnListTagClickListener {
    private FloatingActionButton add_btn;
    private RecyclerView tagList;
    private AdapterTag adapterTag;
    private TagsViewModel tagsViewModel;
    private FragmentManager supportFragmentManager;
    private TextView toolbarTitle;
    private ActionBar upArrow;
    private FragmentActivity myContext;


    public FragmentTags(FragmentManager supportFragmentManager, TextView toolbarTitle, ActionBar upArrow) {
        this.supportFragmentManager = supportFragmentManager;
        this.toolbarTitle = toolbarTitle;
        this.upArrow = upArrow;
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

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void initTagsRecyclerView(View rootView){
        tagsViewModel = ViewModelProviders.of(this).get(TagsViewModel.class);
        tagsViewModel.init();
        tagsViewModel.getTags().observe(this, new Observer<List<Tag>>() {
            @Override
            public void onChanged(List<Tag> tags) {
                adapterTag.notifyDataSetChanged();
            }
        });
        tagList = rootView.findViewById(R.id.rv_tags);
        tagList.hasFixedSize();
        tagList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        adapterTag = new AdapterTag(tagsViewModel.getTags().getValue(), this);
        tagList.setAdapter(adapterTag);
    }

    private void setUpExpandableFloatingButton(final View rootView){
        add_btn = rootView.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Fragment fragment = null;
                fragment = new FragmentCreateRecipe(null);
                toolbarTitle.setText("Create recipe");
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                upArrow.setDisplayHomeAsUpEnabled(true);
                 */
                Fragment fragment = null;
                fragment = new FragmentCreateRecipe(null);
                toolbarTitle.setText("Create recipe");
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container, fragment);
                upArrow.setDisplayHomeAsUpEnabled(true);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public void onClick(int position) {

        Fragment fragment = null;
        fragment = new FragmentTagExpanded(supportFragmentManager, toolbarTitle, upArrow, tagsViewModel.getTag(position));
        toolbarTitle.setText(tagsViewModel.getTag(position).getName());
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        upArrow.setDisplayHomeAsUpEnabled(true);

 /*
        Fragment fragment = null;
        fragment = new FragmentTagExpanded(supportFragmentManager, toolbarTitle, upArrow, tagsViewModel.getTag(position));
        toolbarTitle.setText("Create recipe");
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
        upArrow.setDisplayHomeAsUpEnabled(true);

         */
    }
}
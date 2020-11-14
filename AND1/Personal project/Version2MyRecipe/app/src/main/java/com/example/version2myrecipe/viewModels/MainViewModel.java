package com.example.version2myrecipe.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.repository.TagsRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private TagsRepository repo;

    public void init(){
        if(repo != null){
            return;
        }
        repo = TagsRepository.getInstance();
    }

    public Tag getTag(int index){
        return repo.getTag(index);
    }
}

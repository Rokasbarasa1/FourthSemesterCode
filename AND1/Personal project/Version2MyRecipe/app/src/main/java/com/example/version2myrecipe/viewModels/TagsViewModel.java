package com.example.version2myrecipe.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.version2myrecipe.models.Tag;
import com.example.version2myrecipe.repository.TagsRepository;

import java.util.List;

public class TagsViewModel extends ViewModel {
    private MutableLiveData<List<Tag>> tags;
    private TagsRepository repo;

    public void init(){
        if(tags != null){
            return;
        }
        repo = TagsRepository.getInstance();
        tags = repo.getTags();
    }
    public LiveData<List<Tag>> getTags(){
        return tags;
    }

    public Tag getTag(int position) {
        return repo.getTag(position);
    }

    public void refreshTags() {
        tags = repo.getTags();
    }
}

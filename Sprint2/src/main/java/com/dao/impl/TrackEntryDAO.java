package com.dao.impl;

import com.dao.IDAO;
import com.model.Person;
import com.model.TrackEntry;

import java.util.*;

public class TrackEntryDAO implements IDAO<TrackEntry> {

    @Override
    public Optional<TrackEntry> get(long id) {
        return MemoryDataSource.TRACK_ENTRIES.stream().filter(trackEntry -> trackEntry.getId()==id).findFirst();
    }

    @Override
    public void insert(TrackEntry trackEntry) {
        MemoryDataSource.TRACK_ENTRIES.add(trackEntry);
    }

    @Override
    public void update(TrackEntry trackEntry) {
        Optional<TrackEntry> trackEntryOptional = get(trackEntry.getId());
        if(trackEntryOptional.isPresent()){
            TrackEntry updatedTrackEntry = trackEntryOptional.get();
            updatedTrackEntry.replaceWith(trackEntry);
        }

    }

    @Override
    public List<TrackEntry> findAll() {
        return MemoryDataSource.TRACK_ENTRIES;
    }

    @Override
    public void deleteById(Long id) {
        for(TrackEntry trackEntry : MemoryDataSource.TRACK_ENTRIES) {
            if (trackEntry.getId() == id){
                MemoryDataSource.TRACK_ENTRIES.remove(trackEntry);
                return;
            }
        }

    }
}

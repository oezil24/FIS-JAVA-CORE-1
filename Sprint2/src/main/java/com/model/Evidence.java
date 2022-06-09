package com.model;

import lombok.Data;

import java.util.Set;
@Data
public class Evidence extends AbstractEntity{
    private CriminalCase criminalCase;
    private Storage storage;
    private String number;
    private String itemName;
    private String notes;
    private boolean archived;
    private Set<TrackEntry> trackEntrySet;

    public void replaceWith(Evidence evidence) {
        criminalCase = evidence.getCriminalCase();
       storage = evidence.getStorage();
        number = evidence.getNumber();
        itemName = evidence.getItemName();
        notes = evidence.getNotes();
        archived = evidence.isArchived();
        trackEntrySet = evidence.getTrackEntrySet();
    }
}

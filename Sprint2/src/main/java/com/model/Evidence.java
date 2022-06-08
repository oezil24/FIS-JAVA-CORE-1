package com.model;

import java.util.Set;

public class Evidence extends AbstractEntity{
    private CriminalCase criminalCase;
    private Storage storage;
    private String number;
    private String itemName;
    private String notes;
    private boolean archived;
    private Set<TrackEntry> trackEntrySet;
}

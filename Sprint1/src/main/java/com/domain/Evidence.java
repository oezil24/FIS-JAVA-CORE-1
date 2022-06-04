package main.java.com.domain;

import java.io.Serializable;
import java.util.Set;

public class Evidence implements Serializable {
    private String number;
    private String itemName;
    private String notes;
    private boolean archived;
    private Set<TrackEntry> trackEntries;

}

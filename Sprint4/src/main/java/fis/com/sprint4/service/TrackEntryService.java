package fis.com.sprint4.service;

import fis.com.sprint4.entity.TrackEntry;

import java.util.Set;

public interface TrackEntryService {
    TrackEntry addTrackEntry(TrackEntry trackEntry);

    TrackEntry updateTrackEntry(TrackEntry trackEntry);

    Set<TrackEntry> getTrackEntries();

    TrackEntry getTrackEntry(Long id);

    void deleteTrackEntry(Long id);
}

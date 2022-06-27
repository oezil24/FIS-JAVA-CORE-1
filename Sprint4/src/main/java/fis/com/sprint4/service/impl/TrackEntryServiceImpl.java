package fis.com.sprint4.service.impl;
import fis.com.sprint4.entity.TrackEntry;
import fis.com.sprint4.repository.TrackEntryRepository;
import fis.com.sprint4.service.TrackEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrackEntryServiceImpl implements TrackEntryService {

    @Autowired
    TrackEntryRepository trackEntryRepository;


    @Override
    public TrackEntry addTrackEntry(TrackEntry trackEntry) {
        return this.trackEntryRepository.save(trackEntry);
    }

    @Override
    public TrackEntry updateTrackEntry(TrackEntry trackEntry) {
        return this.trackEntryRepository.save(trackEntry);
    }

    @Override
    public Set<TrackEntry> getTrackEntries() {
        return new HashSet<>(this.trackEntryRepository.findAll());
    }

    @Override
    public TrackEntry getTrackEntry(Long id) {
        return this.trackEntryRepository.findById(id).get();
    }

    @Override
    public void deleteTrackEntry(Long id) {
        TrackEntry trackEntry = new TrackEntry();
        trackEntry.setId(id);
        this.trackEntryRepository.delete(trackEntry);
    }
}

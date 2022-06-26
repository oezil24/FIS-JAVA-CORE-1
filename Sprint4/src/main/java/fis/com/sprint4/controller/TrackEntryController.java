package fis.com.sprint4.controller;



import fis.com.sprint4.entity.TrackEntry;
import fis.com.sprint4.service.TrackEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/trackEntry")
@CrossOrigin("*")
public class TrackEntryController {
    @Autowired
    final
    TrackEntryService trackEntryService;

    public TrackEntryController(TrackEntryService trackEntryService) {
        this.trackEntryService = trackEntryService;
    }

    @GetMapping("/")
    public Set<TrackEntry> getTrackEntries() {
        return this.trackEntryService.getTrackEntries();
    }

    @PostMapping("/")
    public ResponseEntity<TrackEntry> addTrackEntry(@RequestBody TrackEntry trackEntry) {
        return ResponseEntity.ok(this.trackEntryService.addTrackEntry(trackEntry));
    }

    @PutMapping("/")
    public TrackEntry updateTrackEntry(@RequestBody TrackEntry trackEntry) {
        return this.trackEntryService.updateTrackEntry(trackEntry);
    }

    @DeleteMapping("/{id}")
    public void deleteTrackEntry(@PathVariable Long id) {
        this.trackEntryService.deleteTrackEntry(id);
    }
}

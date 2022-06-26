package fis.com.sprint4.controller;

import fis.com.sprint4.entity.Storage;
import fis.com.sprint4.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/storage")
@CrossOrigin("*")
public class StorageController {
    @Autowired
    StorageService storageService;

    @GetMapping("/")
    public Set<Storage> getStorages() {
        return this.storageService.getStorages();
    }

    @PostMapping("/")
    public ResponseEntity<Storage> addStorage(@RequestBody Storage storage) {
        return ResponseEntity.ok(this.storageService.addStorage(storage));
    }

    @PutMapping("/")
    public Storage updateStorage(@RequestBody Storage storage) {
        return this.storageService.updateStorage(storage);
    }

    @DeleteMapping("/{id}")
    public void deleteStorage(@PathVariable Long id) {
        this.storageService.deleteStorage(id);
    }
}

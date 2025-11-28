package wannaBeDeveloper.journalApp.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import wannaBeDeveloper.journalApp.Service.JournalEntryService;
import wannaBeDeveloper.journalApp.entity.JournalEntry;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journals")
@Tag(name = "JournalAPI")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    private String getLoggedInUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping
    @Operation(summary = "get all journal entries")
    public ResponseEntity<List<JournalEntry>> getAllEntries() {
        List<JournalEntry> entries = journalEntryService.getAll();
        if (entries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
        String userName = getLoggedInUserName();
        JournalEntry saved = journalEntryService.createEntry(entry, userName);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable String myId) {
        String userName = getLoggedInUserName();
        Optional<JournalEntry> entry = journalEntryService.getEntryById(myId, userName);
        return entry.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<Void> deleteEntryById(@PathVariable String myId) {
        String userName = getLoggedInUserName();
        boolean deleted = journalEntryService.deleteById(myId, userName);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<JournalEntry> updateEntryById(@PathVariable String myId,
                                                        @RequestBody JournalEntry newEntry) {
        String userName = getLoggedInUserName();
        Optional<JournalEntry> updated = journalEntryService.updateEntry(myId, userName, newEntry);
        return updated.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

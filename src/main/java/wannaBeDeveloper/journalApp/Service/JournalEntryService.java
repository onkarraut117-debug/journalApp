package wannaBeDeveloper.journalApp.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wannaBeDeveloper.journalApp.Repository.JournalEntryRepository;
import wannaBeDeveloper.journalApp.entity.JournalEntry;
import wannaBeDeveloper.journalApp.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;



    @Transactional
    public JournalEntry createEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) throw new RuntimeException("User not found: " + userName);

        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);

        user.getJournalEntries().add(saved);
        userService.updateUserWithoutPassword(user);

        return saved;
    }

    public List<JournalEntry> getAllEntriesOfUser(String userName) {
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();
    }

    public Optional<JournalEntry> getEntryById(String id, String userName) {
        User user = userService.findByUserName(userName);
        return user.getJournalEntries()
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Transactional
    public boolean deleteById(String id, String userName) {
        User user = userService.findByUserName(userName);
        Optional<JournalEntry> entryOpt = user.getJournalEntries()
                .stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();

        if (entryOpt.isPresent()) {
            JournalEntry entry = entryOpt.get();
            user.getJournalEntries().remove(entry);
            userService.updateUserWithoutPassword(user);
            journalEntryRepository.delete(entry);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<JournalEntry> updateEntry(String id, String userName, JournalEntry newEntry) {
        Optional<JournalEntry> entryOpt = getEntryById(id, userName);

        if (entryOpt.isPresent()) {
            JournalEntry old = entryOpt.get();
            if (newEntry.getTitle() != null && !newEntry.getTitle().isBlank()) {
                old.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && !newEntry.getContent().isBlank()) {
                old.setContent(newEntry.getContent());
            }
            return Optional.of(journalEntryRepository.save(old));
        }

        return Optional.empty();
    }
}

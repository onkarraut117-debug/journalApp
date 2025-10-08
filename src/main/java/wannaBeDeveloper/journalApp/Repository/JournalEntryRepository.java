package wannaBeDeveloper.journalApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wannaBeDeveloper.journalApp.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry,String> {
}


//Controller(End-point)-->Service-->Repository
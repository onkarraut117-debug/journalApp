package wannaBeDeveloper.journalApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wannaBeDeveloper.journalApp.entity.JournalEntry;
import wannaBeDeveloper.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User,String> {
    User findByUserName(String username);
    User deleteByUserName(String username);
}


//Controller(End-point)-->Service-->Repository
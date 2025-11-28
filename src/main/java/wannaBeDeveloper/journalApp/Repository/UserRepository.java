package wannaBeDeveloper.journalApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wannaBeDeveloper.journalApp.entity.JournalEntry;
import wannaBeDeveloper.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User,String> {
    User findByUserName(String username);
    User deleteByUserName(String username);

    User findByEmail(String email);
}


//Controller(End-point)-->Service-->Repository
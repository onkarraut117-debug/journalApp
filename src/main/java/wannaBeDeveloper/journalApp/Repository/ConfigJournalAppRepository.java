package wannaBeDeveloper.journalApp.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wannaBeDeveloper.journalApp.entity.ConfigAppEntity;
import wannaBeDeveloper.journalApp.entity.User;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigAppEntity,String> {

}


//Controller(End-point)-->Service-->Repository
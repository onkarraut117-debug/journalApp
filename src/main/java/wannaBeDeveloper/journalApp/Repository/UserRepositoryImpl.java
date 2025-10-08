package wannaBeDeveloper.journalApp.Repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableFindOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import wannaBeDeveloper.journalApp.entity.User;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<User> getUserForSentimentAnalysis() {
        Query query = new Query();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        query.addCriteria(
                Criteria.where("email")
                        .regex(emailRegex)     // valid email format
                        .and("sentimentAnalysis").is(true)
        );

        return mongoTemplate.find(query, User.class);
    }

}


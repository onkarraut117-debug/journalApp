package wannaBeDeveloper.journalApp.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    @NonNull private String userName;
    @NonNull private String password;

    private String email;

    private Boolean sentimentAnalysis;
    @DBRef(lazy = false)
    private List<JournalEntry>journalEntries = new ArrayList<>();
    private List<String>roles;

}



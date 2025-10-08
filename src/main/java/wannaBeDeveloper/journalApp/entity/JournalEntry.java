package wannaBeDeveloper.journalApp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import wannaBeDeveloper.journalApp.enums.Sentiment;

import java.time.LocalDateTime;

@Document(collection="journalEntry")
@Data
public class JournalEntry {
    @Id
     private String id;
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;
}



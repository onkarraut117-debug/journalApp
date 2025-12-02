package wannaBeDeveloper.journalApp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="config_journal_app")
@Data
public class ConfigAppEntity {

    private String key;
    private String values;

}
//


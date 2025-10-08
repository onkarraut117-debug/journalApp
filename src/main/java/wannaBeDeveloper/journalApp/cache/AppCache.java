package wannaBeDeveloper.journalApp.cache;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wannaBeDeveloper.journalApp.Repository.ConfigJournalAppRepository;
import wannaBeDeveloper.journalApp.entity.ConfigAppEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    private Map<String, String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigAppEntity configAppEntity : all) {
            appCache.put(configAppEntity.getKey(), configAppEntity.getValues());
        }
    }

    public String getValue(String key) {
        return appCache.get(key);
    }

    public Map<String, String> getAll() {
        return appCache;
    }
}

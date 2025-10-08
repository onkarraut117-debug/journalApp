package wannaBeDeveloper.journalApp.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wannaBeDeveloper.journalApp.enums.Sentiment;
import wannaBeDeveloper.journalApp.Repository.UserRepositoryImpl;
import wannaBeDeveloper.journalApp.Service.EmailService;
import wannaBeDeveloper.journalApp.Service.SentimentAnalysisService;
import wannaBeDeveloper.journalApp.cache.AppCache;
import wannaBeDeveloper.journalApp.entity.JournalEntry;
import wannaBeDeveloper.journalApp.entity.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    EmailService emailService;
    @Autowired
    UserRepositoryImpl userRepository;
    @Autowired
    AppCache appCache;

    private SentimentAnalysisService sentimentAnalysisService;

   @Scheduled(cron = "0 0 9 * * SUN")
//    @Scheduled(cron = "0 * * ? * *")
    public void fetchUserAndSendMail(){
        List<User> users = userRepository.getUserForSentimentAnalysis();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer>sentimentCounts=new HashMap<>();
            for (Sentiment sentiment:sentiments){
                if (sentiment!=null){
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment,0)+1);
                }
                Sentiment mostFrequentSentiment=null;
                int maxcount=0;
                for (Map.Entry<Sentiment, Integer> entry:sentimentCounts.entrySet()){
                    if(entry.getValue()>maxcount){
                        maxcount = entry.getValue();
                        mostFrequentSentiment=entry.getKey();
                    }

                }
                if(mostFrequentSentiment!=null){
                    emailService.sendEmail(user.getEmail(),"sentiment for last 7 days",mostFrequentSentiment.toString());
                }
            }
//            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
//            String entry = String.join("", filteredEntries);
//            String sentiment = sentimentAnalysisService.getSentiment(entry);
//            emailService.sendEmail(user.getEmail(),"sentiment for last 7 days",sentiment);
     }
 }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void cleanAppCache(){
        appCache.init();
    }

}

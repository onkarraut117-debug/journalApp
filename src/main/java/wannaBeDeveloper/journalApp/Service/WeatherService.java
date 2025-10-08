package wannaBeDeveloper.journalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import wannaBeDeveloper.journalApp.apiResponse.WeatherResponse;
import wannaBeDeveloper.journalApp.cache.AppCache;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final AppCache appCache;

    @Autowired
    RedisService redisService;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherService(RestTemplate restTemplate, AppCache appCache) {
        this.restTemplate = restTemplate;
        this.appCache = appCache;
    }

    public WeatherResponse getWeather(String city) {
        String redisKey = "weather_of_" + city.toLowerCase();

        // 1️⃣ Check Redis cache first
        WeatherResponse cached = redisService.get(redisKey, WeatherResponse.class);
        if (cached != null) {
            System.out.println("⚡ Returning cached weather for " + city);
            return cached;
        }

        // 2️⃣ Build API URL
        String finalUrl = appCache.getAll().get("weather_api")
                .replace("CITY", city)
                .replace("API_KEY", apiKey);

        System.out.println("🌍 Fetching weather from API: " + finalUrl);

        try {
            // Fetch directly as WeatherResponse
            ResponseEntity<WeatherResponse> response =
                    restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);

            WeatherResponse body = response.getBody();
            if (body != null) {
                // 3️⃣ Save to Redis for 5 minutes
                redisService.set(redisKey, body, 300);
            }
            return body;

        } catch (HttpClientErrorException.TooManyRequests ex) {
            System.err.println("⚠️ API rate limit reached. Returning cached data if available.");
            return cached; // fallback to cache if API fails
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("❌ Failed to fetch weather for city: " + city, ex);
        }
    }
}

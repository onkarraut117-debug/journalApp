package wannaBeDeveloper.journalApp.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Location location;
    private Current current;

    @Getter
    @Setter
    public static class Location {
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;

        @JsonProperty("timezone_id")
        private String timezoneId;

        private String localtime;

        @JsonProperty("localtime_epoch")
        private int localtimeEpoch;

        @JsonProperty("utc_offset")
        private String utcOffset;
    }

    @Getter
    @Setter
    public static class Current {
        private int temperature;

        @JsonProperty("weather_code")
        private int weatherCode;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
    }
}

package events.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

    private final long timestamp;
    private final long userId;
    private final String event;

    public Event() {
        this.timestamp = -1;
        this.userId = -1;
        this.event = null;
    }

    @JsonCreator
    public Event(@JsonProperty("timestamp") long timestamp,@JsonProperty("userId") long userId,@JsonProperty("event") String event) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.event = event;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getUserId() {
        return userId;
    }

    public String getEvent() {
        return event;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", userId=").append(userId);
        sb.append(", event='").append(event).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package loggingframework.src.core;

import java.time.Instant;

public class LogMessage {
    private final Instant timeStamp;
    private final LogLevel level;
    private final String msg;
    private final String source;

    private LogMessage(Builder builder){
        this.timeStamp = builder.timeStamp;
        this.level = builder.level;
        this.msg = builder.msg;
        this.source = builder.source;
    }

    public Instant getTimeStamp(){
        return timeStamp;
    }

    public LogLevel getLevel(){
        return level;
    }

    public String getMsg(){
        return msg;
    }

    public String getSource(){
        return source;
    }

    public String toString() {
        return String.format("LogMessage{timestamp=%s, level=%s, message='%s', source='%s'}",
                timeStamp, level, msg, source);
    }

}
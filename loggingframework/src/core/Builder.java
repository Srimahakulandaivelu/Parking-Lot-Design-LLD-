package loggingframework.src.core;

import java.time.Instant;

public class Builder {
    protected Instant timeStamp = Instant.now();
    protected LogLevel level;
    protected String msg;
    protected String source;

    public Builder level(LogLevel level){
        this.level = level;
        return this;
    }

    public Builder msg(String msg){
        this.msg = msg;
        return this;
    }

    public Builder source(String source){
        this.source = source;
        return this;
    }

    public Builder timeStamp(Instant timeStamp){
        this.timeStamp = timeStamp;
        return this;
    }

}

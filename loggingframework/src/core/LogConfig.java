package loggingframework.src.core;

public class LogConfig {
    private LogLevel rootLevel;

    public LogConfig(){
        this.rootLevel = LogLevel.INFO;
    }

    public LogConfig(LogLevel rootLevel){
        this.rootLevel = rootLevel;
    }

    public LogLevel getRootLevel(){
        return rootLevel;
    }

    public void setRootLevel(LogLevel rootLevel){
        this.rootLevel = rootLevel;
    }

    public String toString(){
        return String.format("LogConfiguration{rootLevel=%s}", rootLevel);
    }
}

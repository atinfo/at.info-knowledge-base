package com.blogspot.notes.automation.qa.interfaces;

import java.util.List;

/**
 * Created by Serhii Kuts
 */
public interface Command {
    String getProcess();

    List<String> getArgs();

    int getTimeout();

    default String getCommand() {
        return "[process = " + getProcess() +
                "; args = " + getArgs() +
                "; timeout = " + getTimeout() + "]";
    }
}

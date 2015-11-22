package com.blogspot.notes.automation.qa.entities;

import com.blogspot.notes.automation.qa.interfaces.Command;

import java.util.List;

/**
 * Created by Serhii Kuts
 */
public class DefaultCommand implements Command {
    private String process;
    private List<String> args;
    private int timeout;

    public DefaultCommand(final String process, final List<String> args, final int timeout) {
        this.process = process;
        this.args = args;
        this.timeout = timeout;
    }

    public String getProcess() {
        return process;
    }

    public List<String> getArgs() {
        return args;
    }

    public int getTimeout() {
        return timeout;
    }
}

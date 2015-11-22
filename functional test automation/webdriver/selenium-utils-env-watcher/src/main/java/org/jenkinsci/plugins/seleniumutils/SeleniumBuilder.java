package org.jenkinsci.plugins.seleniumutils;

import com.blogspot.notes.automation.qa.client.WatcherClient;

import static com.blogspot.notes.automation.qa.entities.CommonTask.*;

import static com.blogspot.notes.automation.qa.entities.JavaTask.*;

import hudson.Launcher;
import hudson.Extension;
import hudson.model.*;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.List;

public class SeleniumBuilder extends Builder {

    private final List<GridParameter> gridParameters;

    @DataBoundConstructor
    public SeleniumBuilder(final List<GridParameter> gridParameters) {
        this.gridParameters = gridParameters;
    }

    public List<GridParameter> getGridParameters() {
        return gridParameters;
    }

    @Override
    public boolean perform(final AbstractBuild build, final Launcher launcher, final BuildListener listener) {

        for (GridParameter environment : getGridParameters()) {
            WatcherClient watcherClient = null;
            boolean isStarted = false;

            try {
                watcherClient = new WatcherClient(environment.getRemoteWatcherIp(),
                        Integer.parseInt(environment.getRemoteWatcherPort()));

                if (environment.getKillCommonTasks()) {
                    listener.getLogger().println("Common tasks on " + environment.getRemoteWatcherIp()
                            + " killed = " + watcherClient.killCommonTasks(CHROME, FIREFOX, IE_BROWSER, IE_DRIVER));
                }

                if (environment.getKillJavaTasks()) {
                    listener.getLogger().println("Java tasks on " + environment.getRemoteWatcherIp()
                            + " killed = " + watcherClient.killJavaTasks(SELENIUM));
                }

                if (environment.getReconfigureNode()) {
                    listener.getLogger().println("Node on " + environment.getRemoteWatcherIp()
                            + " reconfigured to use new hub " + environment.getNewHubIp() + " = " +
                            watcherClient.updateLastIpInFile(environment.getNodeJsonPath(),
                                    environment.getNewHubIp()));
                }

                if (environment.getRestartServices()) {
                    isStarted = watcherClient.startScript(environment.getRestarterServicePath());
                    listener.getLogger().println("Services on " + environment.getRemoteWatcherIp()
                            + " started = " + isStarted);
                }

                listener.getLogger().println("All windows are minimized on " + environment.getRemoteWatcherIp()
                        + " = " + watcherClient.minimizeWindows());
            } catch (Exception e) {
                isStarted = false;
                listener.getLogger().println("Error occurred while restarting environment on " +
                        environment.getRemoteWatcherIp());
            } finally {
                if (watcherClient != null) {
                    watcherClient.disconnect();
                }
            }

            if (!isStarted) {
                listener.getLogger().println("Warning: services were not fully started or skipped on " +
                        environment.getRemoteWatcherIp());
            }
        }

        return true;
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public boolean isApplicable(final Class<? extends AbstractProject> aClass) {
            return true;
        }

        public String getDisplayName() {
            return "Environment configuration";
        }
    }
}




package org.jenkinsci.plugins.seleniumutils;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

/**
 * Author: Serhii Kuts
 * Date: 9/20/2014
 * Time: 11:29 PM
 */
public class GridParameter extends AbstractDescribableImpl<GridParameter> {

    private final String remoteWatcherIp;
    private final String remoteWatcherPort;
    private final String restarterServicePath;
    private final String newHubIp;
    private final String nodeJsonPath;
    private final boolean killCommonTasks;
    private final boolean killJavaTasks;
    private final boolean restartServices;
    private final boolean reconfigureNode;

    @DataBoundConstructor
    public GridParameter(final String remoteWatcherIp, final String remoteWatcherPort, final String restarterServicePath,
                         final String newHubIp, final String nodeJsonPath, final boolean killCommonTasks,
                         final boolean killJavaTasks, final boolean restartServices, final boolean reconfigureNode) {
        this.restarterServicePath = restarterServicePath;
        this.newHubIp = newHubIp;
        this.nodeJsonPath = nodeJsonPath;
        this.killCommonTasks = killCommonTasks;
        this.killJavaTasks = killJavaTasks;
        this.remoteWatcherIp = remoteWatcherIp;
        this.remoteWatcherPort = remoteWatcherPort;
        this.restartServices = restartServices;
        this.reconfigureNode = reconfigureNode;
    }

    public String getRestarterServicePath() {
        return restarterServicePath;
    }

    public boolean getKillCommonTasks() {
        return killCommonTasks;
    }

    public boolean getKillJavaTasks() {
        return killJavaTasks;
    }

    public String getRemoteWatcherIp() {
        return remoteWatcherIp;
    }

    public String getRemoteWatcherPort() {
        return remoteWatcherPort;
    }

    public boolean getRestartServices() {
        return restartServices;
    }

    public String getNewHubIp() {
        return newHubIp;
    }

    public String getNodeJsonPath() {
        return nodeJsonPath;
    }

    public boolean getReconfigureNode() {
        return reconfigureNode;
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<GridParameter> {

        public String getDisplayName() {
            return "Load node parameters";
        }

        public FormValidation doCheckRestarterServicePath(@QueryParameter final String value) {
            if (value.length() == 0) {
                return FormValidation.error("Please set a valid path");
            }

            return FormValidation.ok();
        }

        public FormValidation doCheckNodeJsonPath(@QueryParameter final String value) {
            if (value.length() == 0) {
                return FormValidation.error("Please set a valid path");
            }

            return FormValidation.ok();
        }

        public FormValidation doCheckNewHubIp(@QueryParameter final String value) {
            if (value.length() == 0 || !InetAddressValidator.getInstance().isValid(value)) {
                return FormValidation.error("Please set a valid ip address");
            }

            return FormValidation.ok();
        }

        public FormValidation doCheckRemoteWatcherIp(@QueryParameter final String value) {
            if (value.length() == 0 || !InetAddressValidator.getInstance().isValid(value)) {
                return FormValidation.error("Please set a valid ip address");
            }

            return FormValidation.ok();
        }

        public FormValidation doCheckRemoteWatcherPort(@QueryParameter final String value) {
            if (value.length() == 0 || !StringUtils.isNumeric(value)) {
                return FormValidation.error("Please set a valid port");
            }

            return FormValidation.ok();
        }
    }

    public String toString() {
        return getRestarterServicePath() != null ?
                "Re-starter service path = " + getRestarterServicePath() :
                "; kill common tasks = " + getKillCommonTasks() +
                        "; kill java tasks = " + getKillJavaTasks();
    }
}

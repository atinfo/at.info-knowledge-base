package org.jenkinsci.plugins.seleniumutils;

import hudson.Launcher;
import hudson.Extension;
import hudson.util.FormValidation;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class SeleniumBuilder extends Builder {

    private final String hubIp;
    private final String hubPort;
    private final List<NodeConfiguration> nodeConfigurations;

    @DataBoundConstructor
    public SeleniumBuilder(final String hubIp, final String hubPort, final List<NodeConfiguration> nodeConfigurations) {
        this.hubIp = hubIp;
        this.hubPort = hubPort;
        this.nodeConfigurations = nodeConfigurations;
    }

    public String getHubIp() {
        return hubIp;
    }

    public String getHubPort() {
        return hubPort;
    }

    public List<NodeConfiguration> getNodeConfigurations() {
        return nodeConfigurations;
    }

    @Override
    public boolean perform(final AbstractBuild build, final Launcher launcher, final BuildListener listener) {

        final SeleniumClient client = new SeleniumClient();

        try {
            listener.getLogger().println("Hub " + getHubIp() + " is down = " +
                    client.shutDownHub(getHubIp(), Integer.parseInt(getHubPort())));
        } catch (Exception e) {
            listener.getLogger().println("Can't shutdown hub " + getHubIp());
        }

        for (NodeConfiguration nodeConfiguration : getNodeConfigurations()) {
            try {
                listener.getLogger().println("Node " + nodeConfiguration.getNodeIp() + " is down = " +
                        client.shutDownNode(nodeConfiguration.getNodeIp(), Integer.parseInt(nodeConfiguration.getNodePort())));
            } catch (Exception e) {
                listener.getLogger().println("Can't shutdown node " + nodeConfiguration.getNodeIp());
            }
        }

        client.disconnect();

        return true;
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public FormValidation doCheckHubIp(@QueryParameter String value) throws IOException, ServletException {
            if (value.length() == 0 || !InetAddressValidator.getInstance().isValid(value)) {
                return FormValidation.error("Please set a valid ip address");
            }

            return FormValidation.ok();
        }

        public FormValidation doCheckHubPort(@QueryParameter String value) throws IOException, ServletException {
            if (value.length() == 0 || !StringUtils.isNumeric(value)) {
                return FormValidation.error("Please set a valid port");
            }

            return FormValidation.ok();
        }

        public boolean isApplicable(final Class<? extends AbstractProject> aClass) {
            return true;
        }

        public String getDisplayName() {
            return "Selenium Grid configuration";
        }
    }
}


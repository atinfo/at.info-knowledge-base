package org.jenkinsci.plugins.seleniumutils;

import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.FormValidation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Author: Serhii Kuts
 */
public class NodeConfiguration extends AbstractDescribableImpl<NodeConfiguration> {

    private final String nodeIp;
    private final String nodePort;

    @DataBoundConstructor
    public NodeConfiguration(final String nodeIp, final String nodePort) {
        this.nodeIp = nodeIp;
        this.nodePort = nodePort;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public String getNodePort() {
        return nodePort;
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<NodeConfiguration> {

        public FormValidation doCheckNodeIp(@QueryParameter String value) throws IOException, ServletException {
            if (value.length() == 0 || !InetAddressValidator.getInstance().isValid(value)) {
                return FormValidation.error("Please set a valid ip address");
            }

            return FormValidation.ok();
        }

        public FormValidation doCheckNodePort(@QueryParameter String value) throws IOException, ServletException {
            if (value.length() == 0 || !StringUtils.isNumeric(value)) {
                return FormValidation.error("Please set a valid port");
            }

            return FormValidation.ok();
        }

        public String getDisplayName() {
            return "Load node parameters";
        }
    }
}

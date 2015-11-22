package com.blogspot.notes.automation.qa.services;

import com.blogspot.notes.automation.qa.entities.DefaultCommand;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import java.util.Arrays;

import static com.blogspot.notes.automation.qa.utils.CommandLineUtils.executeCommandLine;
import static com.blogspot.notes.automation.qa.utils.IOUtils.updateLastIP;

/**
 * Created by Serhii Kuts
 */
@Path("/cmd")
@Produces({"application/json"})
@Consumes({"application/json"})
public class CommandLineService {

    @POST
    @Path("/startScript")
    public Response startScript(@QueryParam("path") final String path, @QueryParam("quoteArgs") final boolean quoteArgs,
                                @QueryParam("timeout") final int timeout) {
        return Response.status(executeCommandLine(new DefaultCommand("PSExec.exe", Arrays.asList("-d", "-i", path),
                timeout), quoteArgs) != -1 ? Response.Status.OK : Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/updateIP")
    public Response updateIP(@QueryParam("filePath") final String filePath,
                             @QueryParam("newIp") final String newIp) {
        return Response.status(updateLastIP(filePath, newIp) ?
                Response.Status.OK : Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/minimizeWindows")
    public Response minimizeWindows(@QueryParam("quoteArgs") final boolean quoteArgs,
                                    @QueryParam("timeout") final int timeout) {
        return Response.status(executeCommandLine(new DefaultCommand("cmd.exe",
                Arrays.asList("/c", System.getProperty("user.home").concat(
                        "\\AppData\\Roaming\\Microsoft\\Internet Explorer\\Quick Launch\\Shows Desktop.lnk")),
                timeout), quoteArgs) != -1 ?
                Response.Status.OK : Response.Status.NOT_FOUND).build();
    }
}

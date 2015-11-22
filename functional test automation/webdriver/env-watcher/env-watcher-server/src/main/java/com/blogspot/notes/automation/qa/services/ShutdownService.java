package com.blogspot.notes.automation.qa.services;

import com.blogspot.notes.automation.qa.entities.DefaultCommand;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static com.blogspot.notes.automation.qa.utils.CommandLineUtils.executeCommandLine;

/**
 * Created by Serhii Kuts
 */
@Path("/shutdown")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShutdownService {
    private static final String JAVA_TASKS_KILLER = "for /f \"tokens=1\" %i in ('jps -l ^| findstr \"?\"') do ( taskkill /F /PID %i )";
    private static final String COMMON_TASKS_KILLER = "for /f \"tokens=1\" %i in ('tasklist ^| findstr \"?\"') do ( taskkill /F /PID %i )";
    private static final String CMD_PROCESS = "cmd.exe";

    @POST
    @Path("/javaTasks")
    public Response killJavaTasks(final List<String> javaTasks, @QueryParam("quoteArgs") final boolean quoteArgs,
                                  @QueryParam("timeout") final int timeout) {
        return Response.status(executeCommandLine(new DefaultCommand(CMD_PROCESS,
                        Arrays.asList("/c", formatTasks(JAVA_TASKS_KILLER, javaTasks)), timeout), quoteArgs) != -1 ?
                Response.Status.OK : Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/commonTasks")
    public Response killCommonTasks(final List<String> commonTasks, @QueryParam("quoteArgs") final boolean quoteArgs,
                                    @QueryParam("timeout") final int timeout) {
        return Response.status(executeCommandLine(new DefaultCommand(CMD_PROCESS,
                Arrays.asList("/c", formatTasks(COMMON_TASKS_KILLER, commonTasks)), timeout), quoteArgs) != -1 ?
                Response.Status.OK : Response.Status.NOT_FOUND).build();
    }

    private String formatTasks(final String rootCommand, final List<String> tasks) {
        return rootCommand
                .replace("?", tasks.toString().replaceAll("\\[|\\]", "").replaceAll(",", ""));
    }
}

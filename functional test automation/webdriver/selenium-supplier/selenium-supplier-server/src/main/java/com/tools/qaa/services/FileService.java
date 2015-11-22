package com.tools.qaa.services;

import static com.tools.qaa.utils.FilesUtils.*;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("/content")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FileService {

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(final FormDataMultiPart form, @QueryParam("saveTo") final String saveToPath,
                               @QueryParam("unZip") final boolean unZip) {
        return Response.status(form.getFields("file")
                .parallelStream()
                .flatMap(filePart -> saveFile(filePart.getValueAs(InputStream.class),
                        formatPath(filePart.getContentDisposition().getFileName(), saveToPath), unZip).stream())
                .filter(response -> response.equals(Response.Status.INTERNAL_SERVER_ERROR))
                .findAny()
                .orElse(Response.Status.OK))
                .build();
    }
}
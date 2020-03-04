package fr.unice.polytech.rythmML.shell.controller;

import fr.unice.polytech.rythmML.shell.WorkspaceConfig;
import fr.unice.polytech.rythmML.shell.component.Visualize;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FileController {

	@GetMapping(
			value = "/get-file",
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
	)
	public @ResponseBody byte[] getFile() throws IOException {
		InputStream in = new FileInputStream(WorkspaceConfig.DIRECTORY + "/tmp.mid");
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		return IOUtils.toByteArray(in);
	}
}

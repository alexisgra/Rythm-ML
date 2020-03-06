package fr.unice.polytech.rythmML.shell.component;
import fr.unice.polytech.rythmML.shell.WorkspaceConfig;
import org.apache.commons.io.FileUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.FileSystemUtils;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Commands that relies on rythm ml instruments.
 */
@ShellComponent
public class Workfile {

	/**
	 * Setup the workfile and the workspace.
	 *
	 */
	@ShellMethod(value = "argument : FILE_RELATIVE_PATH - Setup the workfile (ie. the partition written in rythmML)", key = "workfile")
	public String workspace(@ShellOption(help = "FILE_RELATIVE_PATH") final String workfile) throws IOException, URISyntaxException {
		File file = new File(workfile);
		if(file.exists()) {
			WorkspaceConfig.WORKSPACE = workfile;
			WorkspaceConfig.DIRECTORY = workfile.substring(0, workfile.lastIndexOf("/"));
			File tmp = new File(WorkspaceConfig.DIRECTORY+"/tmp");
			tmp.mkdirs();
			FileUtils.copyInputStreamToFile(Workfile.class.getClassLoader().getResourceAsStream("index.html"), new File(WorkspaceConfig.DIRECTORY + "/tmp/index.html"));
			FileUtils.copyInputStreamToFile(Workfile.class.getClassLoader().getResourceAsStream("styles.css"), new File(WorkspaceConfig.DIRECTORY + "/tmp/styles.css"));
			FileUtils.copyInputStreamToFile(Workfile.class.getClassLoader().getResourceAsStream("midi-visualizer.js"), new File(WorkspaceConfig.DIRECTORY + "/tmp/midi-visualizer.js"));
			return "Workfile loaded.";
		} else {
			return "Cannot load workfile, check if the path is ok.";
		}
	}

	@PreDestroy
	public void destroy() {
		FileSystemUtils.deleteRecursively(new File(WorkspaceConfig.DIRECTORY+"/tmp"));
	}

}

package fr.unice.polytech.rythmML.shell.component;
import fr.unice.polytech.rythmML.kernel.data.DrumsElements;
import fr.unice.polytech.rythmML.shell.WorkspaceConfig;
import fr.unice.polytech.rythmML.shell.visualizer.OpenBrowser;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Commands that relies on rythm ml instruments.
 */
@ShellComponent
public class Workspace {

	/**
	 * Setup the workspace.
	 *
	 */
	@ShellMethod(value = "Setup the workfile (ie. the partition written in rythmML", key = "workfile")
	public String workspace(final String workspace) throws IOException, URISyntaxException {
		File file = new File(workspace);
		if(file.exists()) {
			WorkspaceConfig.WORKSPACE = workspace;
			WorkspaceConfig.DIRECTORY = workspace.substring(0,workspace.lastIndexOf("/"));
			File tmp = new File(WorkspaceConfig.DIRECTORY+"/tmp");
			tmp.mkdirs();
			FileUtils.copyInputStreamToFile(Workspace.class.getClassLoader().getResourceAsStream("index.html"), new File(WorkspaceConfig.DIRECTORY + "/tmp/index.html"));
			FileUtils.copyInputStreamToFile(Workspace.class.getClassLoader().getResourceAsStream("styles.css"), new File(WorkspaceConfig.DIRECTORY + "/tmp/styles.css"));
			FileUtils.copyInputStreamToFile(Workspace.class.getClassLoader().getResourceAsStream("midi-visualizer.js"), new File(WorkspaceConfig.DIRECTORY + "/tmp/midi-visualizer.js"));
			return "Workspace loaded.";
		} else {
			return "Cannot load workspace, check if the path is ok.";
		}
	}

}

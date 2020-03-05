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

/**
 * Commands that relies on rythm ml instruments.
 */
@ShellComponent
public class Workspace {

	/**
	 * Setup the workspace.
	 *
	 */
	@ShellMethod("Setup the workspace")
	public String workspace(final String workspace) throws IOException {
		File file = new File(workspace);
		if(file.exists()) {
			WorkspaceConfig.WORKSPACE = workspace;
			WorkspaceConfig.DIRECTORY = workspace.substring(0,workspace.lastIndexOf("/"));
			FileUtils.copyFile(new File(Workspace.class.getClassLoader().getResource("index.html").getPath()), new File(WorkspaceConfig.DIRECTORY + "/tmp/index.html"));
			FileUtils.copyFile(new File(Workspace.class.getClassLoader().getResource("styles.css").getPath()), new File(WorkspaceConfig.DIRECTORY + "/tmp/styles.css"));
			FileUtils.copyFile(new File(Workspace.class.getClassLoader().getResource("midi-visualizer.js").getPath()), new File(WorkspaceConfig.DIRECTORY + "/tmp/midi-visualizer.js"));
			return "Workspace loaded.";
		} else {
			return "Cannot load workspace, check if the path is ok.";
		}
	}

}

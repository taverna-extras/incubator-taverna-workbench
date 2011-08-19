/**
 *
 */
package net.sf.taverna.t2.workbench.ui.views.contextualviews.dataflow;

import java.util.Arrays;
import java.util.List;

import net.sf.taverna.t2.workbench.configuration.colour.ColourManager;
import net.sf.taverna.t2.workbench.file.FileManager;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ContextualViewFactory;
import net.sf.taverna.t2.workflowmodel.Dataflow;

/**
 * @author alanrw
 *
 */
public class DataflowContextualViewFactory implements
		ContextualViewFactory<Dataflow> {

	private FileManager fileManager;
	private ColourManager colourManager;

	public boolean canHandle(Object selection) {
		return selection instanceof Dataflow;
	}

	public List<ContextualView> getViews(Dataflow selection) {
		return Arrays.asList(new ContextualView[] {new DataflowContextualView(selection, fileManager, colourManager)});
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public void setColourManager(ColourManager colourManager) {
		this.colourManager = colourManager;
	}

}

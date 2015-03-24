/*******************************************************************************
 * Copyright (C) 2007 The University of Manchester
 *
 *  Modifications to the initial code base are copyright of their
 *  respective authors, or their employers as appropriate.
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1 of
 *  the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 ******************************************************************************/
package net.sf.taverna.t2.activities.stringconstant.views;

import static java.util.Arrays.asList;

import java.net.URI;
import java.util.List;

import net.sf.taverna.t2.servicedescriptions.ServiceDescriptionRegistry;
import net.sf.taverna.t2.workbench.activityicons.ActivityIconManager;
import net.sf.taverna.t2.workbench.configuration.colour.ColourManager;
import net.sf.taverna.t2.workbench.edits.EditManager;
import net.sf.taverna.t2.workbench.file.FileManager;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import net.sf.taverna.t2.workbench.ui.views.contextualviews.activity.ContextualViewFactory;
import uk.org.taverna.commons.services.ServiceRegistry;
import org.apache.taverna.scufl2.api.activity.Activity;

public class StringConstantActivityViewFactory implements
		ContextualViewFactory<Activity> {
	private static final URI ACTIVITY_TYPE = URI
			.create("http://ns.taverna.org.uk/2010/activity/constant");

	private EditManager editManager;
	private FileManager fileManager;
	private ActivityIconManager activityIconManager;
	private ColourManager colourManager;
	private ServiceDescriptionRegistry serviceDescriptionRegistry;
	private ServiceRegistry serviceRegistry;

	@Override
	public boolean canHandle(Object object) {
		return object instanceof Activity
				&& ((Activity) object).getType().equals(ACTIVITY_TYPE);
	}

	@Override
	public List<ContextualView> getViews(Activity activity) {
		return asList(new ContextualView[] { new StringConstantActivityContextualView(
				activity, editManager, fileManager, activityIconManager,
				colourManager, serviceDescriptionRegistry, serviceRegistry) });
	}

	public void setEditManager(EditManager editManager) {
		this.editManager = editManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public void setActivityIconManager(ActivityIconManager activityIconManager) {
		this.activityIconManager = activityIconManager;
	}

	public void setColourManager(ColourManager colourManager) {
		this.colourManager = colourManager;
	}

	public void setServiceDescriptionRegistry(
			ServiceDescriptionRegistry serviceDescriptionRegistry) {
		this.serviceDescriptionRegistry = serviceDescriptionRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
}

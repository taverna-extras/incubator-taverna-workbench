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
package net.sf.taverna.t2.workbench.views.graph.toolbar;

import static net.sf.taverna.t2.workbench.views.graph.toolbar.GraphEditToolbarSection.GRAPH_EDIT_TOOLBAR_SECTION;

import java.net.URI;

import javax.swing.Action;

import org.apache.taverna.ui.menu.AbstractMenuAction;
import org.apache.taverna.workbench.edits.EditManager;
import org.apache.taverna.workbench.selection.SelectionManager;
import net.sf.taverna.t2.workbench.views.graph.actions.RenameWFInputOutputProcessorAction;

/**
 * @author Alex Nenadic
 */
public class RenameWFInputOutputProcessorToolbarAction extends
		AbstractMenuAction {
	private static final URI RENAME_WF_INPUT_OUTPUT_PROCESSOR_URI = URI
			.create("http://taverna.sf.net/2008/t2workbench/menu#graphToolbarRenameWFInputOutputProcessor");

	private EditManager editManager;
	private SelectionManager selectionManager;

	public RenameWFInputOutputProcessorToolbarAction() {
		super(GRAPH_EDIT_TOOLBAR_SECTION, 30,
				RENAME_WF_INPUT_OUTPUT_PROCESSOR_URI);
	}

	@Override
	protected Action createAction() {
		return new RenameWFInputOutputProcessorAction(editManager,
				selectionManager);
	}

	public void setEditManager(EditManager editManager) {
		this.editManager = editManager;
	}

	public void setSelectionManager(SelectionManager selectionManager) {
		this.selectionManager = selectionManager;
	}
}

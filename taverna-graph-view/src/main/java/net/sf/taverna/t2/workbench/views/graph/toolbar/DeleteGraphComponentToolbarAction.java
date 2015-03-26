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

import static net.sf.taverna.t2.workbench.views.graph.toolbar.GraphDeleteToolbarSection.GRAPH_DELETE_TOOLBAR_SECTION;

import java.net.URI;

import javax.swing.Action;

import org.apache.taverna.ui.menu.AbstractMenuAction;
import org.apache.taverna.workbench.edits.EditManager;
import org.apache.taverna.workbench.selection.SelectionManager;
import net.sf.taverna.t2.workbench.views.graph.actions.DeleteGraphComponentAction;

/**
 * @author Alex Nenadic
 */
public class DeleteGraphComponentToolbarAction extends AbstractMenuAction {
	private static final URI DELETE_GRAPH_COMPONENT_URI = URI
			.create("http://taverna.sf.net/2008/t2workbench/menu#graphToolbarDeleteGraphComponent");

	private EditManager editManager;
	private SelectionManager selectionManager;

	public DeleteGraphComponentToolbarAction() {
		super(GRAPH_DELETE_TOOLBAR_SECTION, 10, DELETE_GRAPH_COMPONENT_URI);
	}

	@Override
	protected Action createAction() {
		return new DeleteGraphComponentAction(editManager, selectionManager);
	}

	public void setEditManager(EditManager editManager) {
		this.editManager = editManager;
	}

	public void setSelectionManager(SelectionManager selectionManager) {
		this.selectionManager = selectionManager;
	}
}

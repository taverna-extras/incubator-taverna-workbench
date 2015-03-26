/*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements. See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership. The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.apache.taverna.workbench.edits.impl.toolbar;

import static org.apache.taverna.workbench.edits.impl.toolbar.EditToolbarSection.EDIT_TOOLBAR_SECTION;

import java.net.URI;

import javax.swing.Action;

import org.apache.taverna.ui.menu.AbstractMenuAction;
import org.apache.taverna.workbench.edits.impl.menu.RedoMenuAction;

public class RedoToolbarAction extends AbstractMenuAction {
	private static final URI EDIT_TOOLBAR_REDO_URI = URI
			.create("http://taverna.sf.net/2008/t2workbench/menu#editToolbarRedo");
	private final RedoMenuAction redoMenuAction;

	public RedoToolbarAction(RedoMenuAction redoMenuAction) {
		super(EDIT_TOOLBAR_SECTION, 20, EDIT_TOOLBAR_REDO_URI);
		this.redoMenuAction = redoMenuAction;
	}

	@Override
	protected Action createAction() {
		return redoMenuAction.getAction();
	}
}
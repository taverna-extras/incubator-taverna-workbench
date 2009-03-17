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
package net.sf.taverna.t2.workbench.ui.views.contextualviews.merge;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.taverna.t2.workbench.ui.views.contextualviews.ContextualView;
import net.sf.taverna.t2.workflowmodel.Dataflow;
import net.sf.taverna.t2.workflowmodel.Datalink;
import net.sf.taverna.t2.workflowmodel.EventForwardingOutputPort;
import net.sf.taverna.t2.workflowmodel.EventHandlingInputPort;
import net.sf.taverna.t2.workflowmodel.Merge;
import net.sf.taverna.t2.workflowmodel.MergeInputPort;
import net.sf.taverna.t2.workflowmodel.TokenProcessingEntity;
import net.sf.taverna.t2.workflowmodel.utils.Tools;

/**
 * Contextual view for dataflow's merges.
 * 
 * @author Alex Nenadic
 *
 */
@SuppressWarnings("serial")
public class MergeContextualView extends ContextualView{
	
	private Merge merge;
	private Dataflow workflow;
	private JEditorPane editorPane;

	public MergeContextualView(Merge merge, Dataflow workflow) {
		this.merge = merge;
		this.workflow = workflow;
		initView();
	}

	@Override
	public JComponent getMainFrame() {
		return panelForHtml(buildHtml());
	}
	
	@Override
	public String getViewTitle() {
		return "Merge " + merge.getLocalName();
	}
	

	/**
	 * Update the view with the latest information 
	 * from the configuration bean.
	 */
	@Override
	public void refreshView() {
		editorPane.setText(buildHtml());
	}

	private String buildHtml() {
		String html = "<html><head>" + getStyle() + "</head><body>";
		html += buildTableOpeningTag();
		html += "<tr><td colspan=\"2\">" + getViewTitle() + "</td></tr>";
			
		html += "<tr><td colspan=\"2\"><b>Ordered incoming links (entity.port -> merge.port)</b></td></tr>";
		int counter = 1;
		for (MergeInputPort mergeInputPort : merge.getInputPorts()){	
			EventForwardingOutputPort sourcePort = mergeInputPort.getIncomingLink().getSource();
			// Get the name TokenProcessingEntity (Processor or another Merge or Dataflow) and 
			// its port that contains the source EventForwardingOutputPort
			TokenProcessingEntity entity = Tools.getTokenProcessingEntityWithEventForwardingOutputPort(sourcePort, workflow);
			if (entity != null){
				html += "<tr><td>"+ (counter++) + ".</td><td>" + entity.getLocalName() + "."
						+ sourcePort.getName() + " -> " + merge.getLocalName() + "." + mergeInputPort.getName()
						+ "</td></tr>";
			}
			
		}
				
		html += "<tr><td colspan=\"2\"><b>Outgoing link (merge.port -> entity.port)</b></td></tr>";
		Object[] links = merge.getOutputPort().getOutgoingLinks().toArray();	
		// There will be only one link in the set
		EventHandlingInputPort targetPort = ((Datalink) links[0]).getSink();
		TokenProcessingEntity entity = Tools.getTokenProcessingEntityWithEventHandlingInputPort(targetPort,workflow);
		// Find the other part of the link (if any - could have been deleted)
		if (entity != null){
			html += "<tr><td>&nbsp;</td><td>" + merge.getLocalName() +"." + merge.getOutputPort().getName() + " -> " + entity.getLocalName() + "." + targetPort.getName() + "</td></tr>";
		}
		
		html += "</table>";
		html += "</body></html>";
		return html;
	}

	private String buildTableOpeningTag() {
		String result = "<table ";
		Map<String, String> props = getTableProperties();
		for (String key : props.keySet()) {
			result += key + "=\"" + props.get(key) + "\" ";
		}
		result += ">";
		return result;
	}

	protected Map<String, String> getTableProperties() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("border", "1");
		return result;
	}

	protected String getStyle() {
		String style = "<style type='text/css'>";
		style += "table {align:center; border:solid black 1px; background-color:\"77aadd\";width:100%; height:100%; overflow:auto;}";
		style += "</style>";
		return style;
	}

	protected JPanel panelForHtml(String html) {
		JPanel result = new JPanel();

		result.setLayout(new BorderLayout());
		editorPane = new JEditorPane("text/html", html);
		editorPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(editorPane);
		result.add(scrollPane, BorderLayout.CENTER);
		return result;
	}
	
	

}

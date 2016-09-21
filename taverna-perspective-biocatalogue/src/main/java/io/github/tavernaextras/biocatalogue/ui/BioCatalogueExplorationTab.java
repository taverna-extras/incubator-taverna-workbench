package io.github.tavernaextras.biocatalogue.ui;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutFocusTraversalPolicy;

import io.github.tavernaextras.biocatalogue.model.connectivity.BioCatalogueClient;
import io.github.tavernaextras.biocatalogue.ui.search_results.SearchResultsMainPanel;
import io.github.tavernaextras.biocatalogue.MainComponent;
import io.github.tavernaextras.biocatalogue.MainComponentFactory;

import org.apache.log4j.Logger;


/**
 * 
 * @author Sergejs Aleksejevs
 */
@SuppressWarnings("serial")
public class BioCatalogueExplorationTab extends JPanel implements HasDefaultFocusCapability
{
  private final MainComponent pluginPerspectiveMainComponent;
  private final BioCatalogueClient client;
  private final Logger logger;
  
  
  // COMPONENTS
  private BioCatalogueExplorationTab thisPanel;
  
  private SearchOptionsPanel searchOptionsPanel;
  private SearchResultsMainPanel tabbedSearchResultsPanel;
  
  
  public BioCatalogueExplorationTab()
  {
    this.thisPanel = this;
    
    this.pluginPerspectiveMainComponent = MainComponentFactory.getSharedInstance();
    this.client = BioCatalogueClient.getInstance();
    this.logger = Logger.getLogger(this.getClass());
    
    initialiseUI();
    
    // this is to make sure that search will get focused when this tab is opened
    // -- is a workaround to a bug in JVM
    setFocusCycleRoot(true);
    setFocusTraversalPolicy(new LayoutFocusTraversalPolicy() {
      public Component getDefaultComponent(Container cont) {
          return (thisPanel.getDefaultComponent());
      }
    });
  }
  
  
  private void initialiseUI()
  {
    this.tabbedSearchResultsPanel = new SearchResultsMainPanel();
    this.searchOptionsPanel = new SearchOptionsPanel(tabbedSearchResultsPanel);
    
    
    this.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0.0;
    c.anchor = GridBagConstraints.WEST;
    c.insets = new Insets(3,10,3,10);
    String baseString= "<html><b>Using service catalogue at </b>" + client.getBaseURL() + "</html>";
    this.add(new JLabel(baseString), c);

    
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0.1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.EAST;
    c.insets = new Insets(3,30,3,10);
    
    this.add(searchOptionsPanel, c);
    
    c.insets = new Insets(0,0,0,0);
    c.gridy++;
    c.gridx = 0;
    c.gridwidth = 2;
    c.weightx = c.weighty = 1.0;
    c.fill = GridBagConstraints.BOTH;
    c.anchor = GridBagConstraints.CENTER;
    this.add(tabbedSearchResultsPanel, c);
    
    this.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
  }
  
  
  public SearchResultsMainPanel getTabbedSearchResultsPanel() {
    return tabbedSearchResultsPanel;
  }
  
  
  
  // *** Callbacks for HasDefaultFocusCapability interface ***
  
  public void focusDefaultComponent() {
    this.searchOptionsPanel.focusDefaultComponent();
  }
  
  public Component getDefaultComponent() {
    return (this.searchOptionsPanel.getDefaultComponent());
  }
  
  // *********************************************************
  
  
  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.getContentPane().add(new BioCatalogueExplorationTab());
    f.setSize(1000, 800);
    f.setLocationRelativeTo(null);
    
    f.setVisible(true);
  }
}

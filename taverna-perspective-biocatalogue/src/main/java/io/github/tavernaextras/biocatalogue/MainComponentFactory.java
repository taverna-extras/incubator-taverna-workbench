package io.github.tavernaextras.biocatalogue;
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

import javax.swing.ImageIcon;

import org.apache.taverna.workbench.icons.WorkbenchIcons;
import org.apache.taverna.workbench.ui.zaria.UIComponentFactorySPI;
import org.apache.taverna.workbench.ui.zaria.UIComponentSPI;

/**
 * @author Sergejs Aleksejevs
 */
public class MainComponentFactory implements UIComponentFactorySPI
{
  // this is to ensure that the whole perspective is not re-created
  // each time it is being activated in Taverna, rather it will only
  // happen once during the execution
  private static MainComponent mainPerspectiveComponent = null;
  
	public static MainComponent getSharedInstance()
	{
	  // double-check on existence of the 'mainPerspectiveComponent' ensures
    // that it is really created only once
    if (mainPerspectiveComponent == null) {
      synchronized(MainComponentFactory.class) {
        if (mainPerspectiveComponent == null) {
          mainPerspectiveComponent = new MainComponent();
        }
      }
    }
    return (mainPerspectiveComponent);
	}
	
	public UIComponentSPI getComponent() {
    return (getSharedInstance());
  }
	
	
	public ImageIcon getIcon() {
		return WorkbenchIcons.databaseIcon;
	}

	public String getName() {
		return "Service Catalogue Main Component Factory";
	}

}

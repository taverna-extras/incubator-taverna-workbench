/*******************************************************************************
 * Copyright (C) 2008-2010 The University of Manchester   
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
package net.sf.taverna.t2.workbench.ui.credentialmanager.password;

import org.apache.taverna.security.credentialmanager.MasterPasswordProvider;

public class AskUserMasterPasswordProvider implements MasterPasswordProvider{

//	@Override
//	public boolean canProvideMasterPassword() {
//		// TODO Auto-generated method stub
//		return false;
//	}
	private int priority = 100;

	@Override
	public String getMasterPassword(boolean firstTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getProviderPriority() {
		return priority;
	}

	@Override
	public void setMasterPassword(String password) {
		// TODO Auto-generated method stub	
	}
	
//	@Override
//	public void setProviderPriority(int priority) {
//		this.priority = priority;
//	}
	
}

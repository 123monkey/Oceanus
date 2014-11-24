/*
 *  Copyright Beijing 58 Information Technology Co.,Ltd.
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package com.bj58.oceanus.result.merger;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bj58.oceanus.core.jdbc.result.ColumnFinder;
import com.bj58.oceanus.core.jdbc.result.RowSet;
import com.bj58.oceanus.core.jdbc.result.RowSetCreator;
import com.bj58.oceanus.core.jdbc.result.SimpleRowSet;

/**
 * DefaultRowSetCreator
 * 
 * @author Service Platform Architecture Team (spat@58.com)
 */
public class DefaultRowSetCreator implements RowSetCreator {
	

	@Override
	public RowSet create(ResultSet resultSet, ColumnFinder columnFinder) throws SQLException { 
		int count=resultSet.getMetaData().getColumnCount();
		Object []values=new Object[count];
		for(int i=0;i<values.length;i++){
			values[i]=resultSet.getObject(i+1);
		}
		SimpleRowSet rowSet=new SimpleRowSet(values,columnFinder);
		rowSet.setResultSet(resultSet);
		return rowSet;
	}

}

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
package com.bj58.oceanus.core.timetracker.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bj58.oceanus.core.timetracker.TrackPoint;
import com.bj58.oceanus.core.timetracker.TrackerHodler;

/**
 * 耗时监控处理器：sql的执行耗时
 * 
 * @author Service Platform Architecture Team (spat@58.com)
 */
public class ExecuteSqlHandler extends TrackPointHandler{

	private static Logger logger = LoggerFactory.getLogger(ExecuteSqlHandler.class);
	
	@Override
	protected void handle(TrackerHodler trackerHodler, String tableName, String sql, long costTime) {
		
		if(logger.isDebugEnabled()){
			logger.debug("PointHook [" + TrackPoint.EXECUTE_SQL.name() + 
					"] BaseExecuteHandler execute sql [" + sql + 
					"] cost " + costTime + " ms");
		}
		
		if(trackerHodler!=null && costTime>trackerHodler.getThreshold()) {
			TrackResult trackResult = new TrackResult();
			trackResult.setTableName(tableName);
			trackResult.setCostTime(costTime);
			trackResult.setSql(sql);
			
			super.asyncDoTrack(trackerHodler.getTracker(), trackResult);
		}
	}

}

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
package com.bj58.oceanus.config;

/**
 * 连接池配置实体
 * 
 * @author Service Platform Architecture Team (spat@58.com)
 */
public class ThreadPoolConfig implements Config {

	private static final long serialVersionUID = 1L;
	private static final Integer DEFAULT_CORE_SIZE = 100;
	private static final Integer DEFAULT_QUEUE_SIZE = 2048;
	
	private String id;
	private Integer coreSize = DEFAULT_CORE_SIZE;
	private Integer queueSize = DEFAULT_QUEUE_SIZE;
	private Long timeout = new Long(0);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCoreSize() {
		return coreSize;
	}

	public void setCoreSize(Integer coreSize) {
		this.coreSize = coreSize;
	}

	public Integer getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(Integer queueSize) {
		this.queueSize = queueSize;
	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		return "ThreadPoolConfig [id=" + id + ", coreSize=" + coreSize
				+ ", queueSize=" + queueSize + ", timeout=" + timeout + "]";
	}

}

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
package com.bj58.oceanus.exchange.nodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bj58.oceanus.core.shard.AnalyzeResult;
import com.bj58.sql.parser.ConstantNode;
import com.bj58.sql.parser.NodeTypes;
import com.bj58.sql.parser.QueryTreeNode;

/**
 * SQL节点解析器基类
 * 
 * @author Service Platform Architecture Team (spat@58.com)
 */
public abstract class AbstractNodeAnalyzer<N extends QueryTreeNode, R extends AnalyzeResult>
		implements NodeAnalyzer<N, R> {
	protected static Logger logger = LoggerFactory
			.getLogger(AbstractNodeAnalyzer.class);

	@Override
	public R analyze(N node) {
		for (int nodeType : getNodeTypes()) {
			if (node.getNodeType() == nodeType) {
				return doAnalyze(node);
			}
		}

		throw new IllegalArgumentException("node type not match!node=" + node);
	}

	public abstract R doAnalyze(N node);

	protected void analyzeAndMergeResult(DefaultAnalyzeResult result,
			QueryTreeNode node) {
		if (node != null) {
			if (node instanceof ConstantNode
					|| node.getNodeType() == NodeTypes.PARAMETER_NODE
					|| node.getNodeType() == NodeTypes.COLUMN_REFERENCE) {
				return;
			}
			NodeAnalyzer<QueryTreeNode, AnalyzeResult> analyzer = Analyzers
					.get(node);
			if (analyzer == null) {
				logger.error("analyzer is null!node=" + node);
				return;
			}
			AnalyzeResult fromListResult = analyzer.analyze(node);
			if (fromListResult == null) {
				return;
			}
			result.addTables(fromListResult.getTableInfos());
			result.addConditionColumns(fromListResult.getConditionColumns());
			result.getAggregateColumns().addAll(
					fromListResult.getAggregateColumns()); 
		}
	}

}

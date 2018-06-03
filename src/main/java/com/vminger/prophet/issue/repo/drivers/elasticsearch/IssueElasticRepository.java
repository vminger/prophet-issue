/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.repo.drivers.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface IssueElasticRepository extends
    ElasticsearchRepository<IssueEntityElastic, String> {

}

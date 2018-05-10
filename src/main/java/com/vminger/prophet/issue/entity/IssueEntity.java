/*
 * Copyright ©2018 VMINGER Co., Ltd. All Rights Reserved.
 */

package com.vminger.prophet.issue.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

@Configuration
public class IssueEntity implements Serializable {

	private static final long serialVersionUID = -748388722236389944L;
	
	private String id;
	private String topic;
	private String k12n;
	private String subject;
	private String type;
	private String created_at;
	private String updated_at;
	private String user_id;
	private String from_url;
	private float dod;
	private Map<String, Boolean> qas = new HashMap<String, Boolean>();
	private List<String> tags = new LinkedList<String>();
	
	public IssueEntity() {
		
	}
	
	public IssueEntity(String id, String topic, String k12n, String subject,
			String type, String created_at, String updated_at,
			String user_id, String from_url,float dod, 
			Map<String, Boolean> qas, List<String> tags) {
		super();
		this.id = id;
		this.topic = topic;
		this.k12n = k12n;
		this.subject = subject;
		this.dod = dod;
		this.type = type;
		this.qas = qas;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.user_id = user_id;
		this.from_url = from_url;
		this.tags = tags;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getK12n() {
		return k12n;
	}
	
	public void setK12n(String k12n) {
		this.k12n = k12n;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public float getDod() {
		return dod;
	}
	
	public void setDod(float dod) {
		this.dod = dod;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Map<String, Boolean> getQas() {
		return qas;
	}
	
	public void setQas(Map<String, Boolean> qas) {
		this.qas = qas;
	}
	
	public String getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public String getUpdated_at() {
		return updated_at;
	}
	
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getFrom_url() {
		return from_url;
	}
	
	public void setFrom_url(String from_url) {
		this.from_url = from_url;
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
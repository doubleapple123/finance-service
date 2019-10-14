package io.myfunstuff.stocks.model;

public class Stuff {
	
	public static final String DEFAULT_PROJECT = "My Fun Stuff";
	public static final String DEFAULT_SERVICE = "Finance";
	
	private String project;
	private String service;
	
	public Stuff() {
		project = DEFAULT_PROJECT;
		service = DEFAULT_SERVICE;
	}

	public Stuff(String project, String service) {
		this.project = project;
		this.service = service;
	}
	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
}

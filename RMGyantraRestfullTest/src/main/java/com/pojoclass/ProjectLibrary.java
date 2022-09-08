package com.pojoclass;

public class ProjectLibrary {
	
	 String CreatedBy;
	 String projectName;
	 String status;
	 int teamSize;
	public ProjectLibrary(String createdBy, String projectName, String status, int teamSize) {
		super();
		this.CreatedBy = createdBy;
		this.projectName = projectName;
		this.status = status;
		this.teamSize = teamSize;
	}
	 public ProjectLibrary() 
	 {
		 
	 }
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	
}

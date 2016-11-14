package com.job.core.model;

import java.util.Date;

/**
 * 定时任务日志实体
 * 
 * @author zengxing
 *
 */
public class JobLog {
	private int id;
	// job info
	private String jobGroup;
	private String jobName;
	private String jobCron;
	private String jobDesc;
	private String jobClass;
	private String jobData;
	// trigger info
	private Date triggerTime;
	private String triggerStatus;
	private String triggerMsg;
	// handle info
	private Date handleTime;
	private String handleStatus;
	private String handleMsg;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobCron() {
		return jobCron;
	}

	public void setJobCron(String jobCron) {
		this.jobCron = jobCron;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobData() {
		return jobData;
	}

	public void setJobData(String jobData) {
		this.jobData = jobData;
	}

	public Date getTriggerTime() {
		return triggerTime;
	}

	public void setTriggerTime(Date triggerTime) {
		this.triggerTime = triggerTime;
	}

	public String getTriggerStatus() {
		return triggerStatus;
	}

	public void setTriggerStatus(String triggerStatus) {
		this.triggerStatus = triggerStatus;
	}

	public String getTriggerMsg() {
		return triggerMsg;
	}

	public void setTriggerMsg(String triggerMsg) {
		this.triggerMsg = triggerMsg;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getHandleMsg() {
		return handleMsg;
	}

	public void setHandleMsg(String handleMsg) {
		this.handleMsg = handleMsg;
	}

	@Override
	public String toString() {
		return "JobLog [id=" + id + ", jobGroup=" + jobGroup + ", jobName=" + jobName + ", jobCron=" + jobCron
				+ ", jobDesc=" + jobDesc + ", jobClass=" + jobClass + ", jobData=" + jobData + ", triggerTime="
				+ triggerTime + ", triggerStatus=" + triggerStatus + ", triggerMsg=" + triggerMsg + ", handleTime="
				+ handleTime + ", handleStatus=" + handleStatus + ", handleMsg=" + handleMsg + "]";
	}

}

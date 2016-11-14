package com.job.core.model;

import java.util.Date;

/**
 * 定时任务信息
 * @author zengxing
 *
 */
public class JobInfo {
	private int id;
	private String jobGroup;	// 任务组
	private String jobName;		// 任务名
	private String jobCron;		// CRON表达式
	private String jobDesc;		// 任务描述
	private String jobClass;	// 任务执行JobBean
	private String jobData;		// 任务执行数据 Map-JSON-String
	private String jobStatus;	// 任务状态
	
	private Date addTime;		// 任务添加时间
	private Date updateTime;	// 任务更新时间
	private String author;		// 负责人
	private String alarmEmail;	// 报警邮件
	private int alarmThreshold;	// 报警阈值
	
	private int glueSwitch;		// GLUE模式开关：0-否，1-是
	private String flueSource;	// GLUE源代码
	private String glueRemark;	// GLUE备注
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
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAlarmEmail() {
		return alarmEmail;
	}
	public void setAlarmEmail(String alarmEmail) {
		this.alarmEmail = alarmEmail;
	}
	public int getAlarmThreshold() {
		return alarmThreshold;
	}
	public void setAlarmThreshold(int alarmThreshold) {
		this.alarmThreshold = alarmThreshold;
	}
	public int getGlueSwitch() {
		return glueSwitch;
	}
	public void setGlueSwitch(int glueSwitch) {
		this.glueSwitch = glueSwitch;
	}
	public String getFlueSource() {
		return flueSource;
	}
	public void setFlueSource(String flueSource) {
		this.flueSource = flueSource;
	}
	public String getGlueRemark() {
		return glueRemark;
	}
	public void setGlueRemark(String glueRemark) {
		this.glueRemark = glueRemark;
	}

	@Override
	public String toString() {
		return "JobInfo [id=" + id + ", jobGroup=" + jobGroup + ", jobName=" + jobName + ", jobCron=" + jobCron
				+ ", jobDesc=" + jobDesc + ", jobClass=" + jobClass + ", jobData=" + jobData + ", jobStatus="
				+ jobStatus + ", addTime=" + addTime + ", updateTime=" + updateTime + ", author=" + author
				+ ", alarmEmail=" + alarmEmail + ", alarmThreshold=" + alarmThreshold + ", glueSwitch=" + glueSwitch
				+ ", flueSource=" + flueSource + ", glueRemark=" + glueRemark + "]";
	}

}

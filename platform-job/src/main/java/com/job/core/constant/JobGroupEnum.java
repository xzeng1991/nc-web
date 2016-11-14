package com.job.core.constant;

import org.apache.commons.lang.StringUtils;

public enum JobGroupEnum {
	DEFAULT("默认"),
	WAIMAI("外卖"),
	MOVIE("电影");
	private String desc;
	private JobGroupEnum(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}
	
	/**
	 * 通过名称来匹配枚举
	 * @param name
	 * @return
	 */
	public static JobGroupEnum match(String name){
		if(StringUtils.isBlank(name)){
			return null;
		}
		
		for(JobGroupEnum group : JobGroupEnum.values()){
			if(group.name().equals(name)){
				return group;
			}
		}
		return null;
	}
}

package com.mvc.app.data.constraint.validaton.aop;

import java.util.Map;

public class ValidationConfiguration {
	
	public Class<?> targetDomain;
	
	public Map<String, Class<?>> targetAndGroup;
	
	public String pointCut;

	public Class<?> getTargetDomain() {
		return targetDomain;
	}

	public void setTargetDomain(Class<?> targetDomain) {
		this.targetDomain = targetDomain;
	}

	public Map<String, Class<?>> getTargetAndGroup() {
		return targetAndGroup;
	}

	public void setTargetAndGroup(Map<String, Class<?>> targetAndGroup) {
		this.targetAndGroup = targetAndGroup;
	}

	public String getPointCut() {
		return pointCut;
	}

	public void setPointCut(String pointCut) {
		this.pointCut = pointCut;
	}

}

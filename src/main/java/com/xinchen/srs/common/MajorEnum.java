package com.xinchen.srs.common;

import org.springframework.util.StringUtils;

import com.google.common.collect.ImmutableMap;

public enum MajorEnum {
    UNKNOWN("UNKNOWN"), 
    CS("CS"),
    EE("EE"),
    PHYSICS("PHYSICS"),
    MATH("MATH");
	
	private final String _name;
	private static final ImmutableMap<String, MajorEnum> _nameValueMap;
	
	static {
		ImmutableMap.Builder<String, MajorEnum> nameMapBuilder = ImmutableMap.builder();
		
		for (MajorEnum major : MajorEnum.values()) {
			nameMapBuilder.put(major.name(), major);
		}
		
		_nameValueMap = nameMapBuilder.build();
	}
	
	MajorEnum(String name) {
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
	
	public static MajorEnum getTypeForName(String name) {
		if (StringUtils.isEmpty(name)) {
			return UNKNOWN;
		}
		return _nameValueMap.getOrDefault(name.toUpperCase(), UNKNOWN);
	}
}

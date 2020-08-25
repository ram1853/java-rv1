package com.members.registry.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This is a Singleton design pattern
 *
 */
public class MemberRegistryUtils {

	private Map<Integer, byte[]> fileData = new HashMap<>();

	private static MemberRegistryUtils utils = null;

	private MemberRegistryUtils() {}

	public static MemberRegistryUtils getUtils() {

		if(utils == null) {
			utils = new MemberRegistryUtils();
		}

		return utils;
	}

	public Map<Integer, byte[]> getFileData() {
		return fileData;
	}

	public void setFileData(int memberId, byte[] fileData) {
		this.fileData.put(memberId, fileData);
	}


}

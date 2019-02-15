package com.webdw.controller;

import java.util.HashMap;

public class CWebDWMemCache {
	// 采用HashMap进行对象的存储
	private static HashMap<String, DataWindowController> objStore = new HashMap<String, DataWindowController>();

	public static HashMap<String, DataWindowController> getObjStore() {
		return objStore;
	}

	public static void setObjStore(HashMap<String, DataWindowController> objStore) {
		CWebDWMemCache.objStore = objStore;
	}

	// 在内存中存储数据窗口对象
	// 未来可能改造为使用Redis进行存储
	// 暂时未考虑过期问题
	public static void saveDataWindowController(DataWindowController parentdw) {
		objStore.put(parentdw.uuid, parentdw);
		System.out.println("hashMap.size():" + objStore.size());
	}

	/**
	 * 读取数据窗口对象的存储值
	 * 
	 * @param parentDWUUID
	 * @return
	 */
	public static DataWindowController readParentDW(String parentDWUUID) {

		return objStore.get(parentDWUUID);
	}
}

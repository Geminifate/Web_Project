package cn.itcasr.shop.utils;

import java.util.UUID;

//��������ַ���
public class UUIDUtils {
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

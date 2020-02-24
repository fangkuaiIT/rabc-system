package com.lin.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * ID 生成工具类
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
public class IdUtils {


    private static SecureRandom random = new SecureRandom();
    private static IdWorker idWorker = new IdWorker(-1, -1);

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
     */
    public static String uuid2() {
        return UUID.randomUUID().toString();
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 基于 Base58 编码的 UUID 随机生成bytes
     *
     * @return the string
     * @author : yyfly / 2018-08-08
     */
    public static String base58Uuid() {
        UUID uuid = UUID.randomUUID();
        return base58Uuid(uuid);
    }

    protected static String base58Uuid(UUID uuid) {

        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return Base58Utils.encode(bb.array());
    }

    /**
     * 获取新唯一编号（18为数值）
     * 来自于twitter项目snowflake的id产生方案，全局唯一，时间有序。
     * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
     */
    public static String nextId() {
        return String.valueOf(idWorker.nextId());
    }

    /**
     * 获取新代码编号
     */
    public static String nextCode(String code) {
        if (code != null) {
            String str = code.trim();
            int len = str.length() - 1;
            int lastNotNumIndex = 0;
            for (int i = len; i >= 0; i--) {
                if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                    lastNotNumIndex = i;
                    break;
                }
            }
            // 如果最后一位是数字，并且last索引位置还在最后，则代表是纯数字，则最后一个不是数字的索引为-1
            if ((str.charAt(len) >= '0' && str.charAt(len) <= '9') && (lastNotNumIndex == len)) {
                lastNotNumIndex = -1;
            }

            String prefix = str.substring(0, lastNotNumIndex + 1);
            String numStr = str.substring(lastNotNumIndex + 1, str.length());
            long num = NumberUtils.toLong(numStr);
            System.out.println("处理前：" + str);
            str = prefix + StringUtils.leftPad(String.valueOf(num + 1), numStr.length(), "0");
            System.out.println("处理后：" + str);
            return str;
        }
        return null;
    }

//    public static void main(String[] args) {
//        System.out.println(uuid());
//        System.out.println(nextId());
//        System.out.println(nextCode("8"));
//        System.out.println(nextCode("09"));
//        System.out.println(nextCode("009"));
//        System.out.println(nextCode("E09"));
//        System.out.println(nextCode("EC09"));
//        System.out.println(nextCode("EC0101"));
//        System.out.println(nextCode("EC0109"));
//        System.out.println(nextCode("EC02T03"));
//        System.out.println(nextCode("EC02T099"));
//        System.out.println(nextCode("EC02T100"));
//        System.out.println(nextCode("EC02T10A"));
//		// 数值型ID重复验证测试
//		Set<String> set = SetUtils.newHashSet();
//		try{
//			for (int i=0; i<100; i++){
//				String id = String.valueOf(nextId());
//				if (set.contains(id)){
//					throw new Exception(id + " exists");
//				}
//				set.add(id);
//				System.out.println(id);
//				Thread.sleep(100);
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
}

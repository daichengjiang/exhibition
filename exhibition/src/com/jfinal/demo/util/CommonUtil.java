package com.jfinal.demo.util;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.common.Constant;
import com.jfinal.demo.model.User;

public class CommonUtil {
	
	/**
	* ��ȡIP��ַ<br>
	* ��һ�������ʹ��Request.getRemoteAddr()���ɣ����Ǿ���nginx�ȷ��������������������ʧЧ��<br>
	* �������ȴ�Header�л�ȡX-Real-IP������������ٴ�X-Forwarded-For��õ�һ��IP(��,�ָ�)��
	* ����������������Request .getRemoteAddr()��
	* @param request
	* @return IP
	*/
	public static String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// ��η���������ж��IPֵ����һ��Ϊ��ʵIP��
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	
	
    /**
     * �����㷨���ַ�������
     * @param ���ܵ��ַ���
     * @param �㷨
     * @return ���ܺ���ַ���
     */
    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return password;
        }
        md.reset();
        md.update(unencodedPassword);
        byte[] encodedPassword = md.digest();
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        return buf.toString();
    }
    
    /**
     * ��ȡsession�������û�
     * @param session
     * @return
     */
    public static User getCurrentUser(HttpSession session){
    	return (User)session.getAttribute(Constant.CURRENT_USER);
    }
    
}

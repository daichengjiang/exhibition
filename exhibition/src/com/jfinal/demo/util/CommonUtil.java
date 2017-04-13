package com.jfinal.demo.util;

import java.security.MessageDigest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.common.Constant;
import com.jfinal.demo.model.User;

public class CommonUtil {
	
	/**
	* 获取IP地址<br>
	* 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。<br>
	* 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	* 如果还不存在则调用Request .getRemoteAddr()。
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
			// 多次反向代理后会有多个IP值，第一个为真实IP。
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
     * 根据算法对字符串加密
     * @param 加密的字符串
     * @param 算法
     * @return 加密后的字符串
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
     * 获取session中在线用户
     * @param session
     * @return
     */
    public static User getCurrentUser(HttpSession session){
    	return (User)session.getAttribute(Constant.CURRENT_USER);
    }
    
}

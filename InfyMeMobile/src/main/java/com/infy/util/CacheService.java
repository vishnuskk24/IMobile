package com.infy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheService {

	 @Autowired
	 @Qualifier(value = "cacheManager1")
	    private CacheManager cacheManager;

	    public void checkCache(String email) {
	        Cache cache = cacheManager.getCache("userOtpCache");
	        if (cache != null) {
	            System.out.println("Cache value for " + email + ": " + cache.get(email));
	        }
	    }
	    public String getOTP(String mail) {
	    	String otp="";
	    	Cache cache = cacheManager.getCache("userOtpCache");
	    	  if (cache != null) {
//		            System.out.println("Cache value for " + mail + ": " + cache.get(mail)+ "  ++ "+ cache.get(mail).get());
	    		  if(cache.get(mail)!=null) {
	    			  otp =(String)cache.get(mail) .get();
	    		  }
		           
		        }
	    	  return otp;
	    }
}

package com.infy.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import com.infy.exception.IMobileException;

@Component

public class OTPGeneratorService {
	
	@Autowired
	CustomMailService customMailService;
	@Autowired
	CustomCacheManager cacheManager;
	
	@Autowired
	CacheService cacheService;


//	*
	public boolean validateOTP(String email, String actualOTP) {

		String storedOTP = cacheService.getOTP(email);
		cacheManager.removeOtpInCache(email);
		
		System.out.println(storedOTP +" " + actualOTP);

		return storedOTP.equals(actualOTP);

	}
//*
	public String generateAndStoreOTPForMail(String mail) {

		String otp = generateOTP();

		
		cacheManager.storeOTPInCache(mail, otp);
		customMailService.sendOTPInMail(mail, otp);
		System.out.println(getCache(mail)+ "< --- otp from cache");
		
		return "OTP_Sent_Success";

	}

	public String getCache(String mail) {

		cacheService.checkCache(mail);

//		removeOtpInCache(mail);
//		customMailService.sendOTPInMail(mail, otp);
		return " ";

	}
	
//*
	public String generateOTP() {
		Random rand = new Random();

		int otp = 1000 + rand.nextInt(7000);

		return otp + "";

	}

}

/*
 * 
 * 
 * //public static boolean GenerateOTP() { // // // return true; //}
 * 
 * 
 * //public void printAllCacheValues(String cacheName) { // Cache cache =
 * cacheManager.getCache(cacheName); // if (cache != null) { //
 * ConcurrentMap<Object, Object> nativeCache = (ConcurrentMap<Object, Object>)
 * cache.getNativeCache(); // nativeCache.forEach((key, value) ->
 * System.out.println(key + " = " + value)); // } else { //
 * System.out.println("Cache with name " + cacheName + " does not exist."); // }
 * //} //public String validateOTP(String email,String otp) throws
 * InfyMeMobileException { // // // printAllCacheValues("otpCache"); // //
 * String actualotp = getOTP(email); // if(!actualotp.equals(otp)) throw new
 * InfyMeMobileException("OTP is not matched, try again"); // return "Success";
 * //}
 * 
 * 
 * //@Autowired //private final CacheManager cacheManager; // //public
 * OTPGenerator(CacheManager cacheManager) { // this.cacheManager =
 * cacheManager; //}
 * 
 * //@Cacheable("otpCache") //public String generateOTP(String email) { //
 * StringBuilder otp = new StringBuilder(OTP_LENGTH); // for (int i = 0; i <
 * OTP_LENGTH; i++) { //
 * otp.append(DIGITS.charAt(random.nextInt(DIGITS.length()))); // } //
 * System.out.println(cacheManager + " < ----  cache manager "); //
 * cacheManager.getCache("otpCache").put(email, otp); // // Cache.ValueWrapper
 * valueWrapper = cacheManager.getCache("otpCache").get(email); //
 * System.out.println("++++++++++++++++++++++++++++++"); // System.out.println(
 * " otp from cache  - > " + valueWrapper.get().toString()); //
 * System.out.println("++++++++++++++++++++++++++++++"); //
 * System.out.println(otp + " << --  otp"); // return otp.toString(); //}
 * 
 * //@Cacheable("otpCache") //public String getOTP(String email) { //
 * Cache.ValueWrapper valueWrapper =
 * cacheManager.getCache("otpCache").get(email); //
 * System.out.println("otp - > " + valueWrapper.get()); // return valueWrapper
 * != null ? valueWrapper.get().toString() : null; //}
 */
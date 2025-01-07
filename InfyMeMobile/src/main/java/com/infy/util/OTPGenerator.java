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

import com.infy.exception.InfyMeMobileException;

@Component
@EnableCaching
public class OTPGenerator {

	private static final int OTP_LENGTH = 6;
	private static final String DIGITS = "0123456789";
	private static final SecureRandom random = new SecureRandom();

	public boolean validateOTP(String email, String actualOTP) {

		String storedOTP = getOTPForMail(email);

		return storedOTP.equals(actualOTP);

	}

	public String generateAndStoreOTPForMail(String mail) {

		String otp = generateOTP();

		storeOTPInCache(mail, otp);
		return otp;

	}

	public String getOTPForMail(String mail) {

		String otp = getOtpInCache(mail);

		removeOtpInCache(mail);
		return otp;

	}

	public String generateOTP() {
		Random rand = new Random();

		int otp = 1000 + rand.nextInt(7000);

		return otp + "";

	}

//     ===================caching methods

	@Cacheable(value = "userOtpCache", key = "#email")
	public String getOtpInCache(String email) {
		// This will not execute if the key is found in the cache
		return "OTP not available for email: " + email;
	}

	@CachePut(value = "userOtpCache", key = "#email")
	public String storeOTPInCache(String email, String otp) {
		return otp; // This value is stored in the cache
	}

	@CacheEvict(value = "userOtpCache", key = "#email")
	public void removeOtpInCache(String email) {
		System.out.println("Removed OTP for email: " + email);
	}

	@CacheEvict(value = "userOtpCache", allEntries = true)
	public void clearAll() {
		System.out.println("All cache entries cleared.");
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
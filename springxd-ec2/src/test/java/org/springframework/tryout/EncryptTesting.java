package org.springframework.tryout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.perf4j.StopWatch;
import org.perf4j.aop.Profiled;
import org.springframework.context.annotation.Profile;

public class EncryptTesting {

	public static void main(String[] args) {

		String password = "1111444411114444";
		String salt = "NaCl";
		String hash = password.concat(salt);

		StopWatch stopWatch= new StopWatch("hashCreate");
		hash = encryptString(hash, 99);
		stopWatch.stop();
		long timeTaken = stopWatch.getElapsedTime();
		timeTaken = 31000;
		System.out.println("Hash took " + timeTaken + " milliseconds");
		System.out.println("Time to hash all card combinations is " + (((((9999999999999999L / (9999*2)) * timeTaken)/1000) * 50)/(3600*24)) + " days");
		
	}

	private static String encryptString(String hash, int numberOfTimes) {
		for (int i = 0; i < numberOfTimes; i++) {
			hash = encryptString(hash);
			//System.out.println("Value of " + i + "th" + "hash is :" + hash);
		}
		return hash;
	}

	private static String encryptString(String hash) {
		MessageDigest digest = null;
		byte[] hashBytes = hash.getBytes();
		try {
			digest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 5000; i++) {
			hashBytes = digest.digest(hashBytes);
		}
		hash = new String(hashBytes);
		return hash;
	}

}

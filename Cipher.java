import java.util.Random;

public class Cipher {

	static int modulo = 128;

	/**
	 * @param x, the string to encrypt
	 * @param key, the key to encrypt with
	 * @return the encrypted string
	 */
	public long[] encrypt(String x, Long long1) {
		char[] EncryptedX = x.toCharArray();
		long[] EncryptedText = new long[EncryptedX.length];
		for (int i = 0; i < x.length(); i++) {
			EncryptedX[i] = (char) ((EncryptedX[i] + long1) % Cipher.modulo);
			EncryptedText[i] = (long) EncryptedX[i];
		}
		return EncryptedText;
	}

	/**
	 * @param y, the string to decrypt
	 * @param key, the key to decrypt with
	 * @return the decrypted string
	 */
	public String decrypt(long[] y, Long key) {
		char[] DecryptedX = new char[y.length];
		String dex;
		int add = 0;
		for (int i = 0; i < y.length; i++) {
			if (y[i] - key < 0)
				add = Cipher.modulo;
			DecryptedX[i] = (char) (((y[i] - key) % Cipher.modulo) + add);
			add = 0;
		}
		dex = String.valueOf(DecryptedX);
		return dex;
	}

	/**
	 * @param x, the string to encrypt
	 * @param key_a, the multiplication key to encrypt with
	 * @param key_b, the addition key to encrypt with
	 * @return the encrypted string
	 */
	public long[] encrypt(String x, Long key_a, Long key_b) {
		char[] EncryptedX = x.toCharArray();
		long[] EncryptedText = new long[EncryptedX.length];
		for (int i = 0; i < x.length(); i++) {
			EncryptedX[i] = (char) (((EncryptedX[i] * key_a) + key_b) % Cipher.modulo);
			EncryptedText[i] = (long) EncryptedX[i];
		}
		return EncryptedText;
	}

	/**
	 * @param y, the string to decrypt
	 * @param key_a, the multiplication key to decrypt with
	 * @param key_b, the addition key to decrypt with
	 * @return the decrypted string
	 */
	public String decrypt(long[] y, Long key_a, Long key_b) {
		char[] DecryptedX = new char[y.length];
		String dnx;
		int inversekey_a;
		int add = 0;
		inversekey_a = Inversekey_a(key_a, Cipher.modulo); 
		for (int i = 0; i < y.length; i++) {
			if (y[i] - key_b < 0) 
				add = Cipher.modulo;
			DecryptedX[i] = (char) ((((y[i] - key_b) * inversekey_a) % Cipher.modulo) + add);
			add = 0;
		}
		dnx = String.valueOf(DecryptedX);
		return dnx;
	}
	
	/**
	 * @param message, the string to encrypt
	 * @param key, the two keys (base and power) to encrypt with
	 * @return the encrypted string as a list of numbers
	 */
	public long[] encrypt(String message, long[] key) {
		long[] Encrypted = new long[message.length()];
		for (int i = 0; i < message.length(); i++) 
			Encrypted[i] = (long)(FastEx(message.charAt(i), key[0], key[1]));
		return Encrypted;
	}
	
	/**
	 * @param message, the string as a list of numbers to decrypt
	 * @param key, the two keys (base and power) to decrypt with
	 * @return the decrypted string
	 */
	public String decrypt(long[] message, long[] key) {
		char[] Decrypted = new char[message.length];
		for (int i = 0; i < message.length; i++) 
			Decrypted[i] = (char) (FastEx(message[i], key[0], key[1]));
		String decrypted = String.valueOf(Decrypted);
		return decrypted;
	}
	
	/**
	 * @return keys, to use in the RSA encryption and decryption
	 */
	public long[] generateKeys() {
		Random random = new Random();
		long p = givePrime((long)random.nextInt(10000) + 1);
		long q = givePrime((long)random.nextInt(10000) + 1);
		while (q == p)
			q = givePrime(random.nextInt(100));
		long n = p * q;
		long phi = (p-1)*(q-1);
		long start = phi;
		long Candidates = findCandidate(phi, start);
		long[] Exponents = findFactors(Candidates, phi);
		while (Exponents[0] == 0 || Exponents[1] == 0) {
			start += phi;
			Candidates = findCandidate(phi, start);
			Exponents = findFactors(Candidates, phi);
		}
		long ex = Exponents[0];
		long d = Exponents[1];
		long[] Keys = {ex, d, n};
		return Keys;
	}
	
	/**
	 * @param a, a seed to start searching for a prime
	 * @return prime, the next prime number
	 */
	private long givePrime(long a) {
		long prime = Math.abs(a) + 1;
		while (true) { 
			if (checkPrime(prime) == true)
				return prime;
			else
				prime ++;
		}
	}

	/**
	 * @param num, a double check of the state of prime
	 * @return boolean, whether the num is prime or not
	 */
	private boolean checkPrime(long num) {
		if (num == 2)
			return true;
		for (int i = 2; i <= Math.sqrt(num); i++) {
	        if (num % i == 0)
	            return false;
	    }
	    return true;
	}
	
	/**
	 * @param phi_n
	 * @param start
	 * @return
	 */
	private long findCandidate(long phi_n, long start) {
		long candidate = start;
		if (candidate % phi_n != 1);
			candidate += 1;
		return candidate;
	}
	
	/**
	 * @param candidate
	 * @param phi
	 * @return
	 */
	private long[] findFactors(long candidate, long phi) {
		long[] exponents = new long[2];
		for (long i = 2; i <= Math.sqrt(candidate); i++) {
			if (candidate % i == 0) {
				if (GCD(phi, i) == 1 & GCD(phi, candidate/i) == 1) { 
					exponents[0] = i;
					exponents[1] = candidate/i;
				}
				else
					continue;
			}
		}
		return exponents;
	}
	
	/**
	 * @param a, a number
	 * @param b, another number
	 * @return the greatest common divisor
	 */
	private long GCD(long a, long b) {
		while (b != 0) {
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a; 
	}
	
	/**
	 * @param num, a number
	 * @return the binary string representation of the number
	 */
	private String toBinary(long num) {
		if (num == 0) 
			return "0";
		String binary = "";
		long remainder;
		while (num > 0) {
			remainder = num % 2;
			binary = remainder + binary;
			num = num / 2;
		}
		return binary;
	}
	
	/**
	 * @param base
	 * @param ex
	 * @param mod
	 * @return the value of doing an exponent to deal with large values
	 */
	private long FastEx(long base, long ex, long mod) {
		String binary = toBinary(ex);
		long mainBase = base;
		for (int i = 0; i < binary.length()-1; i++) { 
			if (binary.charAt(i+1) == '0') {
				base = (base * base) % mod;
			}
			else if (binary.charAt(i+1) == '1') {
				base = (base * base) % mod;
				base = (base * mainBase) % mod;
			}
		}
		return base;
	}
	
	/**
	 * @param key_a
	 * @param mod
	 * @return the inverse of key_a
	 */
	private int Inversekey_a(Long key_a, int mod) {
		int inversekey_a = 0; 
		while (((key_a * inversekey_a) % mod) != 1 && inversekey_a < Cipher.modulo) 
			inversekey_a += 1; 
		return inversekey_a;
	}

	/**
	 * @param key_a
	 * @param mod
	 * @return boolean, on whether a key is valid to encrypt and decrypt with
	 */
	public boolean ValidKey(Long key_a, int mod) { 
		if (GCD(key_a, mod) == 1 && Inversekey_a(key_a, mod) != 0) 
			return true;
		else
			return false;
	}
}
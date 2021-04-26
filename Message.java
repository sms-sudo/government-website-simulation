/*
 * Salwa Abdalla 
 * ICS3U Culminating Assignment: 1/21/2019
 * Jeff Radulovic
 * 
 * The messages class is an object template to hold the personal messages
 * between the users (and it will be stored inside the Employee instance
 * as each message will have this information/characteristics
 * 
 * Includes the constructor as well as getters and setters
 * 
 */

//importing the necessary library
import java.util.ArrayList;

public class Message {

	//creating the characteristics for each message
	private String RecipientEmployeeNum;
	private String SenderEmployeeNum;
	private int LevelOfEncryption;
	private ArrayList<Long> Message = new ArrayList<>();
	private ArrayList<Long> encryption_key = new ArrayList<>();
	private String decryptedMessage;
	
	/**
	 * @param RecipientEmployeeNum
	 * @param SenderEmployeeNum
	 * @param LevelOfEncryption
	 * @param encryptedMessage
	 */
	public Message(String RecipientEmployeeNum, String SenderEmployeeNum, int LevelOfEncryption, long[] encryptedMessage, ArrayList<Long> encryption_key) {
		this.RecipientEmployeeNum = RecipientEmployeeNum;
		this.SenderEmployeeNum = SenderEmployeeNum;
		this.LevelOfEncryption = LevelOfEncryption;
		for (int i = 0; i < encryptedMessage.length; i++)
			this.Message.add(encryptedMessage[i]);
		this.encryption_key = encryption_key;
		
		Cipher cipher = new Cipher();
		
		if (LevelOfEncryption == 1) {
			// using level one decryption from the cipher class
			decryptedMessage = cipher.decrypt(encryptedMessage, encryption_key.get(0));
		}

		else if (LevelOfEncryption == 2) {
			// using level two decryption from the cipher class
			decryptedMessage = cipher.decrypt(encryptedMessage,	encryption_key.get(0), encryption_key.get(1));
		}

		else {
			// using level three decryption from the cipher class
			long[] PrivateKey = {encryption_key.get(1), encryption_key.get(2)};
			decryptedMessage = cipher.decrypt(encryptedMessage, PrivateKey);
		}
		
	}
	
	/**
	 * @return the decryptedMessage
	 */
	public String getDecryptedMessage() {
		return decryptedMessage;
	}

	/**
	 * @return the recipientEmployeeNum
	 */
	public String getRecipientEmployeeNum() {
		return RecipientEmployeeNum;
	}

	/**
	 * @return the encryption_key
	 */
	public ArrayList<Long> getEncryption_key() {
		return encryption_key;
	}

	/**
	 * @return the senderEmployeeNum
	 */
	public String getSenderEmployeeNum() {
		return SenderEmployeeNum;
	}

	/**
	 * @return the levelOfEncryption
	 */
	public int getLevelOfEncryption() {
		return LevelOfEncryption;
	}
	
	/**
	 * @return the Message in the form of an ArrayList of Longs
	 */
	public ArrayList<Long> getMessageEncrypted() {
		return Message;
	}

	/**
	 * @param levelOfEncryption the levelOfEncryption to set
	 */
	public void setLevelOfEncryption(int levelOfEncryption) {
		LevelOfEncryption = levelOfEncryption;
	}
	
	/**
	 * @return specific format of the Messages to be printed
	 */
	public String toString() {
		String result = "Sender Number: " + SenderEmployeeNum;
		result += "\nReciever Number: " + RecipientEmployeeNum;
		result += "\nLevel Of Encryption: " + LevelOfEncryption;
		result += "\nMessage " + Message;
		result += "\nEncryption Key " + encryption_key;
		return result;
		
	}

}

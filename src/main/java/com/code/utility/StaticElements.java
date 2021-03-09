package com.code.utility;

public class StaticElements {
	public static int UNKNOWN_ERROR = -3;
	public static int FILE_ERROR = -2;
	public static int NO_PATIENT_WITH_ID = -1;
	public static int ACCESS_DENIED = 0;
	public static int ACCESS_PERMIT = 1;
	
	public static String TEMP_FOLDER_PATH = "temp/";
	public static String pubfile = TEMP_FOLDER_PATH + "pub_key";
	public static String mskfile = TEMP_FOLDER_PATH + "master_key";
	public static String prvfile = TEMP_FOLDER_PATH + "prv_key";
	public static String inputfile = TEMP_FOLDER_PATH + "transmit.json";
	public static String encfile = TEMP_FOLDER_PATH + "transmit.json.cpabe";
	public static String decfile = TEMP_FOLDER_PATH + "PHRFile.json";
	
	public static String Doctormail = "johndoe@gmail.com";
	public static String Patientmail = "";
}

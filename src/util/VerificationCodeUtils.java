package util;

public class VerificationCodeUtils {
	
	public static String generateCode(){
		return "DFGCG-DGJTH-KSJDN-DISKJ-POIMN";
	}
	
	public static boolean verifyCode(String code){
		return code.equals("DFGCG-DGJTH-KSJDN-DISKJ-POIMN");
	}
	

}

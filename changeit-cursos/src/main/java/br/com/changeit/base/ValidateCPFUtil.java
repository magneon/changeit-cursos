package br.com.changeit.base;
import java.util.ArrayList;
import java.util.List;

public final class ValidateCPFUtil {
	
	/**
	 * Validate cpf. This method is responsible to receive the String and verify if its a CPF valid.
	 * The String {@value}, must be a String containing numbers to calculate the first and second digits of CPF.
	 *
	 * @param toValidate The CPF to validate
	 * @return true, if the CPF is valid
	 */
	public static final boolean validateCPF(String toValidate) {
		
		toValidate = toValidate.replace(".", "").replace("-", "");
		
		if (areAllNumbers(toValidate)) {
		
			List<Integer> cpf = transformStringToList(toValidate);
	
			digitGenerate(cpf, calculateDigitsFromCPF(0, cpf, 10, 0));
			digitGenerate(cpf, calculateDigitsFromCPF(0, cpf, 11, 0));
	
			String cpfCalculado = "";
	
			for (Integer digitoCpf : cpf) {
				cpfCalculado += digitoCpf.toString();
			}
	
			return cpfCalculado.equals(toValidate);
		} else {
			return false;
		}
		
	}

	/**
	 * Are all numbers. Verify if the String passed from validateCPF({@value}) is only numbers.
	 *
	 * @param toValidate String containing the target CPF to validate
	 * @return true, if the CPF containing only numbers.
	 */
	private static final boolean areAllNumbers(final String toValidate) {
		
		boolean isValido = true;
		
		if (toValidate.matches("0{11}") || toValidate.matches("1{11}") ||
				toValidate.matches("2{11}") || toValidate.matches("3{11}") ||
				toValidate.matches("4{11}") || toValidate.matches("5{11}") ||
				toValidate.matches("6{11}") || toValidate.matches("7{11}") ||
				toValidate.matches("8{11}") || toValidate.matches("9{11}") ||
				!toValidate.matches("\\d{11}")) {
			isValido = false;
		}
		
		return isValido;
		
	}

	/**
	 * Digit generate.
	 * 
	 * This method is responsible to receive the result and find the respective digit to add in the cpf validate.
	 *
	 * @param cpf The list with all digits of CPF in Integer numbers
	 * @param result The result of the multiplier of all numbers from CPF.
	 */
	private static final void digitGenerate(final List<Integer> cpf, final Integer result) {
		
		Integer digit;
		
		if (result % 11 < 2) {
			digit = 0;
		} else {
			digit = 11 - (result % 11);
		}
		
		cpf.add(digit);
		
	}
	
	/**
	 * Transform string to list.
	 *
	 * @param toValidate The CPF received in String and converted to Integer and put in a list.
	 * @return The list with all digits from CPF in list.
	 */
	private static final List<Integer> transformStringToList(final String toValidate) {
		List<Integer> cpf = new ArrayList<Integer>();
		
		String numerosCpf = toValidate.substring(0, 10);

		for (int i = 0; i < numerosCpf.length() - 1; i++) {
			cpf.add(Integer.parseInt(numerosCpf.charAt(i) + ""));
		}
		
		return cpf;
	}

	/**
	 * Calculate digits from cpf.
	 * 
	 * This method is the most important of this class. The goal of this, is recursively get each digit from CPF and calculate your result with the respective multipler.
	 * For example, the CPF 00011122233 have the respective multipliers:
	 * 
	 *  0	0	0	1	1	1	2	2	2
	 *  10	9	8	7	6	5	4	3	2
	 *  
	 * Because the numbers 33 are yours checkers digits.
	 *
	 * @param controller The index controller of List
	 * @param cpfInList The CPF transformed in list.
	 * @param multiplier The respective multiplier of the index controller
	 * @param result The result of the calculation
	 * @return The final result
	 */
	private static final Integer calculateDigitsFromCPF(Integer controller, final List<Integer> cpfInList, Integer multiplier, Integer result) {
		if (multiplier >= 2) {
			result += cpfInList.get(controller) * multiplier;
			controller++;
			multiplier--;
			return calculateDigitsFromCPF(controller, cpfInList, multiplier, result);
		} else {
			return result;
		}
	}
	
}

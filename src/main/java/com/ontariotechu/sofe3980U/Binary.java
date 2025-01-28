package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary {
	private String number = "0"; // string containing the binary value '0' or '1'

	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should contain only zeros or
	 *               ones with any length and order. otherwise, the value of "0"
	 *               will be stored. Trailing zeros will be excluded and empty
	 *               string will be considered as zero.
	 */
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}

		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}

		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		// uncomment the following code

		if (this.number.isEmpty()) { // replace empty strings with a single zero
			this.number = "0";
		}

	}

	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue() {
		return this.number;
	}

	/**
	 * Adding two binary variables. For more information, visit
	 * <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers
	 * </a>.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1, Binary num2) {
		// the index of the first digit of each number
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		// initial variable
		int carry = 0;
		String num3 = ""; // the binary value of the sum
		while (ind1 >= 0 || ind2 >= 0 || carry != 0) // loop until all digits are processed
		{
			int sum = carry; // previous carry
			if (ind1 >= 0) { // if num1 has a digit to add
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if (ind2 >= 0) { // if num2 has a digit to add
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind2--; // update ind2
			}
			carry = sum / 2; // the new carry
			sum = sum % 2; // the resultant digit
			num3 = ((sum == 0) ? "0" : "1") + num3; // convert sum to string and append it to num3
		}
		Binary result = new Binary(num3); // create a binary object with the calculated value.
		return result;

	}

	/**
	 * Performs an OR gate operation upon two binary numbers represented as
	 * `Binary` objects and returns the result.
	 *
	 * @param num1 The first binary number as a `Binary` object.
	 * @param num2 The second binary number as a `Binary` object.
	 * @return The OR-gate result of `num1` and `num2` as a `Binary` object.
	 */
	public static Binary or(Binary num1, Binary num2) {
		// Determine the longer and shorter strings
		String longer = num1.number.length() >= num2.number.length() ? num1.number : num2.number;
		String shorter = num1.number.length() < num2.number.length() ? num1.number : num2.number;

		// Create a StringBuilder to build the result
		StringBuilder result = new StringBuilder(longer);

		// Indices to match digits from the end of both strings
		int longerIndex = longer.length() - 1;
		int shorterIndex = shorter.length() - 1;

		// Perform OR operation on overlapping positions
		while (shorterIndex >= 0) {
			// OR the characters and store in result
			char longerChar = longer.charAt(longerIndex);
			char shorterChar = shorter.charAt(shorterIndex);
			char orResult = (longerChar == '1' || shorterChar == '1') ? '1' : '0';

			// Update the result, keeping non-overlapping positions as is
			result.setCharAt(longerIndex, orResult);

			// Move indices
			longerIndex--;
			shorterIndex--;
		}

		// The remaining characters in the longer string are untouched
		// Return the result as a Binary object (will perform leading zero-stripping)
		return new Binary(result.toString());
	}

	/**
	 * Performs an AND gate operation upon two binary numbers represented as
	 * `Binary` objects
	 * and returns the result.
	 *
	 * @param num1 The first binary number as a `Binary` object.
	 * @param num2 The second binary number as a `Binary` object.
	 * @return The AND-gate result of `num1` and `num2` as a `Binary` object.
	 */
	public static Binary and(Binary num1, Binary num2) {
		// Determine the longer and shorter strings
		String longer = num1.number.length() >= num2.number.length() ? num1.number : num2.number;
		String shorter = num1.number.length() < num2.number.length() ? num1.number : num2.number;

		// Create a StringBuilder to build the result
		StringBuilder result = new StringBuilder(shorter);

		// Indices to match digits from the end of both strings
		int longerIndex = longer.length() - 1;
		int shorterIndex = shorter.length() - 1;

		// Perform AND operation on overlapping positions, discarding the rest
		while (shorterIndex >= 0) {
			// AND the characters and store in result
			char longerChar = longer.charAt(longerIndex);
			char shorterChar = shorter.charAt(shorterIndex);
			char andResult = (longerChar == '1' && shorterChar == '1') ? '1' : '0';

			// Update the result
			result.setCharAt(shorterIndex, andResult);

			// Move indices
			longerIndex--;
			shorterIndex--;
		}

		// The remaining characters in the longer string are untouched
		// Return the result as a Binary object (will perform leading zero-stripping)
		return new Binary(result.toString());
	}

	/**
	 * Multiplies two binary numbers represented as `Binary` objects and returns the
	 * result.
	 *
	 * @param num1 The first binary number as a `Binary` object.
	 * @param num2 The second binary number as a `Binary` object.
	 * @return The product of `num1` and `num2` as a `Binary` object.
	 */
	public static Binary mul(Binary num1, Binary num2) {
		// Ensure the shorter binary number is used as the multiplier for efficiency.
		if (num1.getValue().length() < num2.getValue().length()) {
			Binary temp = num1;
			num1 = num2;
			num2 = temp;
		}
		// Convert the multiplier (num2) to a character array for bitwise processing.
		char[] digits = num2.getValue().toCharArray();

		// The multiplicand (num1) as a string; this will be left-shifted as needed.
		String multiplier = num1.getValue();

		// Initialize the solution to "0" as the starting point.
		Binary soln = new Binary("0");

		// Position of the current bit being processed in the multiplier.
		int pos = digits.length - 1;

		// Iterate through the bits of the multiplier from the least significant to the
		// most significant.
		for (int i = pos; i > -1; i--) {
			// If the current bit is '1', add the appropriately shifted multiplicand to the
			// solution.
			if (digits[i] == '1') {
				// Create a binary number with the multiplicand shifted left by (pos - i)
				// positions.
				soln = Binary.add(soln, new Binary(multiplier + "0".repeat(pos - i)));
			}
		}
		// Return the final computed binary product.
		return soln;
	}
}

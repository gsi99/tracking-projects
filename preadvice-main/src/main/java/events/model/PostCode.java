package events.model;

public class PostCode {
	String postcode;
	// this one has error in the regex.
	// static String pattern = "^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2}) | (([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2}) | (([AZa-z][0-9][A-Za-z]) | ([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))) [0-9][A- Za-z]{2})$"; 
	 static String pattern = "^(([gG][iI][rR] {0,}0[aA]{2})|((([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y]?[0-9][0-9]?)|(([a-pr-uwyzA-PR-UWYZ][0-9][a-hjkstuwA-HJKSTUW])|([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y][0-9][abehmnprv-yABEHMNPRV-Y]))) {0,}[0-9][abd-hjlnp-uw-zABD-HJLNP-UW-Z]{2}))$";
	// static String pattern = "^([Gg][Ii][Rr] 0[Aa]{2})$"; 

	PostCode(String postCode) {
		if (postCode != null) {
			this.postcode = postCode.replaceAll("\\s","").toUpperCase();
		}
	}
	
	public static boolean validatePostCode(String value) {
		return value.matches(pattern);
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
}

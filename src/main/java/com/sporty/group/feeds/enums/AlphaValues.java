package com.sporty.group.feeds.enums;

public enum AlphaValues {
	HOME("1"), DRAW("X"), AWAY("2");

	private final String code;

	AlphaValues(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static AlphaValues fromValue(String value) {
		for (AlphaValues type : values()) {
			if (type.code.equals(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknown value: " + value);
	}

	public static boolean isValid(String value) {
		for (AlphaValues alphaValue : values()) {
			if (alphaValue.code.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public static String toNormalizedKey(String key) {
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}

		if (key.equals(BetaOdds.HOME.getCode())) {
			return NormalizedOdds.ONE.getCode();
		} else if (key.equals(BetaOdds.DRAW.getCode())) {
			return NormalizedOdds.X.getCode();
		} else if (key.equals(BetaOdds.AWAY.getCode())) {
			return NormalizedOdds.TWO.getCode();
		} else {
			throw new IllegalArgumentException("Unknown odds key: " + key);
		}
	}
}
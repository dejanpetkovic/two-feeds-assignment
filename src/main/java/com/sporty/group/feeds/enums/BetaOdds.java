package com.sporty.group.feeds.enums;

public enum BetaOdds {
	HOME("home"), DRAW("draw"), AWAY("away");

	private final String code;

	BetaOdds(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static BetaOdds fromValue(String value) {
		for (BetaOdds type : values()) {
			if (type.code.equalsIgnoreCase(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknown odd: " + value);
	}

	public static boolean isValid(String value) {
		for (BetaOdds betaOdd : values()) {
			if (betaOdd.code.equalsIgnoreCase(value)) {
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
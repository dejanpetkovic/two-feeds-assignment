package com.sporty.group.feeds.enums;

public enum NormalizedOdds {
	ONE("1"), X("X"), TWO("2");

	private final String code;

	NormalizedOdds(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static NormalizedOdds fromCode(String code) {
		for (NormalizedOdds o : values()) {
			if (o.code.equals(code)) {
				return o;
			}
		}
		throw new IllegalArgumentException("Unknown normalized odds code: " + code);
	}

	public static boolean isValid(String value) {
		for (NormalizedOdds normalizedOdd : values()) {
			if (normalizedOdd.code.equals(value)) {
				return true;
			}
		}
		return false;
	}
}
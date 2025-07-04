package com.sporty.group.feeds.enums;

public enum BetaMessageType {
	ODDS_CHANGE("ODDS"), BET_SETTLEMENT("SETTLEMENT");

	private final String code;

	BetaMessageType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static BetaMessageType fromValue(String value) {
		for (BetaMessageType type : values()) {
			if (type.code.equals(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknown type: " + value);
	}
}
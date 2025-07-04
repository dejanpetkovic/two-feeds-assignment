package com.sporty.group.feeds.enums;

public enum AlphaMessageType {
	ODDS_CHANGE("odds_update"), 
	BET_SETTLEMENT("settlement");

	private final String code;

	AlphaMessageType(String code) {
		this.code = code;
	}


	public String getCode() {
		return code;
	}

	public static AlphaMessageType fromValue(String value) {
		for (AlphaMessageType type : values()) {
			if (type.code.equals(value)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknown message type: " + value);
	}
}
package com.sporty.group.feeds.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BetSettlementMessage implements NormalizedMessage {

	private String id;
	private String market;
	private String provider;
	private String event;
	private String result;

}
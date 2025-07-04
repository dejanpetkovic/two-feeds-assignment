package com.sporty.group.feeds.message;

import java.math.BigDecimal;
import java.util.Map;

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
public class OddsChangeMessage implements NormalizedMessage {

	private String id;
	private String market;
	private String provider;
	private String event;
	private Map<String, BigDecimal> odds;

}
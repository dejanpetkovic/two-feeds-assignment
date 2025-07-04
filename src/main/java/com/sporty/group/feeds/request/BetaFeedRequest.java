package com.sporty.group.feeds.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BetaFeedRequest implements Serializable {

	private static final long serialVersionUID = -5985023137640973852L;

	@JsonProperty("type")
	private String type;

	@JsonProperty("event_id")
	private String event;

	private Map<String, BigDecimal> odds;

	private String result;

}
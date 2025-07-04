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
public class AlphaFeedRequest implements Serializable {

	private static final long serialVersionUID = 2505451077153758006L;

	@JsonProperty("msg_type")
	private String type;

	@JsonProperty("event_id")
	private String event;

	private Map<String, BigDecimal> values;

	private String outcome;
}

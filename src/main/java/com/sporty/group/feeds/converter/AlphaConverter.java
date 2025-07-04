package com.sporty.group.feeds.converter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.sporty.group.feeds.constants.AlphaConstants;
import com.sporty.group.feeds.enums.AlphaValues;
import com.sporty.group.feeds.message.BetSettlementMessage;
import com.sporty.group.feeds.message.OddsChangeMessage;
import com.sporty.group.feeds.request.AlphaFeedRequest;

public class AlphaConverter {

	public static OddsChangeMessage convertToOddsChangeMessage(AlphaFeedRequest request) {
		Map<String, BigDecimal> normalizedOdds = new HashMap<>();

		for (Map.Entry<String, BigDecimal> entry : request.getValues().entrySet()) {
			normalizedOdds.put(AlphaValues.toNormalizedKey(entry.getKey()), entry.getValue());
		}

		return OddsChangeMessage.builder().id(UUID.randomUUID().toString()).market(AlphaConstants.MARKET)
				.provider(AlphaConstants.PROVIDER_LABEL).event(request.getEvent()).odds(normalizedOdds).build();
	}

	public static BetSettlementMessage convertToBetSettlementMessage(AlphaFeedRequest request) {
		return BetSettlementMessage.builder().id(UUID.randomUUID().toString()).market(AlphaConstants.MARKET)
				.provider(AlphaConstants.PROVIDER_LABEL).event(request.getEvent())
				.result(AlphaValues.toNormalizedKey(request.getOutcome())).build();
	}

}
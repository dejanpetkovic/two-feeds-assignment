package com.sporty.group.feeds.converter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.sporty.group.feeds.constants.BetaConstants;
import com.sporty.group.feeds.enums.BetaOdds;
import com.sporty.group.feeds.message.BetSettlementMessage;
import com.sporty.group.feeds.message.OddsChangeMessage;
import com.sporty.group.feeds.request.BetaFeedRequest;

public class BetaConverter {

	public static OddsChangeMessage convertToOddsChangeMessage(BetaFeedRequest request) {
		Map<String, BigDecimal> normalizedOdds = new HashMap<>();

		for (Map.Entry<String, BigDecimal> entry : request.getOdds().entrySet()) {
			normalizedOdds.put(BetaOdds.toNormalizedKey(entry.getKey()), entry.getValue());
		}

		return OddsChangeMessage.builder().id(UUID.randomUUID().toString()).market(BetaConstants.MARKET)
				.provider(BetaConstants.PROVIDER_LABEL).event(request.getEvent()).odds(normalizedOdds).build();
	}

	public static BetSettlementMessage convertToBetSettlementMessage(BetaFeedRequest request) {
		return BetSettlementMessage.builder().id(UUID.randomUUID().toString()).market(BetaConstants.MARKET)
				.provider(BetaConstants.PROVIDER_LABEL).event(request.getEvent())
				.result(BetaOdds.toNormalizedKey(request.getResult())).build();
	}

}
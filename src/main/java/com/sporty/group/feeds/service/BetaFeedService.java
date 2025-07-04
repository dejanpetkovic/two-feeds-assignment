package com.sporty.group.feeds.service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sporty.group.feeds.converter.BetaConverter;
import com.sporty.group.feeds.enums.BetaMessageType;
import com.sporty.group.feeds.enums.BetaOdds;
import com.sporty.group.feeds.exception.InvalidFeedRequestDataException;
import com.sporty.group.feeds.message.NormalizedMessage;
import com.sporty.group.feeds.request.BetaFeedRequest;

@Service
public class BetaFeedService extends AbstractFeedService<BetaFeedRequest> {

	public BetaFeedService(MessageQueueService messageQueueService) {
		super(messageQueueService);
	}

	@Override
	protected void validate(BetaFeedRequest request) {
		if (request == null) {
			throw new InvalidFeedRequestDataException("Request body is missing");
		}

		if (request.getType() == null || request.getType().trim().isEmpty()) {
			throw new InvalidFeedRequestDataException("Missing required field: type");
		}

		if (request.getEvent() == null || request.getEvent().trim().isEmpty()) {
			throw new InvalidFeedRequestDataException("Missing required field: event_id");
		}

		BetaMessageType type = BetaMessageType.fromValue(request.getType());

		switch (type) {
		case ODDS_CHANGE -> {
			if (request.getOdds() == null || request.getOdds().isEmpty()) {
				throw new InvalidFeedRequestDataException("Missing or empty required field: odds");
			}

			Set<String> actualOdds = request.getOdds().keySet();
			Set<String> allowedOdds = Arrays.stream(BetaOdds.values()).map(BetaOdds::getCode)
					.collect(Collectors.toSet());

			if (!actualOdds.equals(allowedOdds)) {
				throw new InvalidFeedRequestDataException(
						"Expected odds keys: " + allowedOdds + ", but got: " + actualOdds);
			}

			// We should consider supporting partial updates where providers
			// can send only changed odds instead of the full set to reduce data transfer
			// from their side, memory consumption from our side,
			// odd initialize should
			// have full sets
		}

		case BET_SETTLEMENT -> {
			if (request.getResult() == null || request.getResult().trim().isEmpty()) {
				throw new InvalidFeedRequestDataException("Missing required field: result");
			}

			if (!BetaOdds.isValid(request.getResult())) {
				throw new InvalidFeedRequestDataException("Invalid result: " + request.getResult());
			}
		}

		default -> throw new InvalidFeedRequestDataException("Unknown type: " + request.getType());
		}
	}

	@Override
	protected NormalizedMessage convert(BetaFeedRequest request) {
		BetaMessageType type = BetaMessageType.fromValue(request.getType());

		switch (type) {
		case ODDS_CHANGE -> {
			return BetaConverter.convertToOddsChangeMessage(request);
		}

		case BET_SETTLEMENT -> {
			return BetaConverter.convertToBetSettlementMessage(request);
		}

		default -> throw new IllegalArgumentException("Unknown type: " + request.getType());
		}
	}
}

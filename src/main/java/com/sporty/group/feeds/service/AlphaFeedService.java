package com.sporty.group.feeds.service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sporty.group.feeds.converter.AlphaConverter;
import com.sporty.group.feeds.enums.AlphaMessageType;
import com.sporty.group.feeds.enums.AlphaValues;
import com.sporty.group.feeds.exception.InvalidFeedRequestDataException;
import com.sporty.group.feeds.message.NormalizedMessage;
import com.sporty.group.feeds.request.AlphaFeedRequest;

@Service
public class AlphaFeedService extends AbstractFeedService<AlphaFeedRequest> {

	public AlphaFeedService(MessageQueueService messageQueueService) {
		super(messageQueueService);
	}

	@Override
	protected void validate(AlphaFeedRequest request) {
		if (request == null) {
			throw new InvalidFeedRequestDataException("Request body is missing");
		}

		if (request.getType() == null || request.getType().trim().isEmpty()) {
			throw new InvalidFeedRequestDataException("Missing required field: msg_type");
		}

		if (request.getEvent() == null || request.getEvent().trim().isEmpty()) {
			throw new InvalidFeedRequestDataException("Missing required field: event_id");
		}

		AlphaMessageType type = AlphaMessageType.fromValue(request.getType());

		switch (type) {
		case ODDS_CHANGE -> {
			if (request.getValues() == null || request.getValues().isEmpty()) {
				throw new InvalidFeedRequestDataException("Missing or empty required field: values");
			}

			Set<String> actualValues = request.getValues().keySet();
			Set<String> allowedValues = Arrays.stream(AlphaValues.values()).map(AlphaValues::getCode)
					.collect(Collectors.toSet());

			if (!actualValues.equals(allowedValues)) {
				throw new InvalidFeedRequestDataException(
						"Expected odds keys: " + allowedValues + ", but got: " + actualValues);
			}

			// We should consider supporting partial updates where providers
			// can send only changed odds instead of the full set to reduce data transfer
			// from their side, memory consumption from our side,
			// odd initialize should
			// have full sets
		}

		case BET_SETTLEMENT -> {
			if (request.getOutcome() == null || request.getOutcome().trim().isEmpty()) {
				throw new InvalidFeedRequestDataException("Missing required field: outcome");
			}

			if (!AlphaValues.isValid(request.getOutcome())) {
				throw new InvalidFeedRequestDataException("Invalid outcome: " + request.getOutcome());
			}
		}

		default -> throw new InvalidFeedRequestDataException("Unknown msg_type: " + request.getType());
		}
	}

	@Override
	protected NormalizedMessage convert(AlphaFeedRequest request) {
		AlphaMessageType type = AlphaMessageType.fromValue(request.getType());

		switch (type) {
		case ODDS_CHANGE -> {
			return AlphaConverter.convertToOddsChangeMessage(request);
		}

		case BET_SETTLEMENT -> {
			return AlphaConverter.convertToBetSettlementMessage(request);
		}

		default -> throw new IllegalArgumentException("Unknown msg_type: " + request.getType());
		}
	}
}

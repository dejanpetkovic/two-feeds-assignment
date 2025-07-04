package com.sporty.group.feeds.service;

import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.sporty.group.feeds.message.BetSettlementMessage;
import com.sporty.group.feeds.message.NormalizedMessage;
import com.sporty.group.feeds.message.OddsChangeMessage;

@Service
public class MessageQueueService {

	private static final Logger logger = LoggerFactory.getLogger(MessageQueueService.class);

	private final Set<String> hashTable = ConcurrentHashMap.newKeySet();
	private final ConcurrentLinkedQueue<NormalizedMessage> queue = new ConcurrentLinkedQueue<>();

	public void queue(NormalizedMessage message) {
		String key = generateMessageHash(message);

		if (hashTable.add(key)) {
			queue.add(message);
			logger.info("Message queued {}", message);
		} else {
			// This can happen if the provider sends the same message multiple times
			// concurrently,
			// or if they did not receive a response to a previous request and retry
			// sending.
			// To avoid processing duplicates, we simply log a warning and return OK,
			// preventing processing of same messages.
			logger.warn("Duplicate message {}", message);
		}
	}

	private String generateMessageHash(NormalizedMessage message) {
		String result = null;
		if (message instanceof OddsChangeMessage oddsMsg) {
			result = "odds:" + oddsMsg.getProvider() + ":" + oddsMsg.getEvent() + ":" + oddsMsg.getMarket() + ":"
					+ oddsMsg.getOdds();
		} else if (message instanceof BetSettlementMessage betMsg) {
			result = "settlement:" + betMsg.getProvider() + ":" + betMsg.getEvent() + ":" + betMsg.getMarket() + ":"
					+ betMsg.getResult();
		} else {
			throw new IllegalArgumentException("Unknown message type");
		}

		return DigestUtils.md5DigestAsHex(result.getBytes(StandardCharsets.UTF_8));
	}
}
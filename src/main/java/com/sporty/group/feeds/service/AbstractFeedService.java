package com.sporty.group.feeds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.sporty.group.feeds.message.NormalizedMessage;

public abstract class AbstractFeedService<T> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractFeedService.class);

	protected final MessageQueueService messageQueueService;

	public AbstractFeedService(MessageQueueService messageQueueService) {
		this.messageQueueService = messageQueueService;
	}

	public ResponseEntity<Void> feed(T request) {
		logger.info("request {}", request);

		validate(request);

		NormalizedMessage message = convert(request);

		process(message);

		logger.info("Message {} succesfully processed", message);

		return ResponseEntity.ok().build();
	}

	protected abstract void validate(T request);

	protected abstract NormalizedMessage convert(T request);

	protected void process(NormalizedMessage message) {
		messageQueueService.queue(message);
	}
}

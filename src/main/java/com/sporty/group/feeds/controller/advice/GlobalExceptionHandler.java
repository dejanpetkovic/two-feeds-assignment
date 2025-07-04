package com.sporty.group.feeds.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sporty.group.feeds.controller.AlphaController;
import com.sporty.group.feeds.controller.BetaController;
import com.sporty.group.feeds.exception.InvalidFeedRequestDataException;

@ControllerAdvice(assignableTypes = { AlphaController.class, BetaController.class })
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(InvalidFeedRequestDataException.class)
	public ResponseEntity<String> handleInvalidFeedRequestDataException(InvalidFeedRequestDataException e) {
		logger.error(e.getMessage(), e);
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		logger.error(e.getMessage(), e);
		return ResponseEntity.internalServerError().body(e.getMessage());
	}

}

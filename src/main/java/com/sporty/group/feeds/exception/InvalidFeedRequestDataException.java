package com.sporty.group.feeds.exception;

public class InvalidFeedRequestDataException extends RuntimeException {

	private static final long serialVersionUID = -2922573505283690421L;

	public InvalidFeedRequestDataException() {
		super();
	}

	public InvalidFeedRequestDataException(Exception cause) {
		super(cause);
	}

	public InvalidFeedRequestDataException(String message) {
		super(message);
	}

	public InvalidFeedRequestDataException(String message, Exception cause) {
		super(message, cause);
	}

}

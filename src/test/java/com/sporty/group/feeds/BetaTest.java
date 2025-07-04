package com.sporty.group.feeds;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sporty.group.feeds.exception.InvalidFeedRequestDataException;
import com.sporty.group.feeds.request.BetaFeedRequest;
import com.sporty.group.feeds.service.BetaFeedService;

@SpringBootTest
class BetaTest {

	@Autowired
	private BetaFeedService betaFeedService;

	@Test
	void testInvalidBetaRequest1() {
		BetaFeedRequest invalidRequest1 = BetaFeedRequest.builder().type(null).build();

		assertThrows(InvalidFeedRequestDataException.class, () -> {
			betaFeedService.feed(invalidRequest1);
		});
	}
}
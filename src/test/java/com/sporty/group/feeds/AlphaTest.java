package com.sporty.group.feeds;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sporty.group.feeds.exception.InvalidFeedRequestDataException;
import com.sporty.group.feeds.request.AlphaFeedRequest;
import com.sporty.group.feeds.service.AlphaFeedService;

@SpringBootTest
class AlphaTest {

	@Autowired
	private AlphaFeedService alphaFeedService;

	@Test
	void testInvalidAlphaRequest1() {
		AlphaFeedRequest invalidRequest1 = AlphaFeedRequest.builder().type(null).build();

		assertThrows(InvalidFeedRequestDataException.class, () -> {
			alphaFeedService.feed(invalidRequest1);
		});
	}
}
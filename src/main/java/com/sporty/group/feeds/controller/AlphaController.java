package com.sporty.group.feeds.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.group.feeds.request.AlphaFeedRequest;
import com.sporty.group.feeds.service.AlphaFeedService;

@RestController
@RequestMapping("/provider-alpha")
public class AlphaController {

	private final AlphaFeedService alphaFeedService;

	public AlphaController(AlphaFeedService alphaFeedService) {
		this.alphaFeedService = alphaFeedService;
	}

	@PostMapping("/feed")
	public ResponseEntity<Void> feed(@RequestBody AlphaFeedRequest request) {
		return alphaFeedService.feed(request);
	}

}

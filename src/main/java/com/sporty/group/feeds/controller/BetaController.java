package com.sporty.group.feeds.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sporty.group.feeds.request.BetaFeedRequest;
import com.sporty.group.feeds.service.BetaFeedService;

@RestController
@RequestMapping("/provider-beta")
public class BetaController {

	private final BetaFeedService betaFeedService;

	public BetaController(BetaFeedService betaFeedService) {
		this.betaFeedService = betaFeedService;
	}

	@PostMapping("/feed")
	public ResponseEntity<Void> feed(@RequestBody BetaFeedRequest request) {
		return betaFeedService.feed(request);
	}

}

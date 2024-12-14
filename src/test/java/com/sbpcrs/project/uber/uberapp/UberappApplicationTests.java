package com.sbpcrs.project.uber.uberapp;

import com.sbpcrs.project.uber.uberapp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberappApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.sendEmail("kisenik225@ckuer.com",
				"Subject of email",
				"Body of email");
	}

}

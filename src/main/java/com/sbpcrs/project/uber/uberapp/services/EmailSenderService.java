package com.sbpcrs.project.uber.uberapp.services;

public interface EmailSenderService {

    void sendEmail(String toEmail, String subject, String body);

    void sendMultipleEmail(String toEmail[], String subject, String body);
}

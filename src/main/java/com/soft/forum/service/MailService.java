package com.soft.forum.service;

import com.soft.forum.Utils.Res;

public interface MailService {
    Res sendMail(String activationUrl, String email);
}

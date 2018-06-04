package com.hong.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by hong on 2017/5/8.
 */
@RestController
public class SessionController {

    @RequestMapping("/helloSession")
    public String helloSession(HttpSession session) {
        UUID uuid = (UUID) session.getAttribute("uuid");
        if (uuid == null) {
            uuid =UUID.randomUUID();
        }
        session.setAttribute("uuid",uuid);
        return session.getId();
    }
}

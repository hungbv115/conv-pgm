package com.process.old.service.impl;

import com.process.old.service.ScreenLogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScreenLogServiceImpl implements ScreenLogService {

    private String key(String screenId) {
        return "LOG_" + screenId;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getOrCreateLog(HttpSession session, String screenId) {
        String attrKey = key(screenId);
        Object existing = session.getAttribute(attrKey);
        if (existing instanceof List<?>) {
            return (List<String>) existing;
        }
        List<String> log = new ArrayList<>();
        session.setAttribute(attrKey, log);
        return log;
    }

    @Override
    public void clearLog(HttpSession session, String screenId) {
        List<String> log = getOrCreateLog(session, screenId);
        log.clear();
    }

    @Override
    public void removeLog(HttpSession session, String screenId) {
        session.removeAttribute(key(screenId));
    }
}



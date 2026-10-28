package com.ejemplo.mvc.controller.comando;

import jakarta.servlet.http.*;
import java.io.IOException;

public class IdiomaComando implements Comando {

    @Override
    public String ejecutar(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String lang = req.getParameter("lang");
        if ("es".equals(lang) || "en".equals(lang)) {
            // Cookie (no sesión): el idioma debe sobrevivir al cierre de sesión
            Cookie cookie = new Cookie("idiomaPreferido", lang);
            cookie.setMaxAge(30 * 24 * 60 * 60);   // 30 días
            cookie.setPath("/");
            resp.addCookie(cookie);
        }

        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer != null ? referer : req.getContextPath() + "/app");
        return null;
    }
}
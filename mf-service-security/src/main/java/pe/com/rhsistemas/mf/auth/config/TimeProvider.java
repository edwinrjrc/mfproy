package pe.com.rhsistemas.mf.auth.config;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider {

    public Date now() {
        return new Date();
    }
}

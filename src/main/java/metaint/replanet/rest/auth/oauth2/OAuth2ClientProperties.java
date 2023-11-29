package metaint.replanet.rest.auth.oauth2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Registration;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class OAuth2ClientProperties {

    private Registration registration;

    private final Map<String, Provider> provider = new HashMap<>();
    public Map<String, Provider> getProvider() {return this.provider;}
    public Map<String, Registration> getRegistration() {return (Map<String, Registration>) this.registration;}
}

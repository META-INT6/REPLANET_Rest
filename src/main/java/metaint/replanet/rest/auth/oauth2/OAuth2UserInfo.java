package metaint.replanet.rest.auth.oauth2;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    //String getName();
}
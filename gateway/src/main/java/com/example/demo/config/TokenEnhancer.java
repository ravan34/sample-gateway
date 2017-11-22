package com.example.demo.config;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class TokenEnhancer extends JwtAccessTokenConverter {

	//Not using now....

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
	                                 OAuth2Authentication authentication) {
	    DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);

	    OAuth2AccessToken enhancedToken = super.enhance(customAccessToken, authentication);
	    return enhancedToken;
	}
}
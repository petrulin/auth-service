package com.otus.authservice.domain.response.dto;

import com.otus.authservice.util.enums.AuthorizedGrantType;
import com.otus.authservice.util.enums.ResourceName;
import com.otus.authservice.util.enums.Scope;
import java.util.List;

public class ClientDetailsData {

	private String                    clientId;
	private String                    clientSecret;
	private List<ResourceName>        resourceIds;
	private List<Scope>               scopes;
	private List<AuthorizedGrantType> authorizedGrantTypes;
	private String                    webServerRedirectUri;
	private List<ResourceName>        authorities;
	private Integer                   accessTokenValidity;
	private Integer                   refreshTokenValidity;
	private String                    additionalInformation;
	private Boolean                   autoApprove;

	public ClientDetailsData() {
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public List<ResourceName> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(List<ResourceName> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public List<Scope> getScopes() {
		return scopes;
	}

	public void setScopes(List<Scope> scopes) {
		this.scopes = scopes;
	}

	public List<AuthorizedGrantType> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(List<AuthorizedGrantType> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public List<ResourceName> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<ResourceName> authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Boolean getAutoApprove() {
		return autoApprove;
	}

	public void setAutoApprove(Boolean autoApprove) {
		this.autoApprove = autoApprove;
	}

}

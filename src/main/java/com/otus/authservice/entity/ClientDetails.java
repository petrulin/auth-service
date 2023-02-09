package com.otus.authservice.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "oauth_client_details", schema = "auth_service", catalog = "postgres")
public class ClientDetails {
    @Id
    @Column(name = "client_id", nullable = false, length = 255)
    private String clientId;
    @Basic
    @Column(name = "client_secret", nullable = false, length = 255)
    private String clientSecret;
    @Basic
    @Column(name = "resource_ids", nullable = true, length = 255)
    private String resourceIds;
    @Basic
    @Column(name = "scope", nullable = true, length = 255)
    private String scopes;
    @Basic
    @Column(name = "authorized_grant_types", nullable = true, length = 255)
    private String authorizedGrantTypes;
    @Basic
    @Column(name = "web_server_redirect_uri", nullable = true, length = 255)
    private String webServerRedirectUri;
    @Basic
    @Column(name = "authorities", nullable = true, length = 255)
    private String authorities;
    @Basic
    @Column(name = "access_token_validity", nullable = true)
    private Integer accessTokenValidity;
    @Basic
    @Column(name = "refresh_token_validity", nullable = true)
    private Integer refreshTokenValidity;
    @Basic
    @Column(name = "additional_information", nullable = true, length = 4096)
    private String additionalInformation;
    @Basic
    @Column(name = "autoapprove", nullable = true, length = 255)
    private String autoApprove;

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

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
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

    public String getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        ClientDetails that = (ClientDetails) o;

        return Objects.equals(getClientId(), that.getClientId()) &&
        Objects.equals(getClientSecret(), that.getClientSecret()) &&
        Objects.equals(getResourceIds(), that.getResourceIds()) &&
        Objects.equals(getScopes(), that.getScopes()) &&
        Objects.equals(getAuthorizedGrantTypes(), that.getAuthorizedGrantTypes()) &&
        Objects.equals(getWebServerRedirectUri(), that.getWebServerRedirectUri()) &&
        Objects.equals(getAuthorities(), that.getAuthorities()) &&
        Objects.equals(getAccessTokenValidity(), that.getAccessTokenValidity()) &&
        Objects.equals(getRefreshTokenValidity(), that.getRefreshTokenValidity()) &&
        Objects.equals(getAdditionalInformation(),
            that.getAdditionalInformation()) &&
        Objects.equals(getAutoApprove(), that.getAutoApprove());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getClientSecret(), getResourceIds(),
            getScopes(), getAuthorizedGrantTypes(), getWebServerRedirectUri(),
            getAuthorities(), getAccessTokenValidity(),
            getRefreshTokenValidity(), getAdditionalInformation(),
            getAutoApprove());
    }

    @Override
    public String toString() {
        return "ClientDetails{" + "clientId='" + clientId + '\'' +
        ", clientSecret='" + clientSecret + '\'' + ", resourceIds='" +
        resourceIds + '\'' + ", scopes='" + scopes + '\'' +
        ", authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
        ", webServerRedirectUri='" + webServerRedirectUri + '\'' +
        ", authorities='" + authorities + '\'' + ", accessTokenValidity=" +
        accessTokenValidity + ", refreshTokenValidity=" + refreshTokenValidity +
        ", additionalInformation='" + additionalInformation + '\'' +
        ", autoapprove='" + autoApprove + '\'' + '}';
    }
}

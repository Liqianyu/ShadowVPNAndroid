package org.shadowvpn.shadowvpn.model;

import io.realm.RealmObject;

public class ShadowVPNConfigure extends RealmObject {
    private String title;

    private String serverIP;

    private int port;

    private String password;

    private String userToken;

    private String localIP;

    private int maximumTransmissionUnits;

    private int concurrency;

    private boolean bypassChinaRoutes;

    private boolean selected;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String pTitle) {
        this.title = pTitle;
    }

    public String getServerIP() {
        return this.serverIP;
    }

    public void setServerIP(final String pServerIP) {
        this.serverIP = pServerIP;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(final int pPort) {
        this.port = pPort;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String pPassword) {
        this.password = pPassword;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String pUserToken) {
        this.userToken = userToken;
    }

    public String getLocalIP() {
        return this.localIP;
    }

    public void setLocalIP(final String pLocalIP) {
        this.localIP = pLocalIP;
    }

    public int getMaximumTransmissionUnits() {
        return this.maximumTransmissionUnits;
    }

    public void setMaximumTransmissionUnits(final int pMaximumTransmissionUnits) {
        this.maximumTransmissionUnits = pMaximumTransmissionUnits;
    }

    public int getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(int concurrency) {
        this.concurrency = concurrency;
    }

    public boolean isBypassChinaRoutes() {
        return this.bypassChinaRoutes;
    }

    public void setBypassChinaRoutes(final boolean pBypassChinaRoutes) {
        this.bypassChinaRoutes = pBypassChinaRoutes;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(final boolean pSelected) {
        this.selected = pSelected;
    }
}
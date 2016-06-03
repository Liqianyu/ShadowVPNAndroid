package org.shadowvpn.shadowvpn.util;

import org.shadowvpn.shadowvpn.model.ShadowVPNConfigure;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ShadowVPNConfigureHelper {
    public static final String DEFAULT_LOCAL_IP = "10.7.0.2";

    public static final int DEFAULT_MAXIMUM_TRANSMISSION_UNITS = 1432;

    public static final int DEFAULT_CONCURRENCY = 1;

    public static ShadowVPNConfigure create(final String pTitle,
            final String pServerIP, final int pPort, final String pPassword,
            final String pUserToken, final String pLocalIP, final int pMaximumTransmissionUnits,
            final int pConcurrency, final boolean pBypassChinaRoutes) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        final ShadowVPNConfigure configure = realm.createObject(ShadowVPNConfigure.class);
        configure.setTitle(pTitle);
        configure.setServerIP(pServerIP);
        configure.setPort(pPort);
        configure.setPassword(pPassword);
        configure.setUserToken(pUserToken);
        configure.setLocalIP(pLocalIP);
        configure.setMaximumTransmissionUnits(pMaximumTransmissionUnits);
        configure.setBypassChinaRoutes(pBypassChinaRoutes);
        configure.setConcurrency(pConcurrency);

        realm.commitTransaction();

        return configure;
    }

    public static void delete(final String pTitle) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        final RealmQuery<ShadowVPNConfigure> shadowVPNConfigureRealmQuery = realm
                .where(ShadowVPNConfigure.class);
        shadowVPNConfigureRealmQuery.equalTo("title", pTitle);

        shadowVPNConfigureRealmQuery.findAll().clear();

        realm.commitTransaction();
    }

    public static ShadowVPNConfigure exists(final String pTitle) {
        final Realm realm = Realm.getDefaultInstance();

        final RealmQuery<ShadowVPNConfigure> shadowVPNConfigureRealmQuery = realm
                .where(ShadowVPNConfigure.class);
        shadowVPNConfigureRealmQuery.equalTo("title", pTitle);

        return shadowVPNConfigureRealmQuery.findFirst();
    }

    public static RealmResults<ShadowVPNConfigure> getAll() {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        final RealmQuery<ShadowVPNConfigure> query = realm.where(ShadowVPNConfigure.class);
        final RealmResults<ShadowVPNConfigure> configures = query.findAll();

        realm.commitTransaction();

        return configures;
    }

    public static ShadowVPNConfigure update(final ShadowVPNConfigure pShadowVPNConfigure, final String pTitle,
            final String pServerIP, final int pPort, final String pPassword,
            final String pUserToken, final String pLocalIP, final int pMaximumTransmissionUnits,
            final int pConcurrency, final boolean pBypassChinaRoutes, final boolean pSelected) {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        pShadowVPNConfigure.setTitle(pTitle);
        pShadowVPNConfigure.setServerIP(pServerIP);
        pShadowVPNConfigure.setPort(pPort);
        pShadowVPNConfigure.setPassword(pPassword);
        pShadowVPNConfigure.setUserToken(pUserToken);
        pShadowVPNConfigure.setLocalIP(pLocalIP);
        pShadowVPNConfigure.setMaximumTransmissionUnits(pMaximumTransmissionUnits);
        pShadowVPNConfigure.setBypassChinaRoutes(pBypassChinaRoutes);
        pShadowVPNConfigure.setSelected(pSelected);
        pShadowVPNConfigure.setConcurrency(pConcurrency);

        realm.commitTransaction();

        return pShadowVPNConfigure;
    }

    public static void selectShadowVPNConfigure(final String pTitle) {
        final ShadowVPNConfigure configure = ShadowVPNConfigureHelper.exists(pTitle);

        if (configure != null) {
            ShadowVPNConfigureHelper
                    .update(configure, configure.getTitle(), configure.getServerIP(),
                            configure.getPort(), configure.getPassword(), configure.getUserToken(),
                            configure.getLocalIP(), configure.getMaximumTransmissionUnits(),
                            configure.getConcurrency(), configure.isBypassChinaRoutes(), true);
        }
    }

    public static void unselectShadowVPNConfigure(final String pTitle) {
        final ShadowVPNConfigure configure = ShadowVPNConfigureHelper.exists(pTitle);

        if (configure != null) {
            ShadowVPNConfigureHelper
                    .update(configure, configure.getTitle(), configure.getServerIP(),
                            configure.getPort(), configure.getPassword(), configure.getUserToken(),
                            configure.getLocalIP(), configure.getMaximumTransmissionUnits(),
                            configure.getConcurrency(), configure.isBypassChinaRoutes(), false);
        }
    }

    public static void resetAllSelectedValue() {
        final Realm realm = Realm.getDefaultInstance();

        final RealmQuery<ShadowVPNConfigure> query = realm.where(ShadowVPNConfigure.class);
        final RealmResults<ShadowVPNConfigure> configures = query.findAll();
        realm.beginTransaction();
        for (int i = 0; i < configures.size(); i ++) {
            ShadowVPNConfigure conf = configures.get(i);

            conf.setSelected(false);
        }

        realm.commitTransaction();

    }
}
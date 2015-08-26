package org.shadowvpn.shadowvpn;

import android.app.Application;

import org.shadowvpn.shadowvpn.model.MyModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * Project - ShadowVPNAndroid
 * Created by Moyw on 8/26/15.
 */
public class ShadowApplication extends Application {

    private static final int REALM_VERSION = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration configuration = new RealmConfiguration.Builder(getApplicationContext())
                .schemaVersion(REALM_VERSION)
                .name("shadow-vpn.realm")
                .migration(new RealmMigration() {
                    @Override
                    public long execute(Realm realm, long version) {
                        return REALM_VERSION;
                    }
                })
                .setModules(new MyModule())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}

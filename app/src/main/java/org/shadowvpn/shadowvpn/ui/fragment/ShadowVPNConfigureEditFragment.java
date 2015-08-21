package org.shadowvpn.shadowvpn.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.shadowvpn.shadowvpn.R;
import org.shadowvpn.shadowvpn.model.ShadowVPNConfigure;
import org.shadowvpn.shadowvpn.util.ShadowVPNConfigureHelper;

public class ShadowVPNConfigureEditFragment extends Fragment {
    private static final String KEY_TITLE = "key_title";
    private String mTitle;
    private AppCompatEditText mTitleText;
    private AppCompatEditText mServerIPText;
    private AppCompatEditText mPortText;
    private AppCompatEditText mPasswordText;
    private AppCompatEditText mUserTokenText;
    private AppCompatEditText mLocalIPText;
    private AppCompatEditText mMaximumTransmissionUnitsText;
    private AppCompatEditText mConcurrency;
    private SwitchCompat mBypassChinaRoutesSwitch;

    public static ShadowVPNConfigureEditFragment newInstance() {
        return ShadowVPNConfigureEditFragment.newInstance(null);
    }

    public static ShadowVPNConfigureEditFragment newInstance(final String pTitle) {
        final ShadowVPNConfigureEditFragment fragment = new ShadowVPNConfigureEditFragment();

        final Bundle arguments = new Bundle();
        arguments.putString(ShadowVPNConfigureEditFragment.KEY_TITLE, pTitle);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        this.setHasOptionsMenu(true);

        if (this.getArguments() != null) {
            this.mTitle = this.getArguments().getString(ShadowVPNConfigureEditFragment.KEY_TITLE);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater pLayoutInflater, final ViewGroup pContainer,
            final Bundle pSavedInstanceState) {
        final View view = pLayoutInflater
                .inflate(R.layout.fragment_shadow_vpn_configure_edit, pContainer, false);

        this.mTitleText = (AppCompatEditText) view.findViewById(R.id.text_title);
        this.mServerIPText = (AppCompatEditText) view.findViewById(R.id.text_server_ip);
        this.mPortText = (AppCompatEditText) view.findViewById(R.id.text_port);
        this.mPasswordText = (AppCompatEditText) view.findViewById(R.id.text_password);
        this.mUserTokenText = (AppCompatEditText) view.findViewById(R.id.text_user_token);
        this.mLocalIPText = (AppCompatEditText) view.findViewById(R.id.text_local_ip);
        this.mMaximumTransmissionUnitsText = (AppCompatEditText) view
                .findViewById(R.id.text_maximum_transmission_units);
        this.mConcurrency = (AppCompatEditText) view.findViewById(R.id.text_concurrency);
        this.mBypassChinaRoutesSwitch = (SwitchCompat) view
                .findViewById(R.id.switch_bypass_china_routes);

        if (TextUtils.isEmpty(this.mTitle)) {
            this.mPortText.setText(String.valueOf(0));
            this.mLocalIPText.setText(ShadowVPNConfigureHelper.DEFAULT_LOCAL_IP);
            this.mMaximumTransmissionUnitsText.setText(
                    String.valueOf(ShadowVPNConfigureHelper.DEFAULT_MAXIMUM_TRANSMISSION_UNITS));
            this.mConcurrency.setText(String.valueOf(ShadowVPNConfigureHelper.DEFAULT_CONCURRENCY));

        } else {
            final ShadowVPNConfigure configure = ShadowVPNConfigureHelper
                    .exists(this.getActivity(), this.mTitle);

            this.mTitleText.setText(configure.getTitle());
            this.mServerIPText.setText(configure.getServerIP());
            this.mPortText.setText(String.valueOf(configure.getPort()));
            this.mPasswordText.setText(configure.getPassword());
            this.mUserTokenText.setText(configure.getUserToken());
            this.mLocalIPText.setText(configure.getLocalIP());
            this.mMaximumTransmissionUnitsText
                    .setText(String.valueOf(configure.getMaximumTransmissionUnits()));
            this.mConcurrency.setText(String.valueOf(configure.getConcurrency()));
            this.mBypassChinaRoutesSwitch.setChecked(configure.isBypassChinaRoutes());
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(final Menu pMenu, final MenuInflater pMenuInflater) {
        pMenuInflater.inflate(R.menu.fragment_shadow_vpnconfigure_edit, pMenu);
    }

    @Override
    public void onPrepareOptionsMenu(final Menu pMenu) {
        pMenu.findItem(R.id.menu_delete).setVisible(!TextUtils.isEmpty(this.mTitle));

        super.onPrepareOptionsMenu(pMenu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem pMenuItem) {
        switch (pMenuItem.getItemId()) {
            case android.R.id.home:
                if (TextUtils.isEmpty(this.mTitle)) {
                    if (this.createShadowVPNConfigure()) {
                        this.getActivity().finish();
                    }
                } else {
                    if (this.updateShadowVPNConfigure()) {
                        this.getActivity().finish();
                    }
                }
                return true;
            case R.id.menu_discard:
                this.getActivity().finish();
                return true;
            case R.id.menu_delete:
                ShadowVPNConfigureHelper.delete(this.getActivity(), this.mTitle);
                this.getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(pMenuItem);
        }
    }

    private boolean createShadowVPNConfigure() {
        final boolean inputResult = this.checkInput();

        if (inputResult) {
            final boolean existsResult = this.checkConfigureExists();

            if (!existsResult) {
                final String title = this.mTitleText.getText().toString();
                final String serverIP = this.mServerIPText.getText().toString();
                final int port = Integer.parseInt(this.mPortText.getText().toString());
                final String password = this.mPasswordText.getText().toString();
                final String userToken = this.mUserTokenText.getText().toString();
                final String localIP = this.mLocalIPText.getText().toString();
                final int maximumTransmissionUnits = Integer
                        .parseInt(this.mMaximumTransmissionUnitsText.getText().toString());
                final int concurrency = Integer.parseInt(this.mConcurrency.getText().toString());
                final boolean bypassChinaRoutes = this.mBypassChinaRoutesSwitch.isChecked();

                ShadowVPNConfigureHelper
                        .create(this.getActivity(), title, serverIP, port, password, userToken,
                                localIP, maximumTransmissionUnits, concurrency, bypassChinaRoutes);

                return true;
            }
        }

        return false;
    }

    private boolean updateShadowVPNConfigure() {
        final boolean inputResult = this.checkInput();

        if (inputResult) {
            final ShadowVPNConfigure shadowVPNConfigure = ShadowVPNConfigureHelper
                    .exists(this.getActivity(), this.mTitle);

            final String title = this.mTitleText.getText().toString();
            final String serverIP = this.mServerIPText.getText().toString();
            final int port = Integer.parseInt(this.mPortText.getText().toString());
            final String password = this.mPasswordText.getText().toString();
            final String userToken = this.mUserTokenText.getText().toString();
            final String localIP = this.mLocalIPText.getText().toString();
            final int maximumTransmissionUnits = Integer
                    .parseInt(this.mMaximumTransmissionUnitsText.getText().toString());
            final int concurrency = Integer.parseInt(this.mConcurrency.getText().toString());
            final boolean bypassChinaRoutes = this.mBypassChinaRoutesSwitch.isChecked();

            ShadowVPNConfigureHelper
                    .update(this.getActivity(), shadowVPNConfigure, title, serverIP, port, password,
                            userToken, localIP, maximumTransmissionUnits, concurrency,
                            bypassChinaRoutes, shadowVPNConfigure.isSelected());
        }

        return inputResult;
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(this.mTitleText.getText().toString())) {
            Toast.makeText(this.getActivity(), R.string.toast_vpn_configure_title_null,
                    Toast.LENGTH_SHORT).show();

            return false;
        }

        if (TextUtils.isEmpty(this.mServerIPText.getText().toString())) {
            Toast.makeText(this.getActivity(), R.string.toast_vpn_configure_server_ip_null,
                    Toast.LENGTH_SHORT).show();

            return false;
        }

        if (TextUtils.isEmpty(this.mPortText.getText().toString())) {
            Toast.makeText(this.getActivity(), R.string.toast_vpn_configure_port_null,
                    Toast.LENGTH_SHORT).show();

            return false;
        }

        if (TextUtils.isEmpty(this.mPasswordText.getText().toString())) {
            Toast.makeText(this.getActivity(), R.string.toast_vpn_configure_password_null,
                    Toast.LENGTH_SHORT).show();

            return false;
        }

        if (TextUtils.isEmpty(this.mLocalIPText.getText().toString())) {
            Toast.makeText(this.getActivity(), R.string.toast_vpn_configure_local_ip_null,
                    Toast.LENGTH_SHORT).show();

            return false;
        }

        if (TextUtils.isEmpty(this.mMaximumTransmissionUnitsText.getText().toString())) {
            Toast.makeText(this.getActivity(),
                    R.string.toast_vpn_configure_maximum_transmission_units_null,
                    Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;
    }

    private boolean checkConfigureExists() {
        final String title = this.mTitleText.getText().toString();

        final ShadowVPNConfigure configure = ShadowVPNConfigureHelper
                .exists(this.getActivity(), title);

        if (configure != null) {
            Toast.makeText(this.getActivity(),
                    this.getString(R.string.toast_vpn_configure_exists, title), Toast.LENGTH_SHORT)
                    .show();

            return true;
        } else {
            return false;
        }
    }
}
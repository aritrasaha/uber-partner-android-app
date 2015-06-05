package in.aritrasaha.uber.partner.http;

import android.net.ConnectivityManager;
import android.net.Uri;

import com.google.inject.Inject;

/**
 * @author aritra
 */
public class LoginRequestManager extends RequestManager {

    private final ConnectivityManager connectivityManager;
    private final ServerConfiguration serverConfiguration;

    @Inject
    public LoginRequestManager(ConnectivityManager connectivityManager, ServerConfiguration serverConfiguration) {
        super(connectivityManager, serverConfiguration);
        this.connectivityManager = connectivityManager;
        this.serverConfiguration = serverConfiguration;
    }

    @Override
    protected Uri.Builder getUriBuilder() {
        Uri.Builder uriBuilder = super.getUriBuilder();
        uriBuilder.encodedAuthority(serverConfiguration.getLoginHost());
        return uriBuilder;
    }

    @Override
    protected void setConnectionProperties() {
        connection.addRequestProperty("Referer", "login.uber.com");
    }

}

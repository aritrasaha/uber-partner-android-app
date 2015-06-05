package in.aritrasaha.uber.partner.config;

import android.app.Application;
import android.net.ConnectivityManager;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

import in.aritrasaha.uber.partner.http.LoginRequestManager;
import in.aritrasaha.uber.partner.http.RequestManager;
import in.aritrasaha.uber.partner.http.ServerConfiguration;

/**
 * @author aritra
 */
public class Module extends AbstractModule {

    public Module(Application application) {

    }

    /* (non-Javadoc)
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected void configure() {

    }

    @Provides
    @Inject
    public RequestManager provideRequestManager(ConnectivityManager connectivityManager,
                                                ServerConfiguration serverConfiguration) {
        RequestManager requestManager = new RequestManager(connectivityManager, serverConfiguration);
        return requestManager;
    }

    @Provides
    @Inject
    public LoginRequestManager provideLoginRequestManager(ConnectivityManager connectivityManager,
                                                          ServerConfiguration serverConfiguration) {
        LoginRequestManager loginRequestManager = new LoginRequestManager(connectivityManager, serverConfiguration);
        return loginRequestManager;
    }

}

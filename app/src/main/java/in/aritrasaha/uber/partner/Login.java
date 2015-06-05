package in.aritrasaha.uber.partner;

import android.widget.TextView;

import com.google.inject.Inject;
import com.google.inject.Provider;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.aritrasaha.uber.partner.http.LoginRequestManager;
import in.aritrasaha.uber.partner.http.RequestManager;
import in.aritrasaha.uber.partner.http.RequestPromise;

/**
 * @author aritra
 */
public class Login {

    private final Provider<LoginRequestManager> loginRequestManagerProvider;
    private final Provider<RequestManager> requestManagerProvider;

    private final CookieManager cookieManager;

    private String email;
    private String password;
    private String csrf;

    private TextView textView;

    @Inject
    public Login(Provider<LoginRequestManager> loginRequestManagerProvider, Provider<RequestManager> requestManagerProvider) {
        this.loginRequestManagerProvider = loginRequestManagerProvider;
        this.requestManagerProvider = requestManagerProvider;

        cookieManager = new CookieManager();
    }

    public void execute(String email, String password, TextView textView) {
        CookieHandler.setDefault(cookieManager);

        this.email = email;
        this.password = password;

        this.textView = textView;

        getLoginPage();
    }

    private void doLogin(String csrf, String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("_csrf_token", csrf);
        params.put("email", email);
        params.put("password", password);

        LoginRequestManager loginRequestManager = loginRequestManagerProvider.get();
        loginRequestManager.setMethod(RequestManager.RequestMethod.POST);
        loginRequestManager.setResourcePath("login");
        loginRequestManager.setToDo(new RequestPromise() {
            @Override
            public void onSuccess(String data, int status) {
                textView.setText(data);
            }
        });
        loginRequestManager.execute(null, params);
    }

    private void getLoginPage() {
        LoginRequestManager loginRequestManager = loginRequestManagerProvider.get();
        loginRequestManager.setResourcePath("login");
        loginRequestManager.setToDo(new RequestPromise() {
            @Override
            public void onSuccess(String data, int status) {
                csrf = parseCsrf(data);
                doLogin(csrf, email, password);
            }
        });
        loginRequestManager.execute(null, null);
    }

    private String parseCsrf(String html) {
        Pattern matchPattern = Pattern.compile("novalidate>\\s*<input type=\"hidden\" name=\"_csrf_token\" value=\"(.*?)\">");
        Matcher m = matchPattern.matcher(html);
        m.find();
        return m.group(1);
    }

}

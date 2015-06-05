/*
 * ServerConfiguration.java
 *
 * Product: SCG
 * Strand Clinical Genomics
 *
 * Copyright 2007-2014, Strand Life Sciences
 * 5th Floor, Kirloskar Business Park,
 * Bellary Road, Hebbal,
 * Bangalore 560024
 * India
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Strand Life Sciences., ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Strand Life Sciences.
 */
package in.aritrasaha.uber.partner.http;

import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import in.aritrasaha.uber.partner.R;

/**
 * @author aritra
 */
@Singleton
public class ServerConfiguration {

    private final String scheme;
    private final String host;
    private final String loginHost;

    @Inject
    public ServerConfiguration(Context context) {
        scheme = context.getResources().getString(R.string.server_scheme);
        host = context.getResources().getString(R.string.server_home_url);
        loginHost = context.getResources().getString(R.string.server_login_url);
    }

    public String getScheme() {
        return scheme;
    }

    public String getLoginHost() {
        return loginHost;
    }

    public String getHost() {
        return host;
    }
}

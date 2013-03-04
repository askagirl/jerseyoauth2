package com.burgmeier.jerseyoauth2.authsrv.openid;

import javax.servlet.http.HttpServletRequest;

import com.burgmeier.jerseyoauth2.api.user.IUser;
import com.burgmeier.jerseyoauth2.authsrv.api.user.IUserService;

public class OpenIdUserService implements IUserService {

	@Override
	public IUser getCurrentUser(HttpServletRequest request) {
		OpenIDUser user = (OpenIDUser)request.getSession().getAttribute(OpenIdConstants.OPENID_SESSION_VAR);
		return user;
	}

}

package com.nosoftskills.predcomposer.session;

import com.nosoftskills.predcomposer.model.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Ivan St. Ivanov
 */
@SessionScoped
@Named("userContext")
public class UserContext implements Serializable {

    private static final long serialVersionUID = -1047249575365658031L;

    private User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}

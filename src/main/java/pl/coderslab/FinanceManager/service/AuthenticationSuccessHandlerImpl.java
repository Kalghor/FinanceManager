package pl.coderslab.FinanceManager.service;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.coderslab.FinanceManager.domain.model.User;
import pl.coderslab.FinanceManager.web.app.ScheduledExpensesLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    private ScheduledExpensesLoader scheduledExpensesLoader;
    private final UserManagerService userManagerService;

    public AuthenticationSuccessHandlerImpl(ScheduledExpensesLoader scheduledExpensesLoader, UserManagerService userManagerService) {
        this.scheduledExpensesLoader = scheduledExpensesLoader;
        this.userManagerService = userManagerService;
    }

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication currentUser) throws IOException, ServletException {
        User user = userManagerService.findByUsername(currentUser.getName());
        scheduledExpensesLoader.loadScheduledExpenses(user);
    }
}
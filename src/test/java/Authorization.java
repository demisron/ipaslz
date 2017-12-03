import Pages.AuthPage;
import Pages.PltMainPage;
import com.thoughtworks.gauge.Step;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static MethodsCommon.Driver.driver;
import static MethodsCommon.Func.*;

/**
 * Created by FedorchukR on 21.03.2017.
 */
public class Authorization {

    Log log = LogFactory.getLog(Authorization.class);
    private static String myprod;

    @Step("Authorisation user - <lg> and pwd - <pw> with localization page <number>")
    public void authorisationLogin(String login, String password, Integer number) throws InterruptedException {
        AuthPage authPage = new AuthPage(driver);
        PltMainPage pltMainPage = new PltMainPage(driver);

        navigateToUrl();
        log.info("Browser - opened");

        authPage.InputFieldClick(login, password);
        log.info("Authorization - done");
        //waitElement(pltMainPage.getNameBlock());
        waitElement(pltMainPage.getLstElement());

        myprod = pltMainPage.getLbRedName().getText();
        if (myprod.compareTo("Мої продукти ЛІГА:ЗАКОН") == 0 && number == 2 ||
                (myprod.compareTo("Мои продукты ЛІГА:ЗАКОН") == 0 && number == 1)) {
            setLocalLanguage(number);
            log.info("Localization - switched");
        }
    }

    @Step("Authorisation our user with conf file with localization page <number>")
    public Object authorisation_our_user_with_conf_file(Integer number) throws InterruptedException {
        AuthPage authPage = new AuthPage(driver);
        PltMainPage pltMainPage = new PltMainPage(driver);

        String login = System.getenv("LOGIN");
        String passwd = System.getenv("PASSWD");

        Object currentPage = authPage.loadUsing(driver);
        log.info("Browser - opened");

        authPage.InputFieldClick(login, passwd);
        log.info("Authorization - done");
        waitElement(pltMainPage.getNameBlock());
        waitElement(pltMainPage.getLstElement());

        myprod = byGetTextElement(pltMainPage.getGetNameBlock());
//        myprod = pltMainPage.getNameBlock().getText();
        if (myprod.compareTo("Мої продукти ЛІГА:ЗАКОН") == 0 && number == 2 ||
                (myprod.compareTo("Мои продукты ЛІГА:ЗАКОН") == 0 && number == 1)) {
            setLocalLanguage(number);
            log.info("Localization - switched");
        }

        log.info("For checking - ready");
        return currentPage;
    }
}

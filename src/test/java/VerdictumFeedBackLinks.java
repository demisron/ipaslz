import Pages.IpsMainPage;
import Pages.OpenDocPage;
import com.thoughtworks.gauge.Step;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

import static MethodsCommon.Driver.driver;
import static MethodsCommon.Func.*;

/**
 * Created by FedorchukR on 28.08.2017.
 */
public class VerdictumFeedBackLinks {

    OpenDocPage oPage = new OpenDocPage(driver);
    IpsMainPage mPage = new IpsMainPage(driver);

    Log log = LogFactory.getLog(VerdictumFeedBackLinks.class);

    @Step("Try out links <verdictum> in document")
    public void VerdictumFeedBackLinks(String str) throws Exception {
            if(str.equals("verdictum")){
                HashMap<String, String> values = selectNewWindow(oPage.setWaitLinkVrd(str));
                waitElement(oPage.getWaitListVrd());
                driver.switchTo().window(values.get("nWindow")).close();
                driver.switchTo().window(values.get("pWindow"));
            }
            else if(str.equals("ipas")){
                scrollToElementClick(oPage.getLnkBackLinks());
                oPage.getLnkFolderInTree().click();                         // Открываем список ссылок в правом меню

                oPage.getLnkBackLinksInTree().click();                      // Открытие ссылки на обратные связи
                checkOpenDoc();

                checkSortDateList(oPage.getLstDate(), "\\.", true);  // Проверяем сортировку  true = на уменьшение

                String titlePage = driver.getTitle();
                HashMap<String, String> values = selectNewWindow(oPage.getLnkAllResultBackLinks()); // Переход на "Все обратные связи"

                String nLeftDoc = mPage.getTxtNameDocLeftDoc().getText();
                String titlePars[] = nLeftDoc.split("от"); // Тайтл в левом меню = тайтлу документа
                if(!titlePage.contains(titlePars[0])){
                    throw new InterruptedException("Name in left menu-> "+nLeftDoc+" <- donn't match title on open page->"+titlePage);
                }
                driver.switchTo().window(values.get("nWindow")).close();
                driver.switchTo().window(values.get("pWindow"));
            }
            else if(str.equals("kk"))
                waitElement(oPage.getLnkKk());
    }
}

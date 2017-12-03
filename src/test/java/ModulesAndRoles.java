import MethodsCommon.MethodsCommon;
import Pages.MainIpsPage;
import com.thoughtworks.gauge.ContinueOnFailure;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

import static MethodsCommon.Driver.driver;

public class ModulesAndRoles extends MethodsCommon {
    private static MainIpsPage mainIpsPage = new MainIpsPage(driver);
    private static ModulesAndRoles modulesAndRoles = new ModulesAndRoles();
    Log log = LogFactory.getLog(ModulesAndRoles.class);

    @ContinueOnFailure(AssertionError.class)
    @Step("Switch to <roleName> role")
//    Выполняется клик по переданной в параметре ссылке с названием роли (Руководителю, Юристу)
    public void switchToRole(String roleName) throws InterruptedException {
        switch (roleName) {
            case "Всем": mainIpsPage.role_all_link.click();
                break;
            case "Руководителю": mainIpsPage.role_chief_link.click();
                break;
            case "Юристу": mainIpsPage.role_lawyer_link.click();
                break;
            case "Бухгалтеру": mainIpsPage.role_accountant_link.click();
                break;
            case "Кадровику": mainIpsPage.role_hr_link.click();
                break;
            default: log.info("Неверное название роли в таблице файла.spec");
        }
}
    @ContinueOnFailure(AssertionError.class)
    @Step("Check <moduleName> module is Dispayed")
// Проверяется наличие иконки и текстовое название модуля
    public void checkModuleIsDisplayed(String moduleName) throws InterruptedException {
        switch (moduleName) {
            case "Законодательство Украины": waitForElementDisplay(mainIpsPage.lawmaking_box);
                shouldSeeElement(mainIpsPage.lawmaking_module_icon);
                shouldSeeElementWithText(mainIpsPage.lawmaking_module_name, "ЗАКОНОДАТЕЛЬСТВО УКРАИНЫ");
                break;
            case "Правовая картина дня": waitForElementDisplay(mainIpsPage.law_pictures_box);
                shouldSeeElement(mainIpsPage.law_pictures_module_icon);
                shouldSeeElementWithText(mainIpsPage.law_pictures_module_name, "ПРАВОВАЯ КАРТИНА ДНЯ");
                break;
            case "Новации": waitForElementDisplay(mainIpsPage.novations_box);
                shouldSeeElement(mainIpsPage.novations_module_icon);
                shouldSeeElementWithText(mainIpsPage.novations_module_name, "НОВАЦИИ");
                break;
            case "Отрасли экономики": waitForElementDisplay(mainIpsPage.industry_box);
                shouldSeeElement(mainIpsPage.industry_module_icon);
                shouldSeeElementWithText(mainIpsPage.industry_module_name, "ОТРАСЛИ ЭКОНОМИКИ");
                break;
            case "Алгоритмы действий": waitForElementDisplay(mainIpsPage.algorithms_box);
                shouldSeeElement(mainIpsPage.algorithms_module_icon);
                shouldSeeElementWithText(mainIpsPage.algorithms_module_name, "АЛГОРИТМЫ ДЕЙСТВИЙ");
                shouldSeeElementWithText(mainIpsPage.algorithms_module_for_business, "ДЛЯ БИЗНЕСА");
                break;
            case "Бухгалтер и закон": waitForElementDisplay(mainIpsPage.acc_and_law_box);
                shouldSeeElement(mainIpsPage.bz_module_icon);
                shouldSeeElementWithText(mainIpsPage.bz_box_name, "БУХГАЛТЕР И ЗАКОН");
                break;
            case "Типовые договоры и шаблоны": waitForElementDisplay(mainIpsPage.patterns_box);
                shouldSeeElement(mainIpsPage.patterns_module_icon);
                shouldSeeElementWithText(mainIpsPage.patterns_module_name, "ТИПОВЫЕ ДОГОВОРЫ И ШАБЛОНЫ");
                break;
            case "Банкротство": waitForElementDisplay(mainIpsPage.bankruptcy_box);
                shouldSeeElement(mainIpsPage.bankruptcy_module_icon);
                shouldSeeElementWithText(mainIpsPage.bankruptcy_module_name, "БАНКРОТСТВО");
                break;
            case "Власть Украины": waitForElementDisplay(mainIpsPage.authority_box);
                shouldSeeElement(mainIpsPage.authority_module_icon);
                shouldSeeElementWithText(mainIpsPage.authority_module_name, "ВЛАСТЬ УКРАИНЫ" );
                break;
            case "Ситуации для бухгалтера": waitForElementDisplay(mainIpsPage.sit_accountant_box);
                shouldSeeElement(mainIpsPage.sit_accountant_module_icon);
                shouldSeeElementWithText(mainIpsPage.sit_accountant_module_name, "СИТУАЦИИ");
                shouldSeeElementWithText(mainIpsPage.sit_accountant_text, "ДЛЯ БУХГАЛТЕРА");
                break;
            case "Ситуации для юриста": waitForElementDisplay(mainIpsPage.sit_lawyer_box);
                shouldSeeElement(mainIpsPage.sit_lawyer_module_icon);
                shouldSeeElementWithText(mainIpsPage.sit_lawyer_module_name, "СИТУАЦИИ" );
                shouldSeeElementWithText(mainIpsPage.sit_lawyer_text, "ДЛЯ ЮРИСТА");
                break;
            case "Формы и бланки": waitForElementDisplay(mainIpsPage.form_box);
                shouldSeeElement(mainIpsPage.form_module_icon);
                shouldSeeElementWithText(mainIpsPage.form_module_name, "ФОРМЫ И БЛАНКИ");
                break;
            case "Законопроекты": waitForElementDisplay(mainIpsPage.law_project_box);
                shouldSeeElement(mainIpsPage.law_project_module_icon);
                shouldSeeElementWithText(mainIpsPage.law_project_module_name, "ЗАКОНОПРОЕКТЫ");
                break;
            case "Судебная практика": waitForElementDisplay(mainIpsPage.court_practice_box);
                shouldSeeElement(mainIpsPage.court_practice_module_icon);
                shouldSeeElementWithText(mainIpsPage.court_practice_module_name, "СУДЕБНАЯ ПРАКТИКА");
                break;
            case "Судебные прецеденты": waitForElementDisplay(mainIpsPage.jur_precedent_box);
                shouldSeeElement(mainIpsPage.jur_precedent_module_icon);
                shouldSeeElementWithText(mainIpsPage.jur_precedent_module_name, "CУДЕБНЫЕ ПРЕЦЕДЕНТЫ");
                break;
            case "Комментированные кодексы": waitForElementDisplay(mainIpsPage.comments_codex_box);
                shouldSeeElement(mainIpsPage.comment_codex_module_icon);
                shouldSeeElementWithText(mainIpsPage.comment_codex_module_name, "КОММЕНТИРОВАННЫЕ КОДЕКСЫ");
                break;
            case "Европейское законодательство": waitForElementDisplay(mainIpsPage.euroLegisl_box);
                shouldSeeElement(mainIpsPage.euroLegisl_module_icon);
                shouldSeeElementWithText(mainIpsPage.euroLegisl_module_name, "ЕВРОПЕЙСКОЕ ЗАКОНОДАТЕЛЬСТВО" );
                break;
            case "Искусство обороны": waitForElementDisplay(mainIpsPage.security_bus_box);
                shouldSeeElement(mainIpsPage.security_bus_module_icon);
                shouldSeeElementWithText(mainIpsPage.security_bus_module_name, "ИСКУССТВО ОБОРОНЫ");
                break;
            case "Терминологический словарь": waitForElementDisplay(mainIpsPage.vocabulary_box);
                shouldSeeElement(mainIpsPage.vocabulary_module_icon);
                shouldSeeElementWithText(mainIpsPage.vacabulary_module_name, "ТЕРМИНОЛОГИЧЕСКИЙ СЛОВАРЬ" );
                break;
            case "Ситуации для бизнеса": waitForElementDisplay(mainIpsPage.sit_business_box);
                shouldSeeElement(mainIpsPage.sit_business_module_icon);
                shouldSeeElementWithText(mainIpsPage.sit_business_module_name, "СИТУАЦИИ");
                shouldSeeElementWithText(mainIpsPage.sit_business_text, "ДЛЯ БИЗНЕСА");
                break;
            case "Консультации личного эксперта": waitForElementDisplay(mainIpsPage.consultations_box);
                shouldSeeElement(mainIpsPage.consultations_module_icon);
                shouldSeeElementWithText(mainIpsPage.cousultations_module_name, "КОНСУЛЬТАЦИИ ЛИЧНОГО ЭКСПЕРТА");
                break;
            case "Календарь бухгалтера": waitForElementDisplay(mainIpsPage.calend_account_box);
                shouldSeeElement(mainIpsPage.calend_account_module_icon);
                shouldSeeElementWithText(mainIpsPage.calend_account_module_name, "КАЛЕНДАРЬ БУХГАЛТЕРА");
                break;
            case "Календарь юриста": waitForElementDisplay(mainIpsPage.calend_lawyer_box);
                shouldSeeElement(mainIpsPage.calend_lawyer_module_icon);
                shouldSeeElementWithText(mainIpsPage.calend_lawyer_module_name, "КАЛЕНДАРЬ ЮРИСТА");
                break;
            case "База налоговых знаний": waitForElementDisplay(mainIpsPage.tax_base_box);
                shouldSeeElement(mainIpsPage.tax_base_module_icon);
                shouldSeeElementWithText(mainIpsPage.tax_base_module_name, "БАЗА НАЛОГОВЫХ ЗНАНИЙ");
                break;
            case "Ситуации для кадровика": waitForElementDisplay(mainIpsPage.sit_hr_box);
                shouldSeeElement(mainIpsPage.sit_hr_module_icon);
                shouldSeeElementWithText(mainIpsPage.sit_hr_module_text, "ДЛЯ КАДРОВИКА");
                shouldSeeElementWithText(mainIpsPage.sit_hr_module_name, "СИТУАЦИИ");
                break;
            case "Судебная практика для бухгалтера": waitForElementDisplay(mainIpsPage.jud_pract_account_box);
                shouldSeeElement(mainIpsPage.jud_pract_account_icon);
                shouldSeeElementWithText(mainIpsPage.jud_pract_account_text, "ДЛЯ БУХГАЛТЕРА");
                shouldSeeElementWithText(mainIpsPage.jud_pract_account_name, "СУДЕБНАЯ ПРАКТИКА");
                break;
            case "Калькулятор отпусков": waitForElementDisplay(mainIpsPage.calc_vacation_box);
                shouldSeeElement(mainIpsPage.calc_vacation_module_icon);
                shouldSeeElementWithText(mainIpsPage.calc_vacation_module_name, "КАЛЬКУЛЯТОР ОТПУСКОВ");
                break;
            case "Калькулятор индексации зарплаты": waitForElementDisplay(mainIpsPage.calc_salary_box);
                shouldSeeElement(mainIpsPage.calc_salary_module_icon);
                shouldSeeElementWithText(mainIpsPage.calc_salary_module_name, "КАЛЬКУЛЯТОР ИНДЕКСАЦИИ ЗАРПЛАТЫ");
                break;
            case "Калькулятор штрафов": waitForElementDisplay(mainIpsPage.calc_fine_box);
                shouldSeeElement(mainIpsPage.calc_fine_module_icon);
                shouldSeeElementWithText(mainIpsPage.calc_fine_module_name, "КАЛЬКУЛЯТОР ШТРАФОВ");
                break;
            case "Инструкции и шаблоны для кадровика": waitForElementDisplay(mainIpsPage.instruction_pattern_box);
                shouldSeeElement(mainIpsPage.instruction_pattern_module_icon);
                shouldSeeElementWithText(mainIpsPage.instruction_pattern_module_name, "ИНСТРУКЦИИ И ШАБЛОНЫ");
                shouldSeeElementWithText(mainIpsPage.instruction_pattern_module_text, "ДЛЯ КАДРОВИКА");
                break;
            case "Справочники": waitForElementDisplay(mainIpsPage.directories_box);
                shouldSeeElement(mainIpsPage.directories_module_icon);
                shouldSeeElementWithText(mainIpsPage.directories_module_name, "СПРАВОЧНИКИ");
                break;
            case "Бухгалтерские проводки": waitForElementDisplay(mainIpsPage.buh_spend_box);
                shouldSeeElement(mainIpsPage.buh_spend_icon);
                shouldSeeElementWithText(mainIpsPage.buh_spend_name, "БУХГАЛТЕРСКИЕ ПРОВОДКИ");
                break;
            default: log.info("Неверное название модуля в таблице файла.spec");
        }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check <moduleName> module is NOT Dispayed")
// Проверяется отсутствие квадратика модуля
    public void checkModuleIsNotDisplayed(String moduleName) throws InterruptedException {
        switch (moduleName) {
            case "Отрасли экономики":
                shouldNotSeeElement(mainIpsPage.INDUSTRY_BOX);
                break;
            case "Алгоритмы действий":
                shouldNotSeeElement(mainIpsPage.ALGORITMS_BOX);
                break;
            case "Бухгалтер и закон":
                shouldNotSeeElement(mainIpsPage.BZ_BOX);
                break;
            case "Банкротство":
                shouldNotSeeElement(mainIpsPage.BANKRUPTCY_BOX);
                break;
            case "Ситуации для бухгалтера":
                shouldNotSeeElement(mainIpsPage.SIT_ACCOUNTANT_BOX);
                break;
            case "Ситуации для юриста":
                shouldNotSeeElement(mainIpsPage.SIT_LAWYER_BOX);
                break;
            case "Формы и бланки":
                shouldNotSeeElement(mainIpsPage.FORM_BOX);
                break;
            case "Законопроекты":
                shouldNotSeeElement(mainIpsPage.LAW_PROJECT_BOX);
                break;
            case "Судебная практика":
                shouldNotSeeElement(mainIpsPage.COURT_PRACTICE_BOX);
                break;
            case "Судебные прецеденты":
                shouldNotSeeElement(mainIpsPage.JUR_PRECEDENT_BOX);
                break;
            case "Комментированные кодексы":
                shouldNotSeeElement(mainIpsPage.COMMENTS_CODEX_BOX);
                break;
            case "Европейское законодательство":
                shouldNotSeeElement(mainIpsPage.EUROLEGISL_BOX);
                break;
            case "Искусство обороны":
                shouldNotSeeElement(mainIpsPage.SECURITY_BUS_BOX);
                break;
            case "Терминологический словарь":
                shouldNotSeeElement(mainIpsPage.VOCABULARY_BOX);
                break;
            case "Ситуации для бизнеса":
                shouldNotSeeElement(mainIpsPage.SIT_BUSINESS_BOX);
                break;
            case "Консультации личного эксперта":
                shouldNotSeeElement(mainIpsPage.CONSULTATIONS_BOX);
                break;
            case "Календарь бухгалтера":
                shouldNotSeeElement(mainIpsPage.CALEND_ACCOUNT_BOX);
                break;
            case "Календарь юриста":
                shouldNotSeeElement(mainIpsPage.CALEND_LAWYER_BOX);
                break;
            case "База налоговых знаний":
                shouldNotSeeElement(mainIpsPage.TAX_BASE_BOX);
                break;
            case "Ситуации для кадровика":
                shouldNotSeeElement(mainIpsPage.SIT_HR_BOX);
                break;
            case "Судебная практика для бухгалтера":
                shouldNotSeeElement(mainIpsPage.JUD_PRACT_ACCOUNT_BOX);
                break;
            case "Калькулятор отпусков":
                shouldNotSeeElement(mainIpsPage.CALC_VACATION_BOX);
                break;
            case "Калькулятор индексации зарплаты":
                shouldNotSeeElement(mainIpsPage.CALC_SALARY_BOX);
                break;
            case "Калькулятор штрафов":
                shouldNotSeeElement(mainIpsPage.CALC_FINE_BOX);
                break;
            case "Инструкции и шаблоны для кадровика":
                shouldNotSeeElement(mainIpsPage.INSTRUCTION_PATTERN_BOX);
                break;
            case "Справочники":
                shouldNotSeeElement(mainIpsPage.DIRECTORIES_BOX);
                break;
            case "Бухгалтерские проводки":
                shouldNotSeeElement(mainIpsPage.BUH_SPEND_BOX);
                break;
            default: log.info("Неверное название модуля в таблице файла.spec");

        }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check module is Displayed in role <roleTable>")
    public void checkModuleIsDisplayedInRole(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            modulesAndRoles.switchToRole(row.getCell(columnNames.get(1))); //второй столбец
            modulesAndRoles.checkModuleIsDisplayed(row.getCell(columnNames.get(0))); //первый столбец
        }
    }

    @ContinueOnFailure(AssertionError.class)
    @Step("Check module is NOT Displayed in role <roleTable>")
    public void checkModuleIsNotDisplayedInRole(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            modulesAndRoles.switchToRole(row.getCell(columnNames.get(1))); //второй столбец
            modulesAndRoles.checkModuleIsNotDisplayed(row.getCell(columnNames.get(0))); //первый столбец
        }
    }

}

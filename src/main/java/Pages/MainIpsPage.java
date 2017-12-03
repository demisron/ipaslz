package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainIpsPage {
    private WebDriver driver;

    public static final String BZ_BOX = "//*[@id='accountant_and_law']//div[@class='box']";
    public static final String INDUSTRY_BOX = "//*[@id='industry']//*[@class='box new-box']";
    public static final String ALGORITMS_BOX = "//*[@id='business_schema']//*[@class='box new-box']";
    public static final String BANKRUPTCY_BOX = "//*[@id='bankruptcy']//div[@class='box']";
    public static final String SIT_ACCOUNTANT_BOX = "//*[@id='situationAccountant']//div[@class='box']";
    public static final String SIT_LAWYER_BOX = "//*[@id='situationLawyer']//div[@class='box']";
    public static final String FORM_BOX = "//*[@id='forms']//div[@class='box']";
    public static final String LAW_PROJECT_BOX = "//*[@id='lawProject']//div[@class='box']";
    public static final String COURT_PRACTICE_BOX = "//*[@id='courtPractice']//div[@class='box']";
    public static final String JUR_PRECEDENT_BOX = "//*[@id='juridical_precedents']//div[@class='box']";
    public static final String COMMENTS_CODEX_BOX = "//*[@id='commentsCodex']//div[@class='box']";
    public static final String EUROLEGISL_BOX = "//*[@id='europeanLegislation']//div[@class='box']";
    public static final String SECURITY_BUS_BOX = "//*[@id='securityBusiness']//div[@class='box']";
    public static final String VOCABULARY_BOX = "//*[@id='termVocabulary']//div[@class='box']";
    public static final String SIT_BUSINESS_BOX = "//*[@id='situationBusiness']//div[@class='box']";
    public static final String CONSULTATIONS_BOX = "//*[@id='consultations']//div[@class='box']";
    public static final String CALEND_ACCOUNT_BOX = "//*[@id='calendarAccountant']//div[@class='box']";
    public static final String CALEND_LAWYER_BOX  = "//*[@id='calendarLawyer']//div[@class='box']";
    public static final String TAX_BASE_BOX = "//*[@id='taxBase']//div[@class='box']";
    public static final String SIT_HR_BOX = "//*[@id='cases_for_hr']//div[@class='box']";
    public static final String JUD_PRACT_ACCOUNT_BOX  = "//*[@id='judicial_practice_for_acountant']//div[@class='box']";
    public static final String CALC_VACATION_BOX  = "//*[@id='calculator_vc']//div[@class='box']";
    public static final String CALC_SALARY_BOX = "//*[@id='calculator_zp']//div[@class='box']";
    public static final String CALC_FINE_BOX = "//*[@id='fines_calc']//div[@class='box']";
    public static final String INSTRUCTION_PATTERN_BOX = "//*[@id='instructions']//div[@class='box']";
    public static final String DIRECTORIES_BOX = "//*[@id='directories']//div[@class='box']";
    public static final String BUH_SPEND_BOX = "//*[@id='wiring']//div[@class='box']";

    public MainIpsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id='lawmaking']//*[@class='box']")
    public WebElement lawmaking_box;//квадратик модуля "Законодательство Украины"

    @FindBy(xpath ="//*[@class='module_ico lawmaking']" )
    public WebElement lawmaking_module_icon;//иконка модуля "Законодательство Украины"

    @FindBy(xpath = "//*[@id='lawmaking']//*[text()='Законодательство Украины']")
    public WebElement lawmaking_module_name;//название модуля "Законодательство Украины"

    @FindBy(xpath = "//*[@id='lawPictures']//*[@class='box']")
    public WebElement law_pictures_box;//квадратик модуля "Правовая картина дня"

    @FindBy(xpath ="//*[@class='module_ico law-picture']" )
    public WebElement law_pictures_module_icon;//иконка модуля "Правовая картина дня"

    @FindBy(xpath = "//*[@id='lawPictures']//*[text()='Правовая картина дня']")
    public WebElement law_pictures_module_name;//название модуля "Правовая картина дня"

    @FindBy(xpath = "//*[@id='mapNews']//*[@class='box']" )
    public WebElement novations_box;//квадратик модуля "Новации"

    @FindBy(xpath = "//*[@class='module_ico map-new']")
    public WebElement novations_module_icon;//иконка модуля "Новации"

    @FindBy(xpath = "//*[@id='mapNews']//*[text()='Новации']")
    public WebElement novations_module_name;// название модуля "Новации"

    @FindBy(xpath = "//*[@id='industry']//*[@class='box new-box']")
    public WebElement industry_box;//квадратик модуля "Отрасли экономики"

    @FindBy(xpath = "//*[@class='module_ico industry']")
    public WebElement industry_module_icon;//иконка модуля "Отрасли экономики"

    @FindBy(xpath = "//*[@id='industry']//*[text()='Отрасли экономики']")//название модуля "Отрасли экономики"
    public WebElement industry_module_name;

    @FindBy(xpath = "//*[@id='business_schema']//*[@class='box new-box']")//квадратик модуля "Алгоритмы действий"
    public WebElement algorithms_box;

    @FindBy(xpath = "//*[@class='module_ico business_schema']")
    public WebElement algorithms_module_icon;//иконка модуля "Алгоритмы действий"

    @FindBy(xpath = "//*[@id='business_schema']//*[text()='Алгоритмы действий']")
    public WebElement algorithms_module_name;// название модуля "Алгоритмы действий"

    @FindBy(xpath ="//*[@id='business_schema']//*[text()='для бизнеса']")
    public WebElement algorithms_module_for_business;// текст "Для бизнеса" под названием модуля "Алгоритмы действий"

    @FindBy(xpath="//div[@id='accountant_and_law']//*[@class='box']") //квадратик модуля "БЗ"
    public WebElement acc_and_law_box;

    @FindBy(xpath = "//*[@id='accountant_and_law']//*[text()='БУХГАЛТЕР И ЗАКОН']")
    public WebElement bz_box_name; // название модуля "БЗ"

    @FindBy(xpath = "//*[@class='module_ico accountant_and_law']")
    public WebElement bz_module_icon; // иконка модуля "БЗ"

    @FindBy(xpath = "//*[@id='patterns']//*[@class='box']")
    public WebElement  patterns_box;// квадратик модуля "Типовые договоры и шаблоны"

    @FindBy(xpath = "//*[@class='module_ico patterns']")
    public WebElement patterns_module_icon;//иконка модуля "Типовые договоры и шаблоны"

    @FindBy(xpath = "//*[@id='patterns']//*[text()='Типовые договоры и шаблоны']")
    public WebElement patterns_module_name; // название модуля "Типовые договоры и шаблоны"

    @FindBy(xpath = "//*[@id='bankruptcy']//*[@class='box']")//квадратик модуля "Банкротство"
    public WebElement bankruptcy_box;

    @FindBy(xpath = "//*[@class='module_ico bankruptcy']")
    public WebElement bankruptcy_module_icon;//иконка модуля "Банкротство"

    @FindBy(xpath = "//*[@id='bankruptcy']//*[text()='Банкротство']")
    public WebElement bankruptcy_module_name;//название модуля "Банкротство"

    @FindBy(xpath = "//*[@id='authorityUa']")
    public WebElement authority_box;//квадратик модуля "Власть Украины"

    @FindBy(xpath = "//*[@class='module_ico authority-ua']")
    public WebElement authority_module_icon;//иконка модуля "Власть Украины"

    @FindBy(xpath = "//*[@id='authorityUa']//*[text()='Власть Украины']")
    public WebElement authority_module_name;//название модуля "Власть Украины"

    @FindBy(xpath = "//*[@id='situationAccountant']//*[@class='box']")
    public WebElement sit_accountant_box;//квадратик модуля "Ситуации для Бухгалтера"

    @FindBy(xpath = "//*[@id='situationAccountant']//*[@class='module_ico situation']")
    public WebElement sit_accountant_module_icon;//иконка модуля "Ситуации для Бухгалтера"

    @FindBy(xpath = "//*[@id='situationAccountant']//*[text()='Ситуации']")
    public WebElement sit_accountant_module_name;//название модуля "Ситуациии для Бухгалтера"

    @FindBy(xpath = "//*[@id='situationAccountant']//*[text()='для бухгалтера']")
    public WebElement sit_accountant_text;//текст "Для бухгалтера" модуля "Ситуации для бухгалтера"

    @FindBy(xpath = "//*[@id='situationLawyer']//*[@class='box']")
    public WebElement sit_lawyer_box;//квадратик модуля "Ситуации для юриста"

    @FindBy(xpath = "//*[@id='situationLawyer']//*[@class='module_ico situation']")
    public WebElement sit_lawyer_module_icon;//иконка модуля "Ситуации для юриста"

    @FindBy(xpath = "//*[@id='situationLawyer']//*[text()='Ситуации']")
    public WebElement sit_lawyer_module_name;//название модуля "Ситуации для юриста"

    @FindBy(xpath = "//*[@id='situationLawyer']//*[text()='для юриста']")
    public WebElement sit_lawyer_text;//текст "Для юриста" модуля "Ситуации для юриста"

    @FindBy(xpath = "//*[@id='forms']//*[@class='box']")
    public WebElement form_box;//квадратик модуля "Формы и бланки"

    @FindBy(xpath = "//*[@id='forms']//*[@class='module_ico forms']")
    public WebElement form_module_icon;//иконка модуля "Формы и бланки"

    @FindBy(xpath = "//*[@id='forms']//*[text()='Формы и бланки']")
    public WebElement form_module_name;//название модуля "Формы и бланки"

    @FindBy(xpath = "//*[@id='lawProject']//*[@class='box']")
    public WebElement law_project_box;//квадратик модуля "Законопроекты"

    @FindBy(xpath = "//*[@id='lawProject']//*[@class='module_ico law-projects']")
    public WebElement law_project_module_icon;//иконка модуля "Законопроекты"

    @FindBy(xpath = "//*[@id='lawProject']//*[text()='Законопроекты']")
    public WebElement law_project_module_name;//название модуля "Законопроекты"

    @FindBy(xpath = "//*[@id='courtPractice']//*[@class='box']")
    public WebElement court_practice_box;//квадратик модуля "Судебная практика"

    @FindBy(xpath = "//*[@id='courtPractice']//*[@class='module_ico court-practice']")
    public WebElement court_practice_module_icon;//иконка модуля "Судебная практика"

    @FindBy(xpath = "(//*[@id='courtPractice']//*[text()='Судебная практика'])[2]")
    public WebElement court_practice_module_name;//название модуля "Судебная практика"

    @FindBy(xpath = "//*[@id='juridical_precedents']//*[@class='box']")
    public WebElement jur_precedent_box;//квадратик модуля "Судебные прецеденты"

    @FindBy(xpath = "//*[@id='juridical_precedents']//*[@class='module_ico juridical_precedents']")
    public WebElement jur_precedent_module_icon;//иконка модуля "Судебные прецеденты"

    @FindBy(xpath = "//*[@id='juridical_precedents']//*[text()='Cудебные прецеденты']")
    public WebElement jur_precedent_module_name;//назввание модуля "Судебные прецеденты"

    @FindBy(xpath = "//*[@id='commentsCodex']//*[@class='box']")
    public WebElement comments_codex_box;//квадратик модуля "Комментированные кодексы"

    @FindBy(xpath = "//*[@id='commentsCodex']//*[@class='module_ico comments']")
    public WebElement comment_codex_module_icon;//иконка модуля "Комментированные кодексы"

    @FindBy(xpath = "//*[@id='commentsCodex']//*[text()='Комментированные кодексы']")
    public WebElement comment_codex_module_name;//названия модуля "Комментированные кодексы"

    @FindBy(xpath = "//*[@id='europeanLegislation']//*[@class='box']")
    public WebElement euroLegisl_box;//квадратик модуля "Европейское законодательство"

    @FindBy(xpath = "//*[@id='europeanLegislation']//*[@class='module_ico btn-euro']")
    public WebElement euroLegisl_module_icon;//иконка модуля "Европейское законодательство"

    @FindBy(xpath = "//*[@id='europeanLegislation']//*[text()='Европейское законодательство']")
    public WebElement euroLegisl_module_name;//название модуля "Европейское законодательство"

    @FindBy(xpath = "//*[@id='securityBusiness']//*[@class='box']")
    public WebElement security_bus_box;//квадратик модуля "Искусство обороны"

    @FindBy(xpath = "//*[@id='securityBusiness']//*[@class='module_ico security']")
    public WebElement security_bus_module_icon;//иконка модуля "Искусство обороны"

    @FindBy(xpath = "//*[@id='securityBusiness']//*[text()='Искусство обороны']")
    public WebElement security_bus_module_name;//название модуля "Искуссвто обороны"

    @FindBy(xpath = "//*[@id='termVocabulary']//*[@class='box']")
    public WebElement vocabulary_box;//квадратик модуля "Терминологический словарь"

    @FindBy(xpath = "//*[@id='termVocabulary']//*[@class='module_ico term-vocabulary']")
    public WebElement vocabulary_module_icon;//иконка модуля "Терминологический словарь"

    @FindBy(xpath = "//*[@id='termVocabulary']//*[text()='Терминологический словарь']")
    public WebElement vacabulary_module_name;//название модуля "Терминологический словарь"

    @FindBy(xpath = "//*[@id='situationBusiness']//*[@class='box']")
    public WebElement sit_business_box;//квадратик модуля "Ситуации для бизнеса"

    @FindBy(xpath = "//*[@id='situationBusiness']//*[@class='module_ico situation']")
    public WebElement sit_business_module_icon;//иконка модуля "Ситуации для бизнеса"

    @FindBy(xpath = "//*[@id='situationBusiness']//*[text()='Ситуации']")
    public WebElement sit_business_module_name;//название модуля "Ситуации для бизнеса"

    @FindBy(xpath = "//*[@id='situationBusiness']//*[text()='для бизнеса']")
    public WebElement sit_business_text;//текст "Для бизнеса" модуля "Ситуации для бизнеса"

    @FindBy(xpath = "//*[@id='consultations']//*[@class='box']")
    public WebElement consultations_box;//квадратик модуля "Консультации личного эксперта"

    @FindBy(xpath = "//*[@id='consultations']//*[@class='module_ico consultation']")
    public WebElement consultations_module_icon;//иконка модуля "Консультации личного эксперта"

    @FindBy(xpath = "//*[@id='consultations']//*[text()='Консультации личного эксперта']")
    public WebElement cousultations_module_name;//название модуля "Консультации личного эксперта"

    @FindBy(xpath = "//*[@id='calendarAccountant']//*[@class='box']")
    public WebElement calend_account_box;//квадратик модуля "Календарь бухгалтера"

    @FindBy(xpath = "//*[@id='calendarAccountant']//*[@class='module_ico calendar-accountant']")
    public WebElement calend_account_module_icon;//иконка модуля "Календарь бухгалтера"

    @FindBy(xpath = "//*[@id='calendarAccountant']//*[text()='Календарь бухгалтера']")
    public WebElement calend_account_module_name;//название модуля "Календарь бухгалтера"

    @FindBy(xpath = "//*[@id='calendarLawyer']//*[@class='box']")
    public WebElement calend_lawyer_box;//квадратик модуля "Календарь юриста"

    @FindBy(xpath = "//*[@id='calendarLawyer']//*[@class='module_ico calendar-lawyer']")
    public WebElement calend_lawyer_module_icon;//иконка модуля

    @FindBy(xpath = "//*[@id='calendarLawyer']//*[text()='Календарь юриста']")
    public WebElement calend_lawyer_module_name;//название модуля "Календарь юриста"

    @FindBy(xpath = "//*[@id='taxBase']//*[@class='box']")
    public WebElement tax_base_box;// квадратик модуля "База налоговых знаний"

    @FindBy(xpath = "//*[@id='taxBase']//*[@class='module_ico tax-base']")
    public WebElement tax_base_module_icon;//иконка модуля "База налоговых знаний"

    @FindBy(xpath = "//*[@id='taxBase']//*[text()='База налоговых знаний']")
    public WebElement tax_base_module_name;//название модуля "База налоговых знаний"

    @FindBy(xpath = "//*[@id='cases_for_hr']//*[@class='box']")
    public WebElement  sit_hr_box;//квадратик модуля "Ситуации для кадровика"

    @FindBy(xpath = "//*[@id='cases_for_hr']//*[@class='module_ico situation']")
    public WebElement sit_hr_module_icon;//иконка модуля "Ситуации для кадровика"

    @FindBy(xpath = "//*[@id='cases_for_hr']//*[text()='для кадровика']")
    public WebElement sit_hr_module_text;//текст "Для кадровика" модуля "Ситуации для кадровика"

    @FindBy(xpath = "//*[@id='cases_for_hr']//*[text()='Ситуации']")
    public WebElement sit_hr_module_name;//название модуля "Ситуации для кадровика"

    @FindBy(xpath = "//*[@id='judicial_practice_for_acountant']//*[@class='box']")
    public WebElement jud_pract_account_box;//квадратик модуля "Судебная практика для бухгалтера"

    @FindBy(xpath = "//*[@id='judicial_practice_for_acountant']//*[@class='module_ico judicial_practice_for_acountant']")
    public WebElement jud_pract_account_icon;//иконка модуля "Судебная практика для бухгалтера"

    @FindBy(xpath = "//*[@id='judicial_practice_for_acountant']//*[text()='Судебная практика']")
    public WebElement jud_pract_account_name;//название модуля "Судебная практика для бухгалтера"

    @FindBy(xpath = "//*[@id='judicial_practice_for_acountant']//*[text()='для бухгалтера']")
    public WebElement jud_pract_account_text;//текст "Для бухгалтера" модуля "Судебная практика для бухгалтера"

    @FindBy(xpath = "//*[@id='calculator_vc']//*[@class='box']")
    public WebElement calc_vacation_box;//квадратик модуля "Калькулятор отпусков"

    @FindBy(xpath = "//*[@id='calculator_vc']//*[@class='module_ico calculator_vc']")
    public WebElement calc_vacation_module_icon;//иконка модуля "Калькулятор отпусков"

    @FindBy(xpath = "//*[@id='calculator_vc']//*[text()='Калькулятор отпусков']")
    public WebElement calc_vacation_module_name;//название модуля "Калькулятор отпусков"

    @FindBy(xpath = "//*[@id='calculator_zp']//*[@class='box']")
    public WebElement calc_salary_box;//квадратик модуля "Калькулятор индексации зарплаты"

    @FindBy(xpath = "//*[@id='calculator_zp']//*[@class='module_ico calculator_zp']")
    public WebElement calc_salary_module_icon;//иконка модуля "Калькулятор индексации зарплаты"

    @FindBy(xpath = "//*[@id='calculator_zp']//*[text()='Калькулятор индексации зарплаты']")
    public WebElement calc_salary_module_name;//название модуля "Калькулятор индексации зарплаты"

    @FindBy(xpath = "//*[@id='fines_calc']//*[@class='box']")
    public WebElement calc_fine_box;//квадратик модуля "Калькулятор штрафов"

    @FindBy(xpath = "//*[@id='fines_calc']//*[@class='module_ico fines_calculator']")
    public WebElement calc_fine_module_icon;//иконка модуля "Калькулятор штрафов"

    @FindBy(xpath = "//*[@id='fines_calc']//*[text()='Калькулятор штрафов']")
    public WebElement calc_fine_module_name;//название модуля "Калькулятор штрафов"

    @FindBy(xpath = "//*[@id='instructions']//*[@class='box']")//квадратик модуля "Инструкции и шаблоны для кадровика"
    public WebElement instruction_pattern_box;

    @FindBy(xpath = "//*[@id='instructions']//*[@class='module_ico instructions']")
    public WebElement instruction_pattern_module_icon;//иконка модуля "Инструкции и шаблоны для кадровика"

    @FindBy(xpath = "//*[@id='instructions']//*[text()='Инструкции и шаблоны']")
    public WebElement instruction_pattern_module_name;//название модуля "Инструкции и шаблоны для кадровика"

    @FindBy(xpath = "//*[@id='instructions']//*[text()='для кадровика']")
    public WebElement instruction_pattern_module_text;//текст "Для кадровика" модуля "Инструкции и шаблоны для кадровика"

    @FindBy(xpath = "//*[@id='directories']//*[@class='box']")
    public WebElement directories_box;//квадратик модуля "Справочники"

    @FindBy(xpath = "//*[@id='directories']//*[@class='module_ico directories']")
    public WebElement directories_module_icon;//иконка модуля "Справочники"

    @FindBy(xpath = "//*[@id='directories']//*[text()='Справочники']")
    public WebElement directories_module_name;//название модуля "Справочники"

    @FindBy(xpath = "//*[@id='wiring']//*[@class='box']")
    public WebElement buh_spend_box;//квадратик модуля "Бухгалтерские проводки"

    @FindBy(xpath = "//*[@id='wiring']//*[@class='module_ico wiring']")
    public WebElement buh_spend_icon;//иконка модуля "Бухгалтерские проводки"

    @FindBy(xpath = "//*[@id='wiring']//*[text()='Бухгалтерские проводки']")
    public WebElement buh_spend_name;

    @FindBy(xpath = "//lz-list/div[1]/div/a/div[4]")
    public WebElement link_in_list; //ссылка на документ в списке. Заголовок документа.

    @FindBy(xpath = "//a[@href='/']")
    public WebElement role_all_link; // ссылка "Всем"

    @FindBy(xpath = "//a[@href='/?role=chief']")
    public WebElement role_chief_link; // ссылка "Руководителю"

    @FindBy(xpath = "//a[@href='/?role=lawyer']")
    public WebElement role_lawyer_link; // ссылка "Юристу"

    @FindBy(xpath = "//a[@href='/?role=accountant']")
    public WebElement role_accountant_link; // ссылка "Бухгалтеру"

    @FindBy(xpath = "//a[@href='/?role=hr']")
    public WebElement role_hr_link; // ссылка "Кадровику"

    @FindBy(xpath = "//*[text()='Смотреть все модули']")
    public WebElement look_all_modules_box; // квадратик "Смотреть все модули"

}


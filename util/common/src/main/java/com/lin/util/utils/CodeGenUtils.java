package com.lin.util.utils;

import com.yyfly.common.util.DateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码自动生成工具类
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
public class CodeGenUtils {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(CodeGenUtils.class);

    /**
     * 工作区路径
     */
    private final static String WORKSPACE_PATH = ToolUtils.getProjectPath().replaceAll("/util/common/target/classes", "");

    /**
     * 模板目录
     */
    private final static String TEMPLATE_PATH = WORKSPACE_PATH + "/util/common/src/main/resources/template";
    /**
     * TEMPLATE_ENTITY_FILE
     */
    private final static String TEMPLATE_ENTITY_FILE = "EntityTemplate.ftl";
    /**
     * TEMPLATE_ENTITY_DTO_FILE
     */
    private final static String TEMPLATE_ENTITY_DTO_FILE = "EntityDTOTemplate.ftl";
    /**
     * TEMPLATE_DAO_FILE
     */
    private final static String TEMPLATE_DAO_FILE = "DaoTemplate.ftl";
    /**
     * TEMPLATE_SERVICE_FILE
     */
    private final static String TEMPLATE_SERVICE_FILE = "ServiceTemplate.ftl";
    /**
     * TEMPLATE_SERVICE_IMPL_FILE
     */
    private final static String TEMPLATE_SERVICE_IMPL_FILE = "ServiceImplTemplate.ftl";
    /**
     * TEMPLATE_CONTROLLER_FILE
     */
    private final static String TEMPLATE_CONTROLLER_FILE = "ControllerTemplate.ftl";
    /**
     * TEMPLATE_DTO_VALIDATOR_FILE
     */
    private final static String TEMPLATE_DTO_VALIDATOR_FILE = "DTOValidator.ftl";

    /**
     * 基本Package和Path
     */
    private final static String BASE_PATH = "src/main/java/com/seerbigdata/imms";
    /**
     * PROJECT_PACKAGE
     */
    private final static String PROJECT_PACKAGE = "com.seerbigdata.imms";
    /**
     * 项目数据库表名前缀
     */
    private final static String TABLE_NAME_PREFIX = "imms_";

    /**
     * author 信息
     */
    private final static String AUTHOR_NAME = "fangkuaiIt";
    /**
     * AUTHOR_EMAIL
     */
    private final static String AUTHOR_EMAIL = "fangkuaiIt@seerbigdata.com";
    /**
     * AUTHOR_CREATE_DATE
     */
    private final static String AUTHOR_CREATE_DATE = DateUtils.getNowTimeString(DateUtils.DATE_FORMAT_LOG_CONTENT);


    /**
     * ***************************************
     * 需要调整的变量
     * ***************************************
     */
    /**
     * module name (支持多层, 例如imms-finance.facility)*
     */
    private final static String MODULE_NAME = "task-scheduling.job-execute";

    /**
     * package name (支持多层, 例如finance.facility)
     */
    private final static String PACKAGE_NAME = "taskscheduling";

    /**
     * 实体类名称
     */
    private final static String ENTITY_NAME = "JobExecuteDeatil";
    /**
     * 实体类描述
     */
    private final static String ENTITY_DESCRIPTION = "执行记录明细";
    /**
     * 页面前缀
     */
    private final static String WEB_PREFIX = PACKAGE_NAME;
    /**
     * 页面前缀
     */
    private final static String WEB_MODULE_NAME = ENTITY_NAME.toLowerCase();

    /**
     * Cfg
     */
    private Configuration cfg;
    /**
     * Template
     */
    private Template template;
    /**
     * Data model
     */
    private Map<String, String> dataModel;

    public static void main(String[] args) throws IOException, TemplateException {
        CodeGenUtils codeUtil = new CodeGenUtils();
        //初始化需要的数据
        codeUtil.init();

        //生成Entity文件
        codeUtil.createEntityFile();

        //生成Entity DTO文件
        codeUtil.createEntityDTOFile();

        //生成Dao文件
        codeUtil.createDaoFile();

        //生成Service文件
        codeUtil.createServiceFile();

        //生成ServiceImpl文件
        codeUtil.createServiceImplFile();

//        //生成DTOValidator文件
//        codeUtil.createValidatorFile();
//
//        //生成Controller文件
//        codeUtil.createControllerFile();

    }

    /**
     * 把类名分割成数组
     *
     * @param classSimpleName the class simple name
     * @return the string [ ]
     * @author : yyfly / 2018-08-08
     */
    public static String splitClassSimpleName(String classSimpleName) {
        StringBuffer sb = new StringBuffer();
        if (classSimpleName != null) {
            for (int i = 0; i < classSimpleName.length(); i++) {
                char c = classSimpleName.charAt(i);
                if (Character.isUpperCase(c)) {
                    if (i == 0) {
                        sb.append(c);
                    } else {//如果是大写字母则加入分割线
                        sb.append("_").append(c);
                    }
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 初始化数据
     *
     * @throws IOException the io exception
     * @author : fangkuaiIt / 2019-01-08
     */
    public void init() throws IOException {
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        dataModel = new HashMap<String, String>();

        //封装替换的值
        dataModel.put("table_name", TABLE_NAME_PREFIX + splitClassSimpleName(ENTITY_NAME));
        dataModel.put("entity_name", ENTITY_NAME);
        dataModel.put("lower_entity_name", setFirstCharToLower(ENTITY_NAME));
        dataModel.put("entity_description", ENTITY_DESCRIPTION);

        //author信息
        dataModel.put("author_name", AUTHOR_NAME);
        dataModel.put("author_email", AUTHOR_EMAIL);
        dataModel.put("author_create_date", AUTHOR_CREATE_DATE);

        dataModel.put("entity_package", PROJECT_PACKAGE + "." + PACKAGE_NAME + ".entity");
        dataModel.put("entity_dto_package", PROJECT_PACKAGE + "." + PACKAGE_NAME + ".dto");
        dataModel.put("dao_package", PROJECT_PACKAGE + "." + PACKAGE_NAME + ".dao");
        dataModel.put("service_package", PROJECT_PACKAGE + "." + PACKAGE_NAME + ".service");
        dataModel.put("service_impl_package", PROJECT_PACKAGE + "." + PACKAGE_NAME + ".service.impl");
        dataModel.put("controller_package", PROJECT_PACKAGE + "." + PACKAGE_NAME + ".web");
        dataModel.put("dto_validator_package", PROJECT_PACKAGE + "." + PACKAGE_NAME + ".validator");

        dataModel.put("web_prefix", WEB_PREFIX);
        dataModel.put("web_module_name", WEB_MODULE_NAME);

        dataModel.put("package_name", PACKAGE_NAME);
    }

    /**
     * 生成Entity文件
     *
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     * @author : fangkuaiIt / 2019-01-08
     */
    public void createEntityFile() throws IOException, TemplateException {

        //Entity目录
        String filedDirectory = WORKSPACE_PATH + "/" + MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/entity";

        File directory = new File(filedDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        //Entity路径
        String filePath = directory + "/" + ENTITY_NAME + ".java";
        logger.info("Entity文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            logger.error("Entity文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_ENTITY_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("Entity文件生成完成！");
    }

    /**
     * 生成EntityDTO文件
     *
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     * @author : fangkuaiIt / 2019-01-08
     */
    public void createEntityDTOFile() throws IOException, TemplateException {

        //Entity目录
        String filedDirectory = WORKSPACE_PATH + "/" + MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/dto";

        File directory = new File(filedDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        //Entity路径
        String filePath = directory + "/" + ENTITY_NAME + "DTO.java";
        logger.info("Entity DTO文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            logger.error("Entity DTO文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_ENTITY_DTO_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("Entity DTO文件生成完成！");
    }

    /**
     * 生成Dao文件
     *
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     * @author : fangkuaiIt / 2019-01-08
     */
    public void createDaoFile() throws IOException, TemplateException {

        //Dao目录
        String filedDirectory = WORKSPACE_PATH + "/" + MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/dao";

        File directory = new File(filedDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        //Dao路径
        String filePath = directory + "/" + ENTITY_NAME + "Dao.java";
        logger.info("Dao文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            logger.error("Dao文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_DAO_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("Dao文件生成完成！");
    }


    /**
     * 生成Service文件
     *
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     * @author : fangkuaiIt / 2019-01-08
     */
    public void createServiceFile() throws IOException, TemplateException {

        //Service目录
        String filedDirectory = WORKSPACE_PATH + "/" + MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/service";

        File directory = new File(filedDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        //Service路径
        String filePath = filedDirectory + "/" + ENTITY_NAME + "Service.java";
        logger.info("Service文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            logger.error("Service文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_SERVICE_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("Service文件生成完成！");
    }


    /**
     * 生成ServiceImpl文件
     *
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     * @author : fangkuaiIt / 2019-01-08
     */
    public void createServiceImplFile() throws IOException, TemplateException {

        //ServiceImpl目录
        String filedDirectory = WORKSPACE_PATH + "/" + MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/service/impl";

        File directory = new File(filedDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        //ServiceImpl路径
        String filePath = directory + "/" + ENTITY_NAME + "ServiceImpl.java";
        logger.info("ServiceImpl文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            logger.error("ServiceImpl文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_SERVICE_IMPL_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("ServiceImpl文件生成完成！");
    }


    /**
     * 生成Controller文件
     *
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     * @author : fangkuaiIt / 2019-01-08
     */
    public void createControllerFile() throws IOException, TemplateException {

        //Controller目录
        String filedDirectory = WORKSPACE_PATH + "/" + MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/web";

        File directory = new File(filedDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        //Controller路径
        String filePath = directory + "/" + ENTITY_NAME + "Controller.java";
        logger.info("Controller文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            logger.error("Controller文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_CONTROLLER_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("Controller文件生成完成！");
    }


    /**
     * 生成 dto validator文件
     *
     * @throws IOException       the io exception
     * @throws TemplateException the template exception
     * @author : fangkuaiIt / 2019-01-08
     */
    public void createValidatorFile() throws IOException, TemplateException {

        //Validator目录
        String filedDirectory = WORKSPACE_PATH + "/" + MODULE_NAME.replaceAll("\\.", "/") + "/" + BASE_PATH + "/"
                + PACKAGE_NAME.replaceAll("\\.", "/") + "/validator";

        File directory = new File(filedDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        //DTOValidator路径
        String filePath = directory + "/" + ENTITY_NAME + "DTOValidator.java";
        logger.info("DTOValidator文件路径为: " + filePath);

        //文件
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            logger.error("DTOValidator文件已经存在,不自动生成！");
            return;
        }

        template = cfg.getTemplate(TEMPLATE_DTO_VALIDATOR_FILE);
        FileWriter writer = new FileWriter(file);
        template.process(dataModel, writer);
        writer.close();
        logger.info("DTOValidator文件生成完成！");
    }



    /**
     * 字符串首字母小写
     *
     * @param str the str
     * @return first char to lower
     * @author : fangkuaiIt
    * @Date : 2019-01-08
     */
    private String setFirstCharToLower(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

}

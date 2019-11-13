package club.gclmit.chaos.music;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 代码生成器
 * @author: 孤城落寞
 * @see: 2019-08-13 11:04
 * @see: com.petkit.certificate.AutoGeneratorCodeTests
 */
@SpringBootTest
public class AutoGeneratorCodeTests {

    @Autowired
    private DataSource dataSource;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 查看数据源情况
     * @throws SQLException
     */
    @Test
    public void getDataSource() throws SQLException {
        logger.info("\n 数据源类型：{} \n 数据源链接状态：{}",dataSource.getClass(),dataSource.getConnection());
    }

    /**
     * mybatis-plus 代码生成配置
     *
     *  配置详情：http://mp.baomidou.com/#/generate-code
     */
    @Test
    public void autoGenerator(){
//      1. 全局配置
        GlobalConfig config=new GlobalConfig();
//       是否开启AR 模式
        String projectPath = System.getProperty("user.dir");
        config.setActiveRecord(false)
//            生成路径  这里我采用的是绝对路径  其他人可以采用相对路径试试
                .setOutputDir(projectPath+"/src/main/java")
//            文件覆盖
                .setFileOverride(true)
//				是否打开生成的文件
                .setOpen(false)
//              开启 baseResultMap
                .setBaseResultMap(true)
//              主键生成策略
                .setIdType(IdType.AUTO)
//				开启 Swagger2
                .setSwagger2(false)
//               作者
                .setAuthor("孤城落寞")
//             设置生成的接口的名字 首字母是否为I 一下类似
                .setServiceName("%sService");

//      2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
//      设置数据库类型
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/music?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true")
                .setUsername("root")
                .setPassword("root");

//      3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
//       全局大写命名
        stConfig.setCapitalMode(true)
//               数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
//               表名前缀
                .setTablePrefix("music_")
//               lombok
                .setEntityLombokModel(true)
//               RestController 风格
                .setRestControllerStyle(true)
                // 驼峰转字符
                .setControllerMappingHyphenStyle(true)
//               用于生成的表 传入的值是同一个集合
                .setInclude("music_pic","music_singer","music_song","music_song_quality");

//      4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
//      设置全局包名
        pkConfig.setParent("club.gclmit.chaos.music")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("model.pojo");

        //5. 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 如果模板引擎是 beetl
         String templatePath = "/templates/mapper.xml.btl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);


        //6. 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);


//      7. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setPackageInfo(pkConfig)
                .setStrategy(stConfig)
                .setCfg(injectionConfig)
                .setTemplate(templateConfig)
                // freemarker 模板引擎
//                .setTemplateEngine(new FreemarkerTemplateEngine());
                // beetl 模板引擎
                .setTemplateEngine(new BeetlTemplateEngine());


//      8. 执行
        ag.execute();
    }

}


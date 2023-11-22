package com.simios.simioapp.servicios;

import com.simios.simioapp.comunes.beans.ResultadoMigracionBean;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.seguridad.UsuarioSessionImpl;
import com.simios.simioapp.comunes.utiles.JSONUtil;
import com.simios.simioapp.servicios.base.MigracionTestBase;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.logging.Logger;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MigracionDBFCaso02Test extends MigracionTestBase {

    private static Logger log = Logger.getLogger(MigracionDBFCaso02Test.class.getName());

    // 680	0209180000	GOBIERNO REGIONAL PIURA
    private static final Integer ENTIDAD_ID = 680;

    @Autowired
    private MigracionService migracionService;

    @Test
    @Rollback
    @Transactional
    @Ignore
    public void migrar01Locales() throws Exception {

        InputStream inputStream = new FileInputStream(getFileFromResources("dbfs/caso02/LOCALES.DBF"));

        UsuarioSession usuarioSession = UsuarioSessionImpl.getDefaultTestUser(String.valueOf(ENTIDAD_ID));

        ResultadoMigracionBean result = migracionService.migrarDBFLocales(inputStream, usuarioSession, ENTIDAD_ID, true);

        log.info(JSONUtil.toJSON(result, true));
    }

    @Test
    @Rollback
    @Transactional
    @Ignore
    public void migrar02Areas() throws Exception {

        InputStream inputStream = new FileInputStream(getFileFromResources("dbfs/caso02/AREAS.DBF"));

        UsuarioSession usuarioSession = UsuarioSessionImpl.getDefaultTestUser(String.valueOf(ENTIDAD_ID));

        ResultadoMigracionBean result = migracionService.migrarDBFAreas(inputStream, usuarioSession, ENTIDAD_ID, true);

        log.info(JSONUtil.toJSON(result, true));
    }

    @Test
    @Rollback
    @Transactional
    @Ignore
    public void migrar03Oficinas() throws Exception {


        InputStream inputStream = new FileInputStream(getFileFromResources("dbfs/caso02/OFICINAS.DBF"));

        UsuarioSession usuarioSession = UsuarioSessionImpl.getDefaultTestUser(String.valueOf(ENTIDAD_ID));

        ResultadoMigracionBean result = migracionService.migrarDBFOficinas(inputStream, usuarioSession, ENTIDAD_ID, true);

        log.info(JSONUtil.toJSON(result, true));

    }

    @Test
    @Rollback
    @Transactional
    @Ignore
    public void migrar04Empleados() throws Exception {

        InputStream inputStream = new FileInputStream(getFileFromResources("dbfs/caso02/TEMPLEA0.DBF"));

        UsuarioSession usuarioSession = UsuarioSessionImpl.getDefaultTestUser(String.valueOf(ENTIDAD_ID));

        ResultadoMigracionBean result = migracionService.migrarDBFEmpleados(inputStream, usuarioSession, ENTIDAD_ID, true);

        log.info(JSONUtil.toJSON(result, true));
    }

    @Test
    @Rollback
    @Transactional
    @Ignore
    public void migrar05Bienes() throws Exception {

        InputStream inputStream = new FileInputStream(getFileFromResources("dbfs/caso02/MBIENES0.DBF"));

        UsuarioSession usuarioSession = UsuarioSessionImpl.getDefaultTestUser(String.valueOf(ENTIDAD_ID));

        ResultadoMigracionBean result = migracionService.migrarDBFBienes(inputStream, usuarioSession, ENTIDAD_ID, true);

        log.info(JSONUtil.toJSON(result, true));
    }

//    @BeforeClass
//    public static void setLoggerLog4j2() throws MalformedURLException {
//        System.setProperty("log4j.configurationFile", "notused-log4j2-test-notused.xml");
//        log = LogManager.getLogger(MigracionCaso01Test.class.getName());
//    }

    @BeforeClass
    public static void setLoggerJavaUtilLogging() throws MalformedURLException {
        System.setProperty("java.util.logging.config.file", "logging-test.properties");
        log = Logger.getLogger(MigracionDBFCaso02Test.class.getName());
    }


}

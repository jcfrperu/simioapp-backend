package com.simios.simioapp.servicios;

import com.simios.simioapp.comunes.beans.ResultadoMigracionSBNExcelBean;
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
public class MigracionEXCELCaso01Test extends MigracionTestBase {

    private static Logger log = Logger.getLogger(MigracionEXCELCaso01Test.class.getName());

    // 553	0209181500	DIRECCION REGIONAL DE VIVIENDA CONSTRUCCION Y SANEAMIENTO PIURA
    private static final Integer ENTIDAD_ID = 553;

    @Autowired
    private MigracionService migracionService;

    @Test
    @Rollback
    @Transactional
    @Ignore
    public void migrar01Bienes() throws Exception {

        InputStream inputStream = new FileInputStream(getFileFromResources("excel/caso01/Inventario2017.xlsm"));

        UsuarioSession usuarioSession = UsuarioSessionImpl.getDefaultTestUser(String.valueOf(ENTIDAD_ID));

        ResultadoMigracionSBNExcelBean result = migracionService.migrarSBNExcelV7(inputStream, usuarioSession, ENTIDAD_ID, true);

        log.info(JSONUtil.toJSON(result, true));
    }

    @BeforeClass
    public static void setLoggerJavaUtilLogging() throws MalformedURLException {
        System.setProperty("java.util.logging.config.file", "logging-test.properties");
        log = Logger.getLogger(MigracionEXCELCaso01Test.class.getName());
    }

}

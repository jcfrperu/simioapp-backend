package com.simios.simioapp.controladores.base;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileParser {

    public static void main(String[] args) throws Exception {

//		new FileParser().parse();
        new FileParser().unparse();
    }

    private void unparse() throws IOException {

        byte[] dataB64 = FileUtils.readFileToByteArray(new File("/home/juaneco/jcfr/temp/archivo.txt"));

        byte[] data = Base64.decodeBase64(dataB64);

        IOUtils.write(data, new FileOutputStream(new File("/home/juaneco/jcfr/temp/archivo2.docx")));

        System.out.println("fin del programa");
    }

//    private void parse() throws IOException {
//
//        byte[] data = FileUtils.readFileToByteArray(new File("C:/JC/desarrollo/ws/migrador/src/main/resources/info.docx.zip"));
//
//        byte[] dataB64 = Base64.encodeBase64String(data);
//
//        IOUtils.write(dataB64, new FileOutputStream(new File("C:/JC/desarrollo/ws/migrador/src/main/resources/info.docx.out")));
//
//        System.out.println("fin del programa");
//    }


}

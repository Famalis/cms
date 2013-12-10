/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import dto.UserDTO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Tutaj przetrzymywane będą różne przydatne metody wykorzystywane w więcej niż
 * jedenj klasie.
 *
 * @author Sergio
 */
public abstract class Utils {

    /**
     * Metoda zamieniająca JSON w postaci Stringa na object Javovy; zwracany
     * obiekt wciąż trzeba zrzutować do konkretnego obiektu.
     * @param json
     * @param javaClass
     * @return 
     */
    public static Object convertJSONStringToObject(String json, Class javaClass) {       
        ObjectMapper mapper = new ObjectMapper();
        Object o = null;
        try {
            o = mapper.readValue(json, javaClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return o;
    }
    /**
     * Metoda zamieniająca JSON w postaci Stringa (z nazwą obiektu na początku)
     * na object Javovy; zwracany
     * obiekt wciąż trzeba zrzutować do konkretnego obiektu.
     *
     * @param json - JSON w formie Stringa
     * @param objectName - Nazwa zmiennej String w której zawarty jest JSON
     * (innymi słowy nazwa zmiennej ktora jest podana jako pierwszy paramter)
     * @param javaClass - Klasa na podstawie której ma zostać stworzony zwracany
     * obiekt
     * @return
     */
    public static Object convertJSONStringToObject(String json, String objectName, Class javaClass) {
        String actualJsonString = json.substring(
                ("'" + objectName + "':'").length(), json.length() - 1);
        ObjectMapper mapper = new ObjectMapper();
        Object o = null;
        try {
            o = mapper.readValue(actualJsonString, javaClass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return o;
    }

    public static String convertObjectToJSON(Object obj) {
        System.out.println("DEBUG: requestJsons");
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data;
        try {
            mapper.writeValue(out, obj);
            data = out.toByteArray();
            return new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertObjectListToJSON(List<?> list) {
        System.out.println("DEBUG: requestJsons");
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data;
        try {
            mapper.writeValue(out, list);
            data = out.toByteArray();
            return new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertOMapToJSON(Map<?, ?> map) {
        System.out.println("DEBUG: requestJsons");
        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] data;
        try {
            mapper.writeValue(out, map);
            data = out.toByteArray();
            return new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void test(File file) {
        try {
            Document document = new Document();
            XMLWorkerHelper xmlWorker = XMLWorkerHelper.getInstance();
            PdfWriter pdfWriter = PdfWriter.getInstance(document,
                    new FileOutputStream("pdf.pdf"));
            xmlWorker.parseXHtml(pdfWriter, document,
                    new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda przygotowuje odpowiedź serwera pod kątem pobrania pliku
     * @param hash
     * @param fileName
     * @param mimeType
     * @param response
     */
    public static void download(String hash, String fileName, String mimeType, HttpServletResponse response) {
        File file;
        try {
            byte[] barr = HexConverter.toBytesFromHex(hash);
            file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(barr);
            fos.close();

            FileInputStream fis = new FileInputStream(file);
            response.setContentType(mimeType);

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    fileName);
            response.setHeader(headerKey, headerValue);

            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException io) {
            System.err.println("Download IO error");
            io.printStackTrace();
        }
    }
    
    public static ResponseEntity<String> createResponseEntity(HttpSession session, Map<String, Object> initData){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        UserDTO user = (UserDTO)(session.getAttribute("user"));
        initData.put("privileges", user.getPrivilegeKeyCodes());
        
        System.out.println("SEND: "+Utils.convertOMapToJSON(initData));
        
        return new ResponseEntity<String>(convertOMapToJSON(initData), responseHeaders, HttpStatus.OK);
    }
}

package com.github.lobedan.jira.stub;

import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by zambezi on 04.12.14.
 */
public class JiraJunitUtil {

    public static String getFileContent(String fileName) throws Exception {
        InputStream in = new ClassPathResource(fileName).getInputStream();
        char[] tmp = new char[4096];
        StringBuilder b = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(in, Charset.forName("utf-8"))) {
            while (true) {
                int len = reader.read(tmp);
                if (len < 0) {
                    break;
                }
                b.append(tmp, 0, len);
            }
            reader.close();
        }
        in.close();
        return b.toString();
    }

    public static MockRestServiceServer mockServer(RestTemplate restTemplate) {
        return MockRestServiceServer.createServer(restTemplate);
    }

}

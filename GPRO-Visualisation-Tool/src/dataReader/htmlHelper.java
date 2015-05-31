package dataReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/* File to handle the Web Request to download a html page */
public class htmlHelper {
	public static final String domainUrl = "http://gpro.net/gb/";
	public static String downloadWebPage(String url)
	{
		String htmlStream = new String();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"))) {
		    for (String line; (line = reader.readLine()) != null;) {
		        htmlStream += line;
		    }
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return htmlStream;
	}
}

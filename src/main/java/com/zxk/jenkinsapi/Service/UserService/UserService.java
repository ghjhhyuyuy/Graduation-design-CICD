package com.zxk.jenkinsapi.Service.UserService;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;

public interface UserService {


    String createJob() throws SAXException, ParserConfigurationException, IOException, TransformerException, ParseException;
}

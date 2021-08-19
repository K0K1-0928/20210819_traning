package com.example.demo.controller;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Controller
public class FormController {
    @RequestMapping("/input")
    public ModelAndView input(ModelAndView mav) {
        mav.setViewName("input");
        return mav;
    }

    @RequestMapping("/xxe")
    public ModelAndView xxe(ModelAndView mav) {
        mav.setViewName("inputXxe");
        return mav;
    }

    @PostMapping("/output")
    public ModelAndView output(ModelAndView mav, HttpServletRequest request, HttpServletResponse response) throws SAXException, IOException, ParserConfigurationException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        String xml = request.getParameter("xml");
        Document doc = builder.parse(new InputSource(new StringReader(xml)));
        
        String name = doc.getElementsByTagName("name").item(0).getTextContent();
        String address = doc.getElementsByTagName("address").item(0).getTextContent();
        
        mav.addObject("name", name);
        mav.addObject("address", address);
        mav.setViewName("output");
        return mav;
    }
}

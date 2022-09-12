package com.practice.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JacksonXmlRootElement(localName = "ValType")
public class ValuteType {

    @JacksonXmlProperty(isAttribute = true, localName = "Type")
    private String type;
    @JacksonXmlElementWrapper(useWrapping = false, localName = "Valute")
    @JacksonXmlProperty(localName = "Valute")
    private List<Valute> valutes;

}

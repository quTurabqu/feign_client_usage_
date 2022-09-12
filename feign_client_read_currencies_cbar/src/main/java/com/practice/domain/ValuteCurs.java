package com.practice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "ValCurs")
public class ValuteCurs {

    @JacksonXmlProperty(isAttribute = true, localName = "Date")
    private String date;
    @JacksonXmlProperty(isAttribute = true, localName = "Name")
    private String name;
    @JacksonXmlProperty(isAttribute = true, localName = "Description")
    private String description;
    @JacksonXmlElementWrapper(useWrapping = false, localName = "ValType")
    @JacksonXmlProperty(localName = "ValType")
    private List<ValuteType> valuteTypes;

}

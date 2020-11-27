package com.gatech.cs6440.drugabuse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ApiModel(description = "hold individuals chat history")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Id of Category")
    private Integer chatId;
    private String message;

    //@JsonIgnore
    //@ManyToOne(targetEntity = Category.class, cascade = CascadeType.ALL)
    private Integer category;

    public Chat() {

    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", groupName='" + category + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

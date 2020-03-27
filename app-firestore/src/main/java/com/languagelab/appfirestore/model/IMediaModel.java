package com.languagelab.appfirestore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IMediaModel {
    String id;
    String title; /* The title is the Document ID, if you change the ID you will alter/create another document */
    String src;
    String type;
    String script;
    String customDefinitions;

    public String toString() { return this.title; }
}

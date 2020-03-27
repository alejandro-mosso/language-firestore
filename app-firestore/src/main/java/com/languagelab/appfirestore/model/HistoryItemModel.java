package com.languagelab.appfirestore.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "This entity describes a document in the Database.")
public class HistoryItemModel {
    @Schema(description = "Document ID. The value of this field is automatically assigned.",
            required = true)
    String id;
    @Schema(description = "Document title. Database will associate the title of the document to the ID; which means," +
            " if you change the title of the item, then you will be creating a new document in DB.",
            required = true)
    String title; /* The title is the Document ID, if you change the ID you will alter/create another document */
    @Schema(description = "Indicates the complexity of this item: basic, intermediate, advanced.",
            example = "basic", required = true)
    String level;
    @Schema(description = "Provides a description of the Story.",
            required = true)
    String description;
    @Schema(description = "Indicates the language you want to learn (in lower case).",
            example = "english", required = true)
    String sourceLanguage; /* The language you want to learn */
    @Schema(description = "Indicates the language you already speak (in lower case).",
            example = "español", required = true)
    String targetLanguage; /* The language you speak */
    @Schema(description = "Abbreviation of the language you want to learn.",
            example = "en", required = true)
    String sourceLan; /* The language you want to learn */
    @Schema(description = "Abbreviation of the language you already speak.",
            example = "es", required = true)
    String targetLan; /* The language you speak */

    public String toString() { return this.title; }
}

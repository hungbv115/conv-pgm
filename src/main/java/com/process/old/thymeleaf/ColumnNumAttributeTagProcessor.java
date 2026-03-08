package com.process.old.thymeleaf;

/**
 * Attribute processor for th:columnNum which renders a plain HTML attribute columnNum.
 */
public class ColumnNumAttributeTagProcessor extends GenericLineColumnAttributeTagProcessor {

    public static final String ATTR_NAME = "columnNum";

    public ColumnNumAttributeTagProcessor(final String dialectPrefix) {
        super(dialectPrefix, ATTR_NAME, "columnNum");
    }
}



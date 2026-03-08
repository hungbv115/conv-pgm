package com.process.old.thymeleaf;

/**
 * Attribute processor for th:lineNum which renders a plain HTML attribute lineNum.
 */
public class LineNumAttributeTagProcessor extends GenericLineColumnAttributeTagProcessor {

    public static final String ATTR_NAME = "lineNum";

    public LineNumAttributeTagProcessor(final String dialectPrefix) {
        super(dialectPrefix, ATTR_NAME, "lineNum");
    }
}



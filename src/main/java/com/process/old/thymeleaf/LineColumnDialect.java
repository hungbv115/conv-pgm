package com.process.old.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

/**
 * Custom Thymeleaf dialect that exposes th:lineNum and th:columnNum attributes,
 * which are rendered as plain HTML attributes lineNum / columnNum.
 */
public class LineColumnDialect extends AbstractProcessorDialect {

    public static final String DIALECT_NAME = "Line & Column Dialect";
    public static final String DIALECT_PREFIX = "th"; // use the standard 'th' prefix

    public LineColumnDialect() {
        super(DIALECT_NAME, DIALECT_PREFIX, 1000);
    }

    @Override
    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();

        processors.add(new LineNumAttributeTagProcessor(dialectPrefix));
        processors.add(new ColumnNumAttributeTagProcessor(dialectPrefix));

        return processors;
    }
}



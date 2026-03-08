package com.process.old.thymeleaf;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Generic attribute processor that evaluates a Thymeleaf expression and
 * writes the result into a plain HTML attribute (e.g. lineNum / columnNum).
 */
public class GenericLineColumnAttributeTagProcessor extends AbstractAttributeTagProcessor {

    private static final int PRECEDENCE = 1000;

    /**
     * The real HTML attribute name to be written to the tag, e.g. "lineNum" or "columnNum".
     */
    private final String targetAttributeName;

    public GenericLineColumnAttributeTagProcessor(final String dialectPrefix,
                                                  final String attributeName,
                                                  final String targetAttributeName) {
        super(
                TemplateMode.HTML,     // Process HTML mode
                dialectPrefix,         // Dialect prefix, e.g. "th"
                null,                  // No tag name restriction
                false,                 // No prefix applied to tag name
                attributeName,         // Attribute name without prefix (e.g. "lineNum")
                true,                  // Remove the attribute after processing
                PRECEDENCE,            // Precedence
                true                   // Apply dialect prefix to attribute name
        );
        this.targetAttributeName = targetAttributeName;
    }

    @Override
    protected void doProcess(final ITemplateContext context,
                             final IProcessableElementTag tag,
                             final AttributeName attributeName,
                             final String attributeValue,
                             final IElementTagStructureHandler structureHandler) {

        if (attributeValue == null) {
            structureHandler.setAttribute(this.targetAttributeName, "");
            return;
        }

        Object result;

        // Cố gắng parse như biểu thức Thymeleaf (ví dụ: ${iterStat.count} hoặc 1).
        // Nếu parse lỗi, fallback dùng nguyên giá trị chuỗi literal.
        try {
            final IStandardExpressionParser parser =
                    StandardExpressions.getExpressionParser(context.getConfiguration());

            final IStandardExpression expression = parser.parseExpression(context, attributeValue);
            result = expression.execute(context);
        } catch (Exception ex) {
            result = attributeValue;
        }

        final String value = result == null ? "" : result.toString();

        // Set the plain HTML attribute (without prefix), e.g. lineNum="10"
        structureHandler.setAttribute(this.targetAttributeName, value);
        // The original th:lineNum / th:columnNum attribute is removed automatically
    }
}



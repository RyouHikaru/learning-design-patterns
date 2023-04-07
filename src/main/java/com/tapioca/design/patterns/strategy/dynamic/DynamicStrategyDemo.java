package com.tapioca.design.patterns.strategy.dynamic;

import java.util.List;

/**
 * Strategy - allows you to define the different parts of an algorithm
 * that you can call.

 * This example implements Dynamic Strategy.
 */
enum OutputFormat {
    MARKDOWN, HTML
}
interface ListStrategy {
    default void start(StringBuilder sb) {
    }

    void addListItem(StringBuilder stringBuilder, String item);

    default void end(StringBuilder sb) {
    }
}

class MarkdownListStrategy implements ListStrategy {
    @Override
    public void addListItem(StringBuilder stringBuilder, String item) {
        stringBuilder.append(" * ").append(item)
                .append(System.lineSeparator());
    }
}

class HtmlListStrategy implements ListStrategy {
    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder stringBuilder, String item) {
        stringBuilder.append("  <li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}

class TextProcessor {
    private StringBuilder sb = new StringBuilder();
    private ListStrategy listStrategy;

    public TextProcessor(OutputFormat format) {
        setOutputFormat(format);
    }

    public void setOutputFormat(OutputFormat format) {
        switch (format) {
            case MARKDOWN -> listStrategy = new MarkdownListStrategy();
            case HTML -> listStrategy = new HtmlListStrategy();
        }
    }

    // The skeleton algorithm is here
    public void appendList(List<String> items) {
        listStrategy.start(sb);
        for (String item : items)
            listStrategy.addListItem(sb, item);
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

public class DynamicStrategyDemo {
    public static void main(String[] args) {
        TextProcessor tp = new TextProcessor(OutputFormat.MARKDOWN);
        tp.appendList(List.of("lorem", "ipsum", "dolor"));
        System.out.println(tp);

        tp.clear();
        tp.setOutputFormat(OutputFormat.HTML);
        tp.appendList(List.of("et", "solom", "marsi"));
        System.out.println(tp);
    }
}

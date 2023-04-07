package com.tapioca.design.patterns.interpreter.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parsing
 */
interface Element {
    int eval();
}

class Integer implements Element {
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation implements Element {
    public enum Type {
        ADDITION, SUBTRACTION
    }

    public Type type;
    public Element left, right;

    @Override
    public int eval() {
        switch (type) {
            case ADDITION -> {
                return left.eval() + right.eval();
            }
            case SUBTRACTION -> {
                return left.eval() - right.eval();
            }
            default -> {
                return 0;
            }
        }
    }
}

class Token {
    public enum Type {
        INTEGER, PLUS, MINUS, LPAREN, RPAREN
    }

    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

public class ParserDemo {
    static List<Token> lex(String input) {
        ArrayList<Token> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '+' -> result.add(new Token(Token.Type.PLUS, "+"));
                case '-' -> result.add(new Token(Token.Type.MINUS, "-"));
                case '(' -> result.add(new Token(Token.Type.LPAREN, "("));
                case ')' -> result.add(new Token(Token.Type.RPAREN, ")"));
                default -> {
                    StringBuilder sb = new StringBuilder("" + input.charAt(i));
                    for (int j = i + 1; j < input.length(); j++) {
                        if (Character.isDigit(input.charAt(j))) {
                            sb.append(input.charAt(j));
                            i++;
                        } else {
                            result.add(new Token(Token.Type.INTEGER, sb.toString()));
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }

    static Element parse(List<Token> tokens) {
        BinaryOperation result = new BinaryOperation();
        boolean haveLHS = false;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            switch (token.type) {
                case INTEGER -> {
                    Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
                    if (!haveLHS) {
                        result.left = integer;
                        haveLHS = true;
                    } else result.right = integer;
                }
                case PLUS -> {
                    result.type = BinaryOperation.Type.ADDITION;
                }
                case MINUS -> {
                    result.type = BinaryOperation.Type.SUBTRACTION;
                }
                case LPAREN -> {
                    int j = i; // Points to the location of right parenthesis
                    for (; j < tokens.size(); j++)
                        if (tokens.get(j).type == Token.Type.RPAREN)
                            break;
                    List<Token> subexpression = tokens.stream()
                            .skip(i + 1)
                            .limit(j - i - 1)
                            .collect(Collectors.toList());
                    Element element = parse(subexpression);
                    if (!haveLHS) {
                        result.left = element;
                        haveLHS = true;
                    } else result.right = element;
                    i = j;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "(13+4)-(12+1)";
        List<Token> tokens = lex(input);
        System.out.println(tokens.stream()
                .map(t -> t.toString())
                .collect(Collectors.joining("\t")));

        Element parsed = parse(tokens);
        System.out.println(input + " = " + parsed.eval());
    }
}

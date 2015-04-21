package com.glcaier.lexparser.pojo;

import com.glcaier.lexparser.LexParser;

/**
 * Created by IntelliJ IDEA on 2015-04-20 21:25.
 * Author:  Glacier (RenLixiang), OurHom.759@gmail.com
 * Company: Class 1204 of Computer Science and Technology
 */
public class Symbol {   //符号类   说名一个符号的内容、类型、编号
    public String symbol;
    public int type, number;

    public static int border_num = 0, operator_num = 0, keywords_num = 0, variable_num = 0, variable_const_num = 0, digital_num = 0;

    public Symbol( String symbol, int type ) {
        this.symbol = symbol;
        this.type = type;

        switch (this.type) {
            case LexParser.TYPE_BORDER:
                border_num ++;
                this.number = border_num;
                break;
            case LexParser.TYPE_KEYWORDS:
                keywords_num ++;
                this.number = keywords_num;
                break;
            case LexParser.TYPE_OPERATOR:
                operator_num ++;
                this.number = operator_num;
                break;
            case LexParser.TYPE_VARIABLE:
                variable_num ++;
                this.number = variable_num;
                break;
            case LexParser.TYPE_CONST_VARIABLE:
                variable_const_num ++;
                this.number = variable_const_num;
                break;
            case LexParser.TYPE_DIGITAL:
                digital_num ++;
                this.number = digital_num;
                break;
            case LexParser.TYPE_NULL:
                this.number = 0;
                break;
            default:
                System.out.println("初始有误");
                System.exit(1);
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public static int getBorder_num() {
        return border_num;
    }

    public static void setBorder_num(int border_num) {
        Symbol.border_num = border_num;
    }

    public static int getOperator_num() {
        return operator_num;
    }

    public static void setOperator_num(int operator_num) {
        Symbol.operator_num = operator_num;
    }

    public static int getKeywords_num() {
        return keywords_num;
    }

    public static void setKeywords_num(int keywords_num) {
        Symbol.keywords_num = keywords_num;
    }

    public static int getVariable_num() {
        return variable_num;
    }

    public static void setVariable_num(int variable_num) {
        Symbol.variable_num = variable_num;
    }

    @Override
    public String toString() {
        String returnStr = null;
        switch( this.type ) {
            case LexParser.TYPE_BORDER:
                returnStr = "\tBORDER  \t\t" + number + "\t" + symbol;
                break;
            case LexParser.TYPE_OPERATOR:
                returnStr = "\tOPERATOR\t\t" + number + "\t" + symbol;
                break;
            case LexParser.TYPE_KEYWORDS:
                returnStr = "\tKEYWORDS\t\t" + number + "\t" + symbol;
                break;
            case LexParser.TYPE_VARIABLE:
                returnStr = "\tVARIABLE\t\t" + number + "\t" + symbol;
                break;
            case LexParser.TYPE_DIGITAL:
                returnStr = "\tDIGITAL  \t\t" + number + "\t" + symbol;
                break;
            case LexParser.TYPE_CONST_VARIABLE:
                returnStr = "\tCONSE_VARIABLE\t" + number + "\t" + symbol;
                break;
        }
        return returnStr;
    }
}

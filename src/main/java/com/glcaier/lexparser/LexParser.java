package com.glcaier.lexparser;

import com.glcaier.lexparser.pojo.Symbol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by IntelliJ IDEA on 2015-04-20 21:05.
 * Author:  Glacier (RenLixiang), OurHom.759@gmail.com
 * Company: Class 1204 of Computer Science and Technology
 */
public class LexParser {

    public static final int TYPE_NULL = 000;        //表示啥都不是
    public static final int TYPE_BORDER = 001;      //表示分隔符
    public static final int TYPE_OPERATOR = 002;    //表示运算符
    public static final int TYPE_KEYWORDS = 003;    //表示关键字
    public static final int TYPE_VARIABLE = 004;    //表示用户自定义变量名

    private String data = null;
    private StringBuffer symbolBuffer = new StringBuffer();

    public static final Set<Symbol> symbolSet = new HashSet<Symbol>();
    {
        symbolSet.add(new Symbol("(", TYPE_BORDER));
        symbolSet.add(new Symbol(")", TYPE_BORDER));
        symbolSet.add(new Symbol("[", TYPE_BORDER));
        symbolSet.add(new Symbol("]", TYPE_BORDER));
        symbolSet.add(new Symbol("{", TYPE_BORDER));
        symbolSet.add(new Symbol("}", TYPE_BORDER));
        symbolSet.add(new Symbol(";", TYPE_BORDER));
        symbolSet.add(new Symbol(",", TYPE_BORDER));
        symbolSet.add(new Symbol(":", TYPE_BORDER));

        symbolSet.add(new Symbol("+", TYPE_OPERATOR));
        symbolSet.add(new Symbol("-", TYPE_OPERATOR));
        symbolSet.add(new Symbol("*", TYPE_OPERATOR));
        symbolSet.add(new Symbol("/", TYPE_OPERATOR));
        symbolSet.add(new Symbol("**", TYPE_OPERATOR));
        symbolSet.add(new Symbol("%", TYPE_OPERATOR));
        symbolSet.add(new Symbol("=", TYPE_OPERATOR));
        symbolSet.add(new Symbol("++", TYPE_OPERATOR));
        symbolSet.add(new Symbol("--", TYPE_OPERATOR));
        symbolSet.add(new Symbol("==", TYPE_OPERATOR));
        symbolSet.add(new Symbol("!=", TYPE_OPERATOR));
        symbolSet.add(new Symbol(">", TYPE_OPERATOR));
        symbolSet.add(new Symbol("<", TYPE_OPERATOR));
        symbolSet.add(new Symbol("&", TYPE_OPERATOR));
        symbolSet.add(new Symbol("|", TYPE_OPERATOR));
        symbolSet.add(new Symbol("!", TYPE_OPERATOR));
        symbolSet.add(new Symbol("&&", TYPE_OPERATOR));
        symbolSet.add(new Symbol("||", TYPE_OPERATOR));
        symbolSet.add(new Symbol(".", TYPE_OPERATOR));
        symbolSet.add(new Symbol("+=", TYPE_OPERATOR));
        symbolSet.add(new Symbol("-=", TYPE_OPERATOR));
        symbolSet.add(new Symbol("*=", TYPE_OPERATOR));
        symbolSet.add(new Symbol("/=", TYPE_OPERATOR));
        symbolSet.add(new Symbol(">=", TYPE_OPERATOR));
        symbolSet.add(new Symbol("<=", TYPE_OPERATOR));

        symbolSet.add(new Symbol("main", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("auto", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("short", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("int", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("long", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("float", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("double", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("char", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("return", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("struct", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("union", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("enum", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("typedef", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("const", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("signed", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("sizeof", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("register", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("unsinged", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("extern", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("static", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("void", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("if", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("else", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("switch", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("case", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("for", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("do", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("while", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("goto", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("continue", TYPE_KEYWORDS));
        symbolSet.add(new Symbol("break", TYPE_KEYWORDS));

        System.out.println("Border_Num: " + Symbol.getBorder_num() + "\tOperator_Num: " + Symbol.getOperator_num() + "\tKeywords_Num: " + Symbol.getKeywords_num());
    }

    public static void main(String[] args) {
        LexParser parser = new LexParser();

        parser.inputData(new File("hello.c")).parser();

    }

    public LexParser inputData( File dataFile ) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFile));
            StringBuffer dataBuffer = new StringBuffer();
            String dataLine = null;
            while( (dataLine = bufferedReader.readLine()) != null ) {
                dataBuffer.append(dataLine);
            }
            bufferedReader.close();
            return inputData(dataBuffer.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LexParser inputData( String data ) {
        this.data = data;     //为了方便处理，在结尾加上间隔符【空格】
        System.out.println(data);
        return this;
    }

    public LexParser parser() {

        char[] dataCharArray = data.toCharArray();
        for ( int index = 0; index < dataCharArray.length; index ++ ) {
            char character = dataCharArray[index];
            //System.out.println("character: [" + character + "]");
            //表示遇到的是正常字符，并非【空格】与【终止字符】【运算符】
            if ( character != ' ' && character != '\t' && judgeSymbol(character).getType() != TYPE_BORDER && judgeSymbol(character).getType() != TYPE_OPERATOR ) {
                symbolBuffer.append(character);
            }
            else {
                //通过Stream过滤器提取出满足条件的集合，获取该集合的迭代器
                Iterator<Symbol> iterator = symbolSet.stream().filter((symbol) -> (symbol.getSymbol().equals(symbolBuffer.toString()))).iterator();
                Symbol symbol = null;
                if ( iterator.hasNext() ) {  //如果迭代器中有元素则有符合条件的元素
                    symbol = iterator.next();    //获取满足条件的symbol对象
                    System.out.println(symbol);
                }
                else {  //没有则表示当前字符串为用户自定义的非表中预定义
                    if ( symbolBuffer.toString().length() != 0 ) {
                        symbol = new Symbol(symbolBuffer.toString(), TYPE_VARIABLE);
                        symbolSet.add(symbol);
                        System.out.println(symbol);
                    }
                }
                symbolBuffer.delete(0, symbolBuffer.length());  //清空buffer

                if ( judgeSymbol(character).getType() == TYPE_OPERATOR ) {
                    if ( (symbol = judgeSymbol(character + "" + dataCharArray[index+1])).getType() == TYPE_OPERATOR ) {
                        System.out.println(symbol);
                        index ++;
                    }
                    else {
                        System.out.println(judgeSymbol(character));
                    }
                }
                else if ( judgeSymbol(character).getType() == TYPE_BORDER ) {
                    System.out.println(judgeSymbol(character));
                }
            }
        }
        return this;
    }

    private Symbol judgeSymbol(char character) {
        return judgeSymbol(character+"");
    }

    private Symbol judgeSymbol(String string) {
        Iterator<Symbol> iterator = symbolSet.stream().filter((symbol) -> (symbol.getSymbol().equals(string))).iterator();
        if ( iterator.hasNext() ) {
            return iterator.next();
        }
        else {
            return new Symbol(null, TYPE_NULL);
        }
    }

}

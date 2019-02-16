package md2html;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Converter {
    final String text;
    final private ArrayList<String> markupSymbols=new ArrayList<>();
    final private Map<Character,String> specialSymbols=new HashMap<>();
    Converter(String text){
        markupSymbols.add("--");
        markupSymbols.add("**");
        markupSymbols.add("__");
        markupSymbols.add("_");
        markupSymbols.add("`");
        markupSymbols.add("*");
        specialSymbols.put('>',"&gt;");
        specialSymbols.put('<',"&lt;");
        specialSymbols.put('&',"&amp;");
        this.text=text;
    }
    public boolean checkEndLine(int index,String endLine){
        if (endLine==null || index<0 || index+endLine.length()>text.length()) return false;
        String s;
        s=text.substring(index,index+endLine.length());
        return s.equals(endLine);
    }
    public boolean checkSpecialSymbol(int index){
        return specialSymbols.containsKey(text.charAt(index));
    }
    public String convertSpecialSymbol(int index){
        return specialSymbols.get(text.charAt(index));
    }
    public boolean checkMarkupSymbols(int index){
        for(int i=0;i<markupSymbols.size();++i) {
            String s = markupSymbols.get(i);
            if (index + s.length() > text.length()) {
                continue;
            }
            if (text.substring(index, index + s.length()).equals(s)) {
                return true;
            }
        }
        return false;
    }
    public String getMarkupSymbols(int index){
        for(int i=0;i<markupSymbols.size();++i) {
            String s = markupSymbols.get(i);
            if (index + s.length() > text.length()) {
                continue;
            }
            if (text.substring(index, index + s.length()).equals(s)) {
                return s;
            }
        }
        return null;
    }
    public StringBuilder convertEndLine(String endLine,boolean open){
        if (endLine==null){
            return new StringBuilder();
        }
        if (endLine.equals("**") || endLine.equals("__")){
            if (open) {
                return new StringBuilder("<strong>");
            }
            return new StringBuilder("</strong>");
        }
        if (endLine.equals("*") || endLine.equals("_")){
            if (open) {
                return new StringBuilder("<em>");
            }
            return new StringBuilder("</em>");
        }
        if (endLine.equals("--")){
            if (open) {
                return new StringBuilder("<s>");
            }
            return new StringBuilder("</s>");
        }
        if (endLine.equals("`")){
            if (open) {
                return new StringBuilder("<code>");
            }
            return new StringBuilder("</code>");
        }
        return null;
    }
}

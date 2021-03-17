import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;


class RefInt {
	int i;
	public RefInt(int Value) {
		i = Value;
	}
}


public class ExpandString {

	
	public static void main(String[] args) {
		
		System.out.println("Введите строку:");
		
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		   
		//начальная позиция символа в строке
		RefInt indx = new RefInt(0); 
		
        String result = "";
		
        // Проверяем строку на валидность и рекурсивно разворачиваем скобки
        if (isValid(s))
        	result = ExpandBrackets(s, indx);
        else
        	System.out.println("Строка не соответствует требованиям!");
        
        
        System.out.println(result);

	}

	static String ExpandBrackets(String s, RefInt indx) {
		// результирующая строка
		String st = "";
		// Переменная для цифр в строке
		String digit = "";
		
		 for (indx.i = indx.i; indx.i < s.length(); indx.i++)
         {
             if (Character.isDigit(s.toCharArray()[indx.i]))
             {
            	 digit = (st == "") ? digit : "";
                 digit = digit + s.toCharArray()[indx.i];
                 continue;
             }
             else if (s.toCharArray()[indx.i] == '[')
             {
            	 indx.i++;
                 String res = ExpandBrackets(s, indx);
                 int num = tryParse(digit, 1);
                 for (int j = 0; j < num; j++)
                     st += res;
                 digit = "";

             }
             else if (s.toCharArray()[indx.i] == ']')
                 return st;
             else
                 st += s.toCharArray()[indx.i];
         }
		
		return st;
	}
	
	// Проверка строки на валидность
    static boolean isValid(String s)
    {
        if (!Pattern.matches("^[a-zA-Z0-9\\[\\]]+$", s))
            return false;
            
        if (CountSubString(s, "\\[") !=  CountSubString(s, "\\]"))
            return false;
        
        return true;
    }
	
    // Подсчет количества скобок в строке
    static int CountSubString(String s, String regex) {
    	
    	int count = 0;
    	Pattern regexp = Pattern.compile(regex);
        Matcher m = regexp.matcher(s);
        while (m.find())
        	count++;
        
    	return count;
    }
    
    // Конвертация строки в число
	public static int tryParse(String value, int defaultVal) {
	    try {
	        return Integer.parseInt(value);
	    } catch (NumberFormatException e) {
	        return defaultVal;
	    }
	}
	
	
}

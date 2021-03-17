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
		
		System.out.println("������� ������:");
		
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		   
		//��������� ������� ������� � ������
		RefInt indx = new RefInt(0); 
		
        String result = "";
		
        // ��������� ������ �� ���������� � ���������� ������������� ������
        if (isValid(s))
        	result = ExpandBrackets(s, indx);
        else
        	System.out.println("������ �� ������������� �����������!");
        
        
        System.out.println(result);

	}

	static String ExpandBrackets(String s, RefInt indx) {
		// �������������� ������
		String st = "";
		// ���������� ��� ���� � ������
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
	
	// �������� ������ �� ����������
    static boolean isValid(String s)
    {
        if (!Pattern.matches("^[a-zA-Z0-9\\[\\]]+$", s))
            return false;
            
        if (CountSubString(s, "\\[") !=  CountSubString(s, "\\]"))
            return false;
        
        return true;
    }
	
    // ������� ���������� ������ � ������
    static int CountSubString(String s, String regex) {
    	
    	int count = 0;
    	Pattern regexp = Pattern.compile(regex);
        Matcher m = regexp.matcher(s);
        while (m.find())
        	count++;
        
    	return count;
    }
    
    // ����������� ������ � �����
	public static int tryParse(String value, int defaultVal) {
	    try {
	        return Integer.parseInt(value);
	    } catch (NumberFormatException e) {
	        return defaultVal;
	    }
	}
	
	
}

package utility;

public class Resuable_function {

	static Xls_Reader reader=new Xls_Reader(System.getProperty("user.dir")+"\\resources\\Apisheet.xlsx");
	public static String value(String val)
	{
		String sw=val.substring(0, 3);
		return sw;
	}
	public static String change(int dea)
	{
		String x=String.valueOf(dea);
		return x;
	}
	public static String status_code()
	{
		return reader.getCellData("Api", "Status-code", 2);
	}
	public static String status_message()
	{
		return reader.getCellData("Api", "Status-message", 2);
	}
	public static void test_message(String x)
	{
	 reader.setCellData("Api", "Test_status", 2, x);
	}
	public static String[] test_url()
	{
		String we=reader.getCellData("Api", "Url", 2);
		String s[]=we.split("/");
		return s;
		
		
	}
}

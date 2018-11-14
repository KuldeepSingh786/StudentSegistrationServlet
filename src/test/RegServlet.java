package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.StringJoiner;

import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RegServlet extends GenericServlet
{
	public Connection con;
	
	public void init()
	{
		con=DBConnection.getCon();
	}
	
	public void service(ServletRequest req,ServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		StringJoiner sj1=new StringJoiner("/"); //DOB
		StringJoiner sj2=new StringJoiner(","); //Hobbies
		StringJoiner sj3=new StringJoiner("-"); //ClassX
		StringJoiner sj4=new StringJoiner("-");//ClassXII
		StringJoiner sj5=new StringJoiner("-");//Graduation
		
		String fName=req.getParameter("First_Name");
		String lName=req.getParameter("Last_Name");
		
		String days=req.getParameter("Birthday_day");
		sj1.add(days);
		String month=req.getParameter("Birthday_Month");
		sj1.add(month);
		String year=req.getParameter("Birthday_Year");
		sj1.add(year);
		
		String mailId=req.getParameter("Email_Id");
		long mobNo=Long.parseLong(req.getParameter("Mobile_Number"));
		
		String gen=req.getParameter("Gender");
		String addr=req.getParameter("Address");
		String city=req.getParameter("City");
		String dist=req.getParameter("District");
		String pin=req.getParameter("Pin_Code");
		String state=req.getParameter("State");
		String country=req.getParameter("Country");
		String hobbies[]=req.getParameterValues("Hobby");
		for(String l:hobbies)
		{
			sj2.add(l);
		}
		//Qualification
		//ClassX
		String board=req.getParameter("ClassX_Board");
		sj3.add(board);
		String perc=req.getParameter("ClassX_Percentage");
		sj3.add(perc);
		String yop=req.getParameter("ClassX_YrOfPassing");
		sj3.add(yop);
		
		//ClassXII
				String boardXII=req.getParameter("ClassXII_Board");
				sj4.add(boardXII);
				String percXII=req.getParameter("ClassXII_Percentage");
				sj4.add(percXII);
				String yopXII=req.getParameter("ClassXII_YrOfPassing");
				sj4.add(yopXII);
		//Graduation
				String boardGrad=req.getParameter("Graduation_Board");
				sj5.add(boardGrad);
				String percGrad=req.getParameter("Graduation_Percentage");
				sj5.add(percGrad);
				String yopGrad=req.getParameter("Graduation_YrOfPassing");
				sj5.add(yopGrad);
		
		String course=req.getParameter("Course");
		
		try
		{
			PreparedStatement ps=con.prepareStatement("insert into StudReg values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setString(3,sj1.toString());
			ps.setString(4, mailId);
			ps.setLong(5, mobNo);
			ps.setString(6, gen);
			ps.setString(7, addr);
			ps.setString(8, city);
			ps.setString(9, dist);
			ps.setString(10, pin);
			ps.setString(11, state);
			ps.setString(12, country);
			ps.setString(13, sj2.toString());
			ps.setString(14, sj3.toString());
			ps.setString(15, sj4.toString());
			ps.setString(16, sj5.toString());
			ps.setString(17, course);
			//ps.executeQuery();
			int k=ps.executeUpdate();
			if(k>0)
			{
				pw.println("Student registration done successfully....");
			}
			else
			{
				pw.println("Student registration Failed....");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

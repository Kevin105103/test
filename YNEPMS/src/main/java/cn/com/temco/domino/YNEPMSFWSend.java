// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   YNEPMSFWSend.java

package cn.com.temco.domino;

import cn.com.boco.services.epmswservice.ePMSWServiceClient;
import cn.com.boco.services.epmswservice.ePMSWServicePortType;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.xml.parsers.*;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class YNEPMSFWSend extends HttpServlet
{

    public YNEPMSFWSend()
    {
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        System.out.println("YNEPMSFWSend--Start");
        String rtnStr = "";
        httpservletrequest.setCharacterEncoding("GBK");
        httpservletresponse.setContentType("text/html");
        httpservletresponse.setCharacterEncoding("GBK");
        String businessId = toChinese(httpservletrequest.getParameter("businessId"));
        String businessName = toChinese(httpservletrequest.getParameter("businessName"));
        String fileCode = toChinese(httpservletrequest.getParameter("fileCode"));
        String fileDate = toChinese(httpservletrequest.getParameter("fileDate"));
        String totalInvestment = toChinese(httpservletrequest.getParameter("totalInvestment"));
        String projectAmount = toChinese(httpservletrequest.getParameter("projectAmount"));
        String equipAmount = toChinese(httpservletrequest.getParameter("equipAmount"));
        String otherAmount = toChinese(httpservletrequest.getParameter("otherAmount"));
        String fwstatus = toChinese(httpservletrequest.getParameter("fwstatus"));
        String fwdescription = toChinese(httpservletrequest.getParameter("fwdescription"));
        String ynepmsextend1 = toChinese(httpservletrequest.getParameter("ynepmsextend1"));
        String ynepmsextend2 = toChinese(httpservletrequest.getParameter("ynepmsextend2"));
        String ynepmsextend3 = toChinese(httpservletrequest.getParameter("ynepmsextend3"));
        String ynepmsextend4 = toChinese(httpservletrequest.getParameter("ynepmsextend4"));
        String ynepmsextend5 = toChinese(httpservletrequest.getParameter("ynepmsextend5"));
        String infoxml = "<?xml version=\"1.0\" encoding=\"gb2312\"?><invoke type =\"create\"><office><property name=\"businessId\">" + businessId + "</property>" + "<property name=\"businessName\">" + businessName + "</property>" + "<property name=\"fileCode\">" + fileCode + "</property>" + "<property name=\"fileDate\">" + fileDate + "</property>" + "<property name=\"totalInvestment\">" + totalInvestment + "</property>" + "<property name=\"projectAmount\">" + projectAmount + "</property>" + "<property name=\"equipAmount\">" + equipAmount + "</property>" + "<property name=\"otherAmount\">" + otherAmount + "</property>" + "<property name=\"fwstatus\">" + fwstatus + "</property>" + "<property name=\"fwdescription\">" + fwdescription + "</property>" + "<property name=\"ynepmsextend1\">" + ynepmsextend1 + "</property>" + "<property name=\"ynepmsextend2\">" + ynepmsextend2 + "</property>" + "<property name=\"ynepmsextend3\">" + ynepmsextend3 + "</property>" + "<property name=\"ynepmsextend4\">" + ynepmsextend4 + "</property>" + "<property name=\"ynepmsextend5\">" + ynepmsextend5 + "</property>" + "</office>" + "</invoke>";
        log.info(infoxml);
        ePMSWServiceClient client = new ePMSWServiceClient();
        ePMSWServicePortType HelloService = client.getePMSWServiceHttpPort("http://10.174.70.54:9080/services/ePMSWService");
        rtnStr = HelloService.ifService("epms_oa", "$epmswebclient4oa$", "Office", "", infoxml, "OA");
        log.info(rtnStr);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try
        {
            db = dbf.newDocumentBuilder();
        }
        catch(ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        if(rtnStr.indexOf("send.oadata") != -1)
        {
            StringReader sr = new StringReader(rtnStr);
            Document respXmlDoc = null;
            try
            {
                respXmlDoc = db.parse(new InputSource(sr));
            }
            catch(SAXException e)
            {
                e.printStackTrace();
            }
            Element root = respXmlDoc.getDocumentElement();
            NodeList nl = root.getElementsByTagName("describe");
            PrintWriter out = httpservletresponse.getWriter();
            out.write("<html>\r\n");
            out.write("\r\n");
            out.write("<link href=\"bbs.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.write("<head>\r\n");
            out.write("<title>\u4E91\u5357EPMS</title>\r\n");
            out.write("</head>\r\n");
            out.write("<body>\r\n");
            out.write("<form name=\"frmJump\" method=\"get\" action=\"http://test\">\r\n");
            out.write("<br><font size=3 color = brown><B>\u4E91\u5357EPMS-\u53D1\u6587\u7BA1\u7406-\u5904\u7406\u7ED3\u679C\uFF1A</B></font><br>");
            if(nl.getLength() > 0)
            {
                for(int i = 0; i < nl.getLength(); i++)
                {
                    Element ctNode = (Element)nl.item(i);
                    String describe = ctNode.getTextContent();
                    out.write("<br><font size=2 color = blue>" + describe + "</font>");
                    out.write("<br><font size=2 color = blue>\u53D1\u6587\u7F16\u53F7\uFF1A" + fileCode + "</font>");
                }

            }
            out.write("</form>");
            out.write("<script language=\"javascript\" type=\"text/javascript\">");
            out.write("</script>");
            out.write("</body>");
            out.write("</html>");
            out.flush();
            out.close();
        }
    }

    public void doPost(HttpServletRequest httpservletrequest1, HttpServletResponse httpservletresponse1)
        throws ServletException, IOException
    {
    }

    public String toChinese_bak(String str)
    {

        String s = str;
/*
        byte tmp[] = str.getBytes("ISO-8859-1");
        s = new String(tmp);
        return s;
        Exception e;
        e;
*/        return s;

    }

     public String toChinese(String strvalue)
	{
	try{
	if(strvalue==null)
	return null;
	else
	{
	strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
	return strvalue;
	}
	}catch(Exception e){
	return null;
	}
	}


    private static Logger log;

    static 
    {
        log = Logger.getLogger(cn.com.temco.domino.YNEPMSFWSend.class);
    }
}

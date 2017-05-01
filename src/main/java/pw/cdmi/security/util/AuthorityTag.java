package pw.cdmi.security.util;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;


public class AuthorityTag implements SimpleTag {



	JspContext context = null;
	JspTag parent = null;
	JspFragment jspBody = null;
	
	public void setUrl(String url) {
		this.url = url;
	}
	private String url;
	@SuppressWarnings("unchecked")
	@Override  
    public void doTag() throws JspException, IOException {  
	  //从session中取值
	  List<String> urllist=	(List<String>)context.getAttribute("AuthsOfUrl", 3);
	  //去除重复的url
	  HashSet<String> urlset=new HashSet<String>(urllist);
       for (String aurl : urlset) {
    	   //判断是否有权限操作
    	   if(url.equals(aurl)){
    		   jspBody.invoke(null);
           }
	  }
    }  
    @Override  
    public JspTag getParent() {  
        return parent;  
    }  
    @Override  
    public void setJspBody(JspFragment jspBody) {  
                this.jspBody=jspBody;  
        }  
    @Override  
    public void setJspContext(JspContext jspContext) {  
        context = jspContext;  
    }  
    @Override  
    public void setParent(JspTag parent) {  
        this.parent = parent;  
    }  

}

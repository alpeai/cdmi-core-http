package pw.cdmi.core.http;

import javax.servlet.http.HttpSession;

public class CustomSession {
	protected HttpSession curSession;

	public CustomSession(HttpSession session) {
		this.curSession = session;
	}

	/**
	 * 判断用户Session是否登录。
	 * 
	 * @return
	 */
	public void save() {
		save(20);
	}

	public void save(int time){
		curSession.setMaxInactiveInterval(time * 60);
	}
	/**
	 * 退出回话
	 */
	public void invalidate() {
		curSession.invalidate();
	}
}

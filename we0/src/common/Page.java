package common;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author chchen
 * @version 1.0
 */
public class Page {
	public String output = "";
	public String file;
	public String pvar = "p";
	public int curPage;
	public String varStr = "";
	public int totalPage;
	public int pageNumber = 10;
	private String charSet = "utf-8";
	private String andString = "?";

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getPvar() {
		return pvar;
	}

	public void setPvar(String pvar) {
		this.pvar = pvar;
	}

	public void setVars(HashMap<String, String> varMap) {
		if (varMap == null || varMap.size() == 0)
			return;
		Iterator<String> it = varMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			try {
				varStr += "&amp;" + key + "="
						+ URLEncoder.encode(varMap.get(key), charSet);
			} catch (Exception e) {
			}
		}
	}

	public void setFilePath(String file) {
		this.file = file;
	}

	public void set(int totalPage, int curPage, int pageNumber) {
		if(this.file.indexOf("?")!=-1){
			andString = "&";
		}
		this.totalPage = totalPage;
		if (curPage > totalPage)
			curPage = totalPage;
		if (curPage < 1)
			curPage = 1;
		this.curPage = curPage;
		this.pageNumber = pageNumber;
		if (this.totalPage > 1) {
			this.output += "<table class=\"perpage\"><tr><td>";
			this.output += "<a href=\"" + this.file + andString + this.pvar + "=1"
					+ this.varStr + "\"	 title=\"首页\" class=\"sx\">首页</a>";
			if (curPage > 1) {
				this.output += "<a href=\"" + this.file + andString + this.pvar + "="
						+ (curPage - 1) + (this.varStr)
						+ "\" title=\"前1页\" class=\"ssx\">上页</a>";
			}
			int start = (int) Math.floor((double) curPage / pageNumber)
					* pageNumber;

			int end = start + pageNumber - 1;

			if (start < 1)
				start = 1;
			if (end > this.totalPage)
				end = this.totalPage;

			if (curPage > 5) {
				this.output += "<a href=\"" + this.file + andString
						+ this.pvar + "=" + 1 + this.varStr + "\">1</a>";
				this.output += "<a href=\"" + this.file + andString
				+ this.pvar + "=" + 2 + this.varStr + "\">2</a>";
				this.output += "<span>...</span>";


				for (int j = curPage-2; j <= end; j++) {
					if (curPage == j) {
						this.output += "<span class=\"current\">"+ j + "</span>";
					} else {
						this.output += "<a href=\"" + this.file + andString
								+ this.pvar + "=" + j + this.varStr + "\">" + j
								+ "</a>";
					}
				}
			}
			else{
				for (int i = start; i <= end; i++) {
					
					if (curPage == i) {
						this.output += "<span class=\"current\">"+ i + "</span>";
					} else {
						this.output += "<a href=\"" + this.file + andString
								+ this.pvar + "=" + i + this.varStr + "\">" + i
								+ "</a>";
					}
				}
			}

			if (this.totalPage > curPage
					&& this.totalPage > 1) {
				this.output += "<a href=\"" + this.file + andString + this.pvar + '='
						+ (curPage + 1) + (this.varStr)
						+ "\" title=\"下一页>\" class=\"ssx\">下页</a>";
			}
			this.output += "<a href=\"" + this.file + andString + this.pvar + '='
					+ this.totalPage + (this.varStr) + "\" title=\"尾页\" class=\"sx\" >尾页</a>";
			this.output += "<span class=\"abc\"  >共<strong>"+totalPage+"</strong>页 </span></td></tr></table>";
		}
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
}


package model;

public class News {
     public String title;
     public String main;
     public String urlo;
     public String url;
     
     
     
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	public News(String title, String main, String picurl, String url
			) {
		this.title = title;
		this.main = main;
		this.urlo = picurl;
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}


	public String getUrlo() {
		return urlo;
	}
	public void setUrlo(String urlo) {
		this.urlo = urlo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
     
}

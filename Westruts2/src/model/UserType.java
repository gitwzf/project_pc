package model;

/*
 * 微信粉丝类型
 */
public class UserType {
    private String id;     //分组ID
    private String name;   //类型名
    private String link;   //URL
    private int num;       //数量
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}

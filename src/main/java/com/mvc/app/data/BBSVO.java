package com.mvc.app.data;

public class BBSVO {

    private Integer id;

    private String name;

    private String email;

    private String pass;

    private String title;

    private String comment;

    private String wdate;

    private String ip;

    private Integer see;


    public BBSVO() { };
	public BBSVO(String name, String email, String pass, String title, String comment,
			String ip) {
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.title = title;
		this.comment = comment;
		this.ip = ip;
	}

	public BBSVO(int id, String title, String comment) {
		this.id = id;
		this.title = title;
		this.comment = comment;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getSee() {
        return see;
    }

    public void setSee(Integer see) {
        this.see = see;
    }

}
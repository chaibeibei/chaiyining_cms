package com.chaiyining.cms.pojo;

import java.io.Serializable;

public class Tag  implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String tagname;
    
    @Override
	public String toString() {
		return "Tag [id=" + id + ", tagname=" + tagname + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname == null ? null : tagname.trim();
    }
}
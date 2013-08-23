package com.sanzhong.score.pojo;

import java.io.Serializable;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("serial")
public class Resource extends BaseBean implements Serializable {

	private Integer id;
	private String name;
	private String url;
	private Integer is_module;
	private String icon;
    private Integer parent_id;
    private Integer auth;

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIs_module() {
		return is_module;
	}

	public void setIs_module(Integer is_module) {
		this.is_module = is_module;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@SuppressWarnings("rawtypes")
	public static RowMapper getRowMapper() throws Exception {
		return mapRow(Resource.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (obj instanceof Resource) {
			Resource res = (Resource) obj;
			if(this.id.equals(res.id)){
				return true;
			}
		}
		return false;

	}

}

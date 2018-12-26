package com.zkf.mysql.entity;

import com.common.utils.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 数据库对象基础类.
 * 
 * @author sxq $Id: BaseEntity.java 10994 2017-01-05 02:29:34Z zkf $
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 3425184395587010150L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
	@GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "seq_id", unique = true, nullable = false)
    protected Long id;

    @Column(name = "create_user_id")
    protected Long createUserId;

    @JsonSerialize(using = JsonDateSerializer.class)
    @CreationTimestamp
    @Column(name = "create_time")
    protected Timestamp createTime;

    @Column(name = "update_user_id")
    protected Long updateUserId;

    @JsonSerialize(using = JsonDateSerializer.class)
    @UpdateTimestamp
    @Column(name = "update_time")
    protected Timestamp updateTime;

	@Column(name = "status")
	protected Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

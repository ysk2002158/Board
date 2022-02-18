package com.example.board.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 投稿.
 */
@Entity
@Table(name = "post")
@Data
public class Post {

	/** ID */
	@Id
	@Column
	private String id = null;

	/** 投稿者 */
	@Column(length = 20, nullable = false)
	private String author = null;

	/** タイトル */
	@Column(length = 20, nullable = false)
	private String title = null;

	/** 内容 */
	@Column(length = 1000, nullable = false)
	private String body = null;

	/** 登録日時 */
	private Date createdDate = null;

	/** 更新日時 */
	private Date updatedDate = null;

	/** 削除済 */
	private boolean deleted = false;

}

package carrental.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(length=100000)
	private String article;
	
	private String author;
	
	public Article(String article, String author) {
		super();
		this.article = article;
		this.author = author;
	}

}
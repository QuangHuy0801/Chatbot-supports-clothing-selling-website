package WebProject.WebProject.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_details")
public class Product_details {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

		@Column(name = "size")
		private int size;
		
		@Column(name = "color", columnDefinition = "nvarchar(11)")
		private String color;

		@Column(name = "quantity" )
		private int quantity;
		@ManyToOne
		@JoinColumn(name = "product_id")
		@EqualsAndHashCode.Exclude
		@ToString.Exclude
		private Product product;
		
		@OneToMany(mappedBy = "product_details", cascade = CascadeType.ALL)
		private List<Order_Item> order_Item;
		
		@OneToMany(mappedBy = "product_details", cascade = CascadeType.ALL)
		private List<Cart> cart;
}

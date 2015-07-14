package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the DEMO_ORDERS database table.
 * 
 */
@Entity
@Table(name="DEMO_ORDERS",schema="GUITARSHOP")
@NamedQuery(name="DemoOrder.findAll", query="SELECT d FROM DemoOrder d")
public class DemoOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORDER_ID")
	private long orderId;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDER_TIMESTAMP")
	private Date orderTimestamp;

	@Column(name="ORDER_TOTAL")
	private BigDecimal orderTotal;

	@Column(name="USER_ID")
	private BigDecimal userId;

	//bi-directional many-to-one association to DemoCustomer
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private DemoCustomer demoCustomer;

	public DemoOrder() {
	}

	public long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderTimestamp() {
		return this.orderTimestamp;
	}

	public void setOrderTimestamp(Date orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}

	public BigDecimal getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public DemoCustomer getDemoCustomer() {
		return this.demoCustomer;
	}

	public void setDemoCustomer(DemoCustomer demoCustomer) {
		this.demoCustomer = demoCustomer;
	}

}
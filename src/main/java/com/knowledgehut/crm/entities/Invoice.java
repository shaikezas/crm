package com.knowledgehut.crm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "INVOICE")
public class Invoice extends AuditableIdEntity {

    @Column(name = "INVOICE_NUM")
    private String invoiceNum;

    @Column(name = "WORK_SHOP_ID")
    private Long workShopId;

	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public Long getWorkShopId() {
		return workShopId;
	}

	public void setWorkShopId(Long workShopId) {
		this.workShopId = workShopId;
	}


}

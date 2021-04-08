package com.temadiplomes.doctorfinder.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="oda_mjekeve_data")
public class OdaEMjekeveKS {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="identify_nr")
	private String identifyNo;
	
	@Column(name="nr_anetarit")
	private int nrAnetarit;
	
	@Column(name="emri")
	private String emri;
	
	@Column(name="mbiemri")
	private String mbiemri;
	
	@Column(name="emri_prindit")
	private String emriPrindit;
	
	@Column(name="nr_licences")
	private String licenceNO;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_skadimit")
	private Date dataSkadimit;
	
	public OdaEMjekeveKS() {
		
	}

	public OdaEMjekeveKS(String identifyNo, int nrAnetarit, String emri, String mbiemri, String emriPrindit,
			String licenceNO, Date dataSkadimit) {
		this.identifyNo = identifyNo;
		this.nrAnetarit = nrAnetarit;
		this.emri = emri;
		this.mbiemri = mbiemri;
		this.emriPrindit = emriPrindit;
		this.licenceNO = licenceNO;
		this.dataSkadimit = dataSkadimit;
	}
	
	public OdaEMjekeveKS(String identifyNo, int nrAnetarit, String emri, String mbiemri, String emriPrindit,
			String licenceNO) {
		this.identifyNo = identifyNo;
		this.nrAnetarit = nrAnetarit;
		this.emri = emri;
		this.mbiemri = mbiemri;
		this.emriPrindit = emriPrindit;
		this.licenceNO = licenceNO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifyNo() {
		return identifyNo;
	}

	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
	}

	public int getNrAnetarit() {
		return nrAnetarit;
	}

	public void setNrAnetarit(int nrAnetarit) {
		this.nrAnetarit = nrAnetarit;
	}

	public String getEmri() {
		return emri;
	}

	public void setEmri(String emri) {
		this.emri = emri;
	}

	public String getMbiemri() {
		return mbiemri;
	}

	public void setMbiemri(String mbiemri) {
		this.mbiemri = mbiemri;
	}

	public String getEmriPrindit() {
		return emriPrindit;
	}

	public void setEmriPrindit(String emriPrindit) {
		this.emriPrindit = emriPrindit;
	}

	public String getLicenceNO() {
		return licenceNO;
	}

	public void setLicenceNO(String licenceNO) {
		this.licenceNO = licenceNO;
	}

	public Date getDataSkadimit() {
		return dataSkadimit;
	}

	public void setDataSkadimit(Date dataSkadimit) {
		this.dataSkadimit = dataSkadimit;
	}

	@Override
	public String toString() {
		return "OdaEMjekeveKS [id=" + id + ", identifyNo=" + identifyNo + ", nrAnetarit=" + nrAnetarit + ", emri="
				+ emri + ", mbiemri=" + mbiemri + ", emriPrindit=" + emriPrindit + ", licenceNO=" + licenceNO
				+ ", dataSkadimit=" + dataSkadimit + "]";
	}
	
}

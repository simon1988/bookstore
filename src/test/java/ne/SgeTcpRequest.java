package ne;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class SgeTcpRequest {
	private SgeTcpHead head;
	@XmlElementWrapper(name = "body")
	@XmlElement(name = "record")
	private List<SgeTcpRecord> body;
	public SgeTcpHead getHead() {
		return head;
	}
	public void setHead(SgeTcpHead head) {
		this.head = head;
	}
	public List<SgeTcpRecord> getBody() {
		return body;
	}
	public void setBody(List<SgeTcpRecord> body) {
		this.body = body;
	}
}
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "head")
class SgeTcpHead {
	@XmlElement(name = "h_exch_code")
	private String adapter;
	@XmlElement(name = "h_bank_no")
	private String bankNumber;
	@XmlElement(name = "h_term_type")
	private String termType;
	@XmlElement(name = "h_teller_id")
	private String tellerId;
	@XmlElement(name = "h_teller_id_1")
	private String tellerId1;
	@XmlElement(name = "h_teller_id_2")
	private String tellerId2;
	@XmlElement(name = "h_bk_seq_no")
	private String bankSeq;
	@XmlElement(name = "h_work_date")
	private String workDate;
	@XmlElement(name = "h_exch_date")
	private String exchangeDate;
	
	@XmlElement(name = "h_user_id")
	private String userId;
	@XmlElement(name = "h_branch_id")
	private String branchId;
	@XmlElement(name = "h_fact_date")
	private String factDate;
	@XmlElement(name = "h_fact_time")
	private String factTime;
	@XmlElement(name = "h_serial_no")
	private String serialNumber;
	@XmlElement(name = "h_rsp_code")
	private String rspCode;
	@XmlElement(name = "h_rsp_msg")
	private String rspMessage;

	@XmlElement(name = "trans_code")
	private String signAdapter;
	@XmlElement(name = "work_date")
	private String signWorkDate;
	@XmlElement(name = "sdb_id")
	private String signSDBId;
	@XmlElement(name = "bank_id")
	private String signBankId;
	@XmlElement(name = "seq_no")
	private String signSeqNo;
	
	public String getAdapter() {
		return adapter;
	}
	public void setAdapter(String adapter) {
		this.adapter = adapter;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public String getTellerId() {
		return tellerId;
	}
	public void setTellerId(String tellerId) {
		this.tellerId = tellerId;
	}
	public String getTellerId1() {
		return tellerId1;
	}
	public void setTellerId1(String tellerId1) {
		this.tellerId1 = tellerId1;
	}
	public String getTellerId2() {
		return tellerId2;
	}
	public void setTellerId2(String tellerId2) {
		this.tellerId2 = tellerId2;
	}
	public String getBankSeq() {
		return bankSeq;
	}
	public void setBankSeq(String bankSeq) {
		this.bankSeq = bankSeq;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getExchangeDate() {
		return exchangeDate;
	}
	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getFactDate() {
		return factDate;
	}
	public void setFactDate(String factDate) {
		this.factDate = factDate;
	}
	public String getFactTime() {
		return factTime;
	}
	public void setFactTime(String factTime) {
		this.factTime = factTime;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	public String getRspMessage() {
		return rspMessage;
	}
	public void setRspMessage(String rspMessage) {
		this.rspMessage = rspMessage;
	}
	public String getSignAdapter() {
		return signAdapter;
	}
	public void setSignAdapter(String signAdapter) {
		this.signAdapter = signAdapter;
	}
	public String getSignWorkDate() {
		return signWorkDate;
	}
	public void setSignWorkDate(String signWorkDate) {
		this.signWorkDate = signWorkDate;
	}
	public String getSignSDBId() {
		return signSDBId;
	}
	public void setSignSDBId(String signSDBId) {
		this.signSDBId = signSDBId;
	}
	public String getSignBankId() {
		return signBankId;
	}
	public void setSignBankId(String signBankId) {
		this.signBankId = signBankId;
	}
	public String getSignSeqNo() {
		return signSeqNo;
	}
	public void setSignSeqNo(String signSeqNo) {
		this.signSeqNo = signSeqNo;
	}
	
}
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "record")
class SgeTcpRecord {
	@XmlElement(name = "branch_id")
	private String branchId;
	@XmlElement(name = "user_pwd")
	private String userPassword;
	@XmlElement(name = "login_ip")
	private String loginIp;
	@XmlElement(name = "area_code")
	private String areaCode;
	@XmlElement(name = "zipcode")
	private String zipCode;
	@XmlElement(name = "tel")
	private String tel;
	@XmlElement(name = "addr")
	private String address;
	@XmlElement(name = "cert_type_id")
	private String certType;
	@XmlElement(name = "cust_name")
	private String customerName;
	@XmlElement(name = "mobile_phone")
	private String mobile;
	@XmlElement(name = "cert_num")
	private String certNo;
	@XmlElement(name = "email")
	private String email;
	@XmlElement(name = "exch_pwd")
	private String tradePassword;
	@XmlElement(name = "acct_no")
	private String firmId;
	@XmlElement(name = "broker_list")
	private String brokerList;
	@XmlElement(name = "fund_pwd")
	private String fundPassword;
	@XmlElement(name = "grade_id")
	private String gradeId;
	@XmlElement(name = "cust_type_id")
	private String custType;
	@XmlElement(name = "old_acct_stat")
	private String oldAccountStatus;
	@XmlElement(name = "new_acct_stat")
	private String newAccountStatus;

	@XmlElement(name = "inv_name")
	private String signCustName;
	@XmlElement(name = "inv_id_code")
	private String signCertNo;
	@XmlElement(name = "cap_acct")
	private String signFirmid;
	@XmlElement(name = "id_type")
	private String signCertType;
	@XmlElement(name = "cur_code")
	private String signCurrencyType;
	@XmlElement(name = "acct_id")
	private String signBankInfo;
	
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}

	public String getBrokerList() {
		return brokerList;
	}

	public void setBrokerList(String brokerList) {
		this.brokerList = brokerList;
	}

	public String getFundPassword() {
		return fundPassword;
	}

	public void setFundPassword(String fundPassword) {
		this.fundPassword = fundPassword;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}
	
	public String getOldAccountStatus() {
		return oldAccountStatus;
	}

	public void setOldAccountStatus(String oldAccountStatus) {
		this.oldAccountStatus = oldAccountStatus;
	}

	public String getNewAccountStatus() {
		return newAccountStatus;
	}

	public void setNewAccountStatus(String newAccountStatus) {
		this.newAccountStatus = newAccountStatus;
	}

	public String getSignCustName() {
		return signCustName;
	}

	public void setSignCustName(String signCustName) {
		this.signCustName = signCustName;
	}

	public String getSignCertNo() {
		return signCertNo;
	}

	public void setSignCertNo(String signCertNo) {
		this.signCertNo = signCertNo;
	}

	public String getSignFirmid() {
		return signFirmid;
	}

	public void setSignFirmid(String signFirmid) {
		this.signFirmid = signFirmid;
	}

	public String getSignCertType() {
		return signCertType;
	}

	public void setSignCertType(String signCertType) {
		this.signCertType = signCertType;
	}

	public String getSignCurrencyType() {
		return signCurrencyType;
	}

	public void setSignCurrencyType(String signCurrencyType) {
		this.signCurrencyType = signCurrencyType;
	}

	public String getSignBankInfo() {
		return signBankInfo;
	}

	public void setSignBankInfo(String signBankInfo) {
		this.signBankInfo = signBankInfo;
	}
}

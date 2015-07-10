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
	private String bankId;
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
	public String getAdapter() {
		return adapter;
	}
	public void setAdapter(String adapter) {
		this.adapter = adapter;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
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
	
}
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "record")
class SgeTcpRecord {
	@XmlElement(name = "branch_id")
	private String branchId;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}

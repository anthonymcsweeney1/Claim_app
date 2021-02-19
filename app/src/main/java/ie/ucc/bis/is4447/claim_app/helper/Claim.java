package ie.ucc.bis.is4447.claim_app.helper;
// Claim class to get and set Claim variables

public class Claim {
    private int ClaimID;
    private String InvoiceNum;
    private String Status;
    private String customer_reason;
    private String claim_type;
    private String offercode;
    private String settlement;
    private double amount;
    private String invoice_date;
    private String creation_date;
    private int Cus_ID;
    private String Cus_Name;
    private String BillTo;
    private String BillToAcc;
    private String ShipTo;
    private String Approver;
    private String ApproverEmail;
    private String OperatingUnit;
    private String Currency;
    private String ClaimNum;
    private String ShipToAcc;
    private String Creator;
    private String Overage;
    private String notes;
    private String Processor;
    private String approval_level;
    private String lastupdated_by;
    private String lastupdate;
    private int requestid;


    public Claim(int ClaimID, String InvoiceNum, String Status, String customer_reason, String claim_type, String offercode, String settlement, double amount, String invoice_date, String creation_date, int Cus_ID, String Cus_Name, String BillTo, String BillToAcc, String ShipTo, String Approver, String ApproverEmail, String OperatingUnit, String Currency, String ClaimNum, String ShipToAcc, String Creator, String Overage, String notes, String Processor, String approval_level, String lastupdated_by, String lastupdate, int requestid) {
        this.ClaimID = ClaimID;
        this.InvoiceNum = InvoiceNum;
        this.Status = Status;
        this.customer_reason = customer_reason;
        this.claim_type = claim_type;
        this.offercode = offercode;
        this.settlement = settlement;
        this.amount = amount;
        this.invoice_date = invoice_date;
        this.creation_date = creation_date;
        this.Cus_ID = Cus_ID;
        this.Cus_Name = Cus_Name;
        this.BillTo = BillTo;
        this.BillToAcc = BillToAcc;
        this.ShipTo = ShipTo;
        this.Approver = Approver;
        this.ApproverEmail = ApproverEmail;
        this.OperatingUnit = OperatingUnit;
        this.Currency = Currency;
        this.ClaimNum = ClaimNum;
        this.ShipToAcc = ShipToAcc;
        this.Creator = Creator;
        this.Overage = Overage;
        this.notes = notes;
        this.Processor = Processor;
        this.approval_level = approval_level;
        this.lastupdated_by = lastupdated_by;
        this.lastupdate = lastupdate;
        this.requestid = requestid;



    }

    public int getClaimID() {
        return ClaimID;
    }

    public String getInvoiceNum() {
        return InvoiceNum;
    }

    public String getStatus() {
        return Status;
    }

    public String getcustomer_reason() {
        return customer_reason;
    }

    public String getclaim_type() {
        return claim_type;
    }

    public String getoffercode() {
        return offercode;
    }

    public String getsettlement() {
        return settlement;
    }

    public double getamount() {
        return amount;
    }

    public String getinvoice_date() {
        return invoice_date;
    }

    public String getcreation_date() {
        return creation_date;
    }

    public int getCus_ID() {
        return Cus_ID;
    }

    public String getCus_Name() {
        return Cus_Name;
    }

    public String getBillTo() {
        return BillTo;
    }

    public String getBillToAcc() {
        return BillToAcc;
    }

    public String getShipTo() {
        return ShipTo;
    }

    public String getApprover() {
        return Approver;
    }

    public String getApproverEmail() {
        return ApproverEmail;
    }

    public String getOperatingUnit() {
        return OperatingUnit;
    }

    public String getCurrency() {
        return Currency;
    }

    public String getClaimNum() {
        return ClaimNum;
    }

    public String getShipToAcc() {
        return ShipToAcc;
    }

    public String getCreator() {
        return Creator;
    }

    public String getOverage() {
        return Overage;
    }

    public String getnotes() {
        return notes;
    }

    public String getProcessor() {
        return Processor;
    }

    public String getapproval_level() {
        return approval_level;
    }

    public String getlastupdated_by() {
        return lastupdated_by;
    }

    public String getlastupdate() {
        return lastupdate;
    }

    public int getrequestid() {
        return requestid;
    }

}
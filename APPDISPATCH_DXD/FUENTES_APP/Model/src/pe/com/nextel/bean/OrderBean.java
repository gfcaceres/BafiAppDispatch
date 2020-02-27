package pe.com.nextel.bean;

import java.io.Serializable;

import java.sql.Date;
import java.sql.Timestamp;

import pe.com.nextel.util.GenericObject;
import pe.com.nextel.util.MiUtil;


public class OrderBean extends GenericObject implements Serializable{

    private long npOrderId;
    private long npCustomerId;
    private long npSiteId;
    private String npCompanyType;
    private int npProviderGrpId;
    private String npSalesmanName;
    private String npDealerName;
    private long npSolutionId;
    private int npSpecificationId;
    private long npBuildingId;
    private long npRegionId;
    private long npShipToId;
    private String npShipToAddress1;
    private String npShipToAddress2;
    private String npShipToCity;
    private String npShipToProvince;
    private String npShipToState;
    private String npShipToZip;
    private long npDispatchPlaceId;
    private long npCarrierId;
    private String npCarrierName;
    private String npGeneratorType;
    private long npGeneratorId;
    private long npTodoId;
    private long npWorkFlowId;
    private String npOrigen;
    private String npStatus;
    private String npOrderCode;
    private String npDescription;
    private String npUserName1;
    private String npUserName2;
    private String npUserName3;
    private String npPaymentTerms;
    private String npPaymentStatus;
    private Date npPaymentFutureDate;
    private double npPaymentTotal;
    private double npPaymentAmount;
    private Timestamp npSignDate;
    private Timestamp npActivationDate;
    private Timestamp npDeliveryDate;
    private Timestamp npClosedDate;
    private Date npScheduleDate;
    private Date npScheduleDate2;
    private int npScheduleStatus;
    private String npResponsibleAreaToGiveBack;
    private double npDepositGuarantee;
    private String npVoucher;
    private int npExceptionApprove;
    private int npExceptionInstallation;
    private int npExceptionPrice;
    private int npExceptionPlan;
    private int npExceptionWarrant;
    private int npExceptionRevenue;
    private double npExceptionRevenueAmount;
    private String npExcepcionBillCycle;
    private Date npModificationDate;
    private String npModificationBy;
    private String npSolutionName;
    
    private String npType;
    private String npSpecification;
    
    private Timestamp npCreatedDate;
    private String npCreatedBy;
    private int npTimeStamp;
    private Date npPaymentDate;
    
    private String npBpelconversationid;
    private int  npBpelinstanceid;     
    private String npBpelbackinboxs;
    private String npBeginPeriod;
    private String npEndPeriod;
	  private long npDivisionId;
	  private String npDivisionName;
    
    private String npguidegenerated;
    
    private String npstatustmp;
    private long npproposedid;
    
    private String npProcesoAutom;//GGRANADOS
    //MMORA
    private String npcontractorname; 
    private String npcontractorphone;

    private long lSalesStructureOriginalId;
    private int npFlagVep;
    private int npNumCuotas;
    private double npAmountVep;
    
    private String npInvoicesGenerated;

        
   
    
    private long npProviderGrpIdData; //Vendedor Data
    private String npProviderGrpDataName;

    public void setNpCompanyType(String npCompanyType) {
           this.npCompanyType = npCompanyType;
       }
    public String getNpCompanyType() {
           return this.npCompanyType;
       }      
    
    public void setNpProviderGrpId(int npProviderGrpId) {
           this.npProviderGrpId = npProviderGrpId;
       }
    public int getNpProviderGrpId() {
           return this.npProviderGrpId;
       }      
  
    public void setNpSalesmanName(String npSalesmanName) {
           this.npSalesmanName = npSalesmanName;
       }
    public String getNpSalesmanName() {
           return this.npSalesmanName;
       }    
  
    public void setNpDealerName(String npDealerName) {
           this.npDealerName = npDealerName;
       }
    public String getNpDealerName() {
           return this.npDealerName;
       }    

    public void setNpShipToAddress1(String npShipToAddress1) {
           this.npShipToAddress1 = npShipToAddress1;
       }
    public String getNpShipToAddress1() {
           return this.npShipToAddress1;
       } 
    public void setNpShipToAddress2(String npShipToAddress2) {
           this.npShipToAddress2 = npShipToAddress2;
       }
    public String getNpShipToAddress2() {
           return this.npShipToAddress2;
       } 

    public void setNpShipToCity(String npShipToCity) {
           this.npShipToCity = npShipToCity;
       }
    public String getNpShipToCity() {
           return this.npShipToCity;
       } 

    public void setNpShipToProvince(String npShipToProvince) {
           this.npShipToProvince = npShipToProvince;
       }
    public String getNpShipToProvince() {
           return this.npShipToProvince;
       }

    public void setNpShipToState(String npShipToState) {
           this.npShipToState = npShipToState;
       }
    public String getNpShipToState() {
           return this.npShipToState;
       } 
    public void setNpShipToZip(String npShipToZip) {
           this.npShipToZip = npShipToZip;
       }
    public String getNpShipToZip() {
           return this.npShipToZip;
       }
   
    public void setNpGeneratorType(String npGeneratorType) {
           this.npGeneratorType = npGeneratorType;
       }
    public String getNpGeneratorType() {
           return this.npGeneratorType;
       }
    
    public void setNpOrigen(String npOrigen) {
           this.npOrigen = npOrigen;
       }
    public String getNpOrigen() {
           return this.npOrigen;
       }  
    public void setNpStatus(String npStatus) {
           this.npStatus = npStatus;
       }
    public String getNpStatus() {
           return this.npStatus;
       }
    public void setNpOrderCode(String npOrderCode) {
           this.npOrderCode = npOrderCode;
       }
    public String getNpOrderCode() {
           return this.npOrderCode;
       }
    public void setNpDescription(String npDescription) {
           this.npDescription = npDescription;
       }
    public String getNpDescription() {
           return this.npDescription;
       }

    public void setNpPaymentTerms(String npPaymentTerms) {
           this.npPaymentTerms = npPaymentTerms;
       }
    public String getNpPaymentTerms() {
           return this.npPaymentTerms;
       }
    public void setNpPaymentStatus(String npPaymentStatus) {
           this.npPaymentStatus = npPaymentStatus;
       }
    public String getNpPaymentStatus() {
           return this.npPaymentStatus;
       }       
    public void setNpPaymentFutureDate(Date npPaymentFutureDate) {
           this.npPaymentFutureDate = npPaymentFutureDate;
       }
    public Date getNpPaymentFutureDate() {
           return this.npPaymentFutureDate;
       }
       
    public void setNpPaymentTotal(double npPaymentTotal) {
           this.npPaymentTotal = npPaymentTotal;
       }
    public double getNpPaymentTotal() {
           return this.npPaymentTotal;
       }        
       
    public void setNpPaymentAmount(double npPaymentAmount) {
           this.npPaymentAmount = npPaymentAmount;
       }
    public double getNpPaymentAmount() {
           return this.npPaymentAmount;
       }  

    public void setNpActivationDate(Timestamp npActivationDate) {
           this.npActivationDate = npActivationDate;
       }
    public Timestamp getNpActivationDate() {
           return this.npActivationDate;
       } 
    public void setNpDeliveryDate(Timestamp npDeliveryDate) {
           this.npDeliveryDate = npDeliveryDate;
       }
    public Timestamp getNpDeliveryDate() {
           return this.npDeliveryDate;
       } 
       
    public void setNpClosedDate(Timestamp npClosedDate) {
           this.npClosedDate = npClosedDate;
       }
    public Timestamp getNpClosedDate() {
           return this.npClosedDate;
       } 

    public void setNpScheduleDate(Date npScheduleDate) {
           this.npScheduleDate = npScheduleDate;
       }
    public Date getNpScheduleDate() {
           return this.npScheduleDate;
       } 

    public void setNpScheduleStatus(int npScheduleStatus) {
           this.npScheduleStatus = npScheduleStatus;
       }
    public int getNpScheduleStatus() {
           return this.npScheduleStatus;
       } 
 
    public void setNpResponsibleAreaToGiveBack(String npResponsibleAreaToGiveBack) {
           this.npResponsibleAreaToGiveBack = npResponsibleAreaToGiveBack;
       }
    public String getNpResponsibleAreaToGiveBack() {
           return this.npResponsibleAreaToGiveBack;
       }  
 
    public void setNpDepositGuarantee(double npDepositGuarantee) {
           this.npDepositGuarantee = npDepositGuarantee;
       }
    public double getNpDepositGuarantee() {
           return this.npDepositGuarantee;
       }  

    public void setNpVoucher(String npVoucher) {
           this.npVoucher = npVoucher;
       }
    public String getNpVoucher() {
           return this.npVoucher;
       }  
       
    public void setNpExceptionApprove(int npExceptionApprove) {
           this.npExceptionApprove = npExceptionApprove;
       }
    public int getNpExceptionApprove() {
           return this.npExceptionApprove;
       } 
    public void setNpExceptionInstallation(int npExceptionInstallation) {
           this.npExceptionInstallation = npExceptionInstallation;
       }
    public int getNpExceptionInstallation() {
           return this.npExceptionInstallation;
       }  
    public void setNpExceptionPrice(int npExceptionPrice) {
           this.npExceptionPrice = npExceptionPrice;
       }
    public int getNpExceptionPrice() {
           return this.npExceptionPrice;
       }  
    public void setNpExceptionPlan(int npExceptionPlan) {
           this.npExceptionPlan = npExceptionPlan;
       }
    public int getNpExceptionPlan() {
           return this.npExceptionPlan;
       }  
    public void setNpExceptionWarrant(int npExceptionWarrant) {
           this.npExceptionWarrant = npExceptionWarrant;
       }
    public int getNpExceptionWarrant() {
           return this.npExceptionWarrant;
       } 
       
    public void setNpExceptionRevenue(int npExceptionRevenue) {
           this.npExceptionRevenue = npExceptionRevenue;
       }
    public int getNpExceptionRevenue() {
           return this.npExceptionRevenue;
       }        
    public void setNpExceptionRevenueAmount(double npExceptionRevenueAmount) {
           this.npExceptionRevenueAmount = npExceptionRevenueAmount;
       }
    public double getNpExceptionRevenueAmount() {
           return this.npExceptionRevenueAmount;
       }       
    public void setNpExcepcionBillCycle(String npExcepcionBillCycle) {
           this.npExcepcionBillCycle = npExcepcionBillCycle;
       }
    public String getNpExcepcionBillCycle() {
           return this.npExcepcionBillCycle;
       }        
    public void setNpModificationDate(Date npModificationDate) {
           this.npModificationDate = npModificationDate;
       }
    public Date getNpModificationDate() {
           return this.npModificationDate;
       }        
    public void setNpModificationBy(String npModificationBy) {
           this.npModificationBy = npModificationBy;
       }
    public String getNpModificationBy() {
           return this.npModificationBy;
       }       
    
    public void setNpCreatedDate(Timestamp npCreatedDate) {
           this.npCreatedDate = npCreatedDate;
       }
    public Timestamp getNpCreatedDate() {
           return this.npCreatedDate;
       }     
    public void setNpCreatedBy(String npCreatedBy) {
           this.npCreatedBy = npCreatedBy;
       }
    public String getNpCreatedBy() {
           return this.npCreatedBy;
       }         

    public void setNpTimeStamp(int npTimeStamp) {
           this.npTimeStamp = npTimeStamp;
       }
    public int getNpTimeStamp() {
           return this.npTimeStamp;
       }


    public void setNpType(String npType) {
        this.npType = npType;
    }

    public String getNpType() {
        return npType;
    }

    public void setNpSpecification(String npSpecification) {
        this.npSpecification = npSpecification;
    }

    public String getNpSpecification() {
        return npSpecification;
    }

    public void setNpPaymentDate(Date npPaymentDate) {
        this.npPaymentDate = npPaymentDate;
    }

    public Date getNpPaymentDate() {
        return npPaymentDate;
    }

    public void setNpSolutionName(String npSolutionName) {
        this.npSolutionName = npSolutionName;
    }

    public String getNpSolutionName() {
        return npSolutionName;
    }


  public void setNpOrderId(long npOrderId)
  {
    this.npOrderId = npOrderId;
  }


  public long getNpOrderId()
  {
    return npOrderId;
  }


  public void setNpCustomerId(long npCustomerId)
  {
    this.npCustomerId = npCustomerId;
  }


  public long getNpCustomerId()
  {
    return npCustomerId;
  }


  public void setNpSiteId(long npSiteId)
  {
    this.npSiteId = npSiteId;
  }


  public long getNpSiteId()
  {
    return npSiteId;
  }


  public void setNpSolutionId(long npSolutionId)
  {
    this.npSolutionId = npSolutionId;
  }


  public long getNpSolutionId()
  {
    return npSolutionId;
  }


  public void setNpSpecificationId(int npSpecificationId)
  {
    this.npSpecificationId = npSpecificationId;
  }


  public int getNpSpecificationId()
  {
    return npSpecificationId;
  }


  public void setNpBuildingId(long npBuildingId)
  {
    this.npBuildingId = npBuildingId;
  }


  public long getNpBuildingId()
  {
    return npBuildingId;
  }


  public void setNpRegionId(long npRegionId)
  {
    this.npRegionId = npRegionId;
  }


  public long getNpRegionId()
  {
    return npRegionId;
  }


  public void setNpShipToId(long npShipToId)
  {
    this.npShipToId = npShipToId;
  }


  public long getNpShipToId()
  {
    return npShipToId;
  }


  public void setNpDispatchPlaceId(long npDispatchPlaceId)
  {
    this.npDispatchPlaceId = npDispatchPlaceId;
  }


  public long getNpDispatchPlaceId()
  {
    return npDispatchPlaceId;
  }


  public void setNpCarrierId(long npCarrierId)
  {
    this.npCarrierId = npCarrierId;
  }


  public long getNpCarrierId()
  {
    return npCarrierId;
  }


  public void setNpGeneratorId(long npGeneratorId)
  {
    this.npGeneratorId = npGeneratorId;
  }


  public long getNpGeneratorId()
  {
    return npGeneratorId;
  }


  public void setNpTodoId(long npTodoId)
  {
    this.npTodoId = npTodoId;
  }


  public long getNpTodoId()
  {
    return npTodoId;
  }


  public void setNpWorkFlowId(long npWorkFlowId)
  {
    this.npWorkFlowId = npWorkFlowId;
  }


  public long getNpWorkFlowId()
  {
    return npWorkFlowId;
  }


  public void setNpBpelconversationid(String npBpelconversationid)
  {
    this.npBpelconversationid = npBpelconversationid;
  }


  public String getNpBpelconversationid()
  {
    return npBpelconversationid;
  }


  public void setNpBpelinstanceid(int npBpelinstanceid)
  {
    this.npBpelinstanceid = npBpelinstanceid;
  }


  public int getNpBpelinstanceid()
  {
    return npBpelinstanceid;
  }


  public void setNpBpelbackinboxs(String npBpelbackinboxs)
  {
    this.npBpelbackinboxs = npBpelbackinboxs;
  }


  public String getNpBpelbackinboxs()
  {
    return npBpelbackinboxs;
  }


  public void setNpCarrierName(String npCarrierName)
  {
    this.npCarrierName = npCarrierName;
  }


  public String getNpCarrierName()
  {
    return npCarrierName;
  }
  
    public String getNpScheduleStatusName() {
     if (this.npScheduleStatus==1)  
         return "Pendiente";
     else if (this.npScheduleStatus==0)  
         return "Actualizado";
     else
       return "";
  }
  
  public double getSaldo() {
        double fSaldo=npPaymentTotal-npPaymentAmount;
        
         return fSaldo;
  }


  public void setNpSignDate(Timestamp npSignDate)
  {
    this.npSignDate = npSignDate;
  }


  public Timestamp getNpSignDate()
  {
    return npSignDate;
  }


   public void setNpBeginPeriod(String npBeginPeriod)
   {
      this.npBeginPeriod = npBeginPeriod;
   }


   public String getNpBeginPeriod()
   {
      return npBeginPeriod;
   }


   public void setNpEndPeriod(String npEndPeriod)
   {
      this.npEndPeriod = npEndPeriod;
   }


   public String getNpEndPeriod()
   {
      return npEndPeriod;
   }


   public void setNpUserName1(String npUserName1)
   {
      this.npUserName1 = npUserName1;
   }


   public String getNpUserName1()
   {
      return npUserName1;
   }


   public void setNpUserName2(String npUserName2)
   {
      this.npUserName2 = npUserName2;
   }


   public String getNpUserName2()
   {
      return npUserName2;
   }


   public void setNpUserName3(String npUserName3)
   {
      this.npUserName3 = npUserName3;
   }


   public String getNpUserName3()
   {
      return npUserName3;
   }

  public void setNpguidegenerated(String npguidegenerated){
    this.npguidegenerated = npguidegenerated;
  }

  public String getNpguidegenerated(){
    if( MiUtil.getString(npguidegenerated).equals("S") )
        return "S";
    else
        return "";
  }

	public void setNpstatustmp(String npstatustmp){
		this.npstatustmp = npstatustmp;
	}

	public String getNpstatustmp(){
		return npstatustmp;
	}


	public void setNpDivisionId(long npDivisionId)
	{
		this.npDivisionId = npDivisionId;
	}


	public long getNpDivisionId()
	{
		return npDivisionId;
	}


	public void setNpDivisionName(String npDivisionName)
	{
		this.npDivisionName = npDivisionName;
	}


	public String getNpDivisionName()
	{
		return npDivisionName;
	}


  public void setNpScheduleDate2(Date npScheduleDate2)
  {
    this.npScheduleDate2 = npScheduleDate2;
  }


  public Date getNpScheduleDate2()
  {
    return npScheduleDate2;
  }


  public void setNpproposedid(long npproposedid)
  {
    this.npproposedid = npproposedid;
  }


  public long getNpproposedid()
  {
    return npproposedid;
  }


  public void setNpProcesoAutom(String npProcesoAutom) {
    this.npProcesoAutom = npProcesoAutom;
  }


  public String getNpProcesoAutom() {
    return npProcesoAutom;
  }


  public void setNpProviderGrpIdData(long npProviderGrpIdData)
  {
    this.npProviderGrpIdData = npProviderGrpIdData;
  }


  public long getNpProviderGrpIdData()
  {
    return npProviderGrpIdData;
  }


  public void setNpProviderGrpDataName(String npProviderGrpDataName)
  {
    this.npProviderGrpDataName = npProviderGrpDataName;
  }


  public String getNpProviderGrpDataName()
  {
    return npProviderGrpDataName;
  }

  public void setNpcontractorname (String npcontractorname){
		this. npcontractorname = npcontractorname;
	}

	public String getNpcontractorname (){
		return npcontractorname;
	}
  
  public void setNpcontractorphone (String npcontractorphone){
		this. npcontractorphone = npcontractorphone;
	}

	public String getNpcontractorphone (){
		return npcontractorphone;
	}

  public void setSalesStructureOriginalId(long lSalesStructureOriginalId){
    this.lSalesStructureOriginalId = lSalesStructureOriginalId;
  }


  public long getSalesStructureOriginalId(){
    return lSalesStructureOriginalId;
  }


  public void setNpFlagVep(int npFlagVep)
  {
    this.npFlagVep = npFlagVep;
  }


  public int getNpFlagVep()
  {
    return npFlagVep;
  }


  public void setNpNumCuotas(int npNumCuotas)
  {
    this.npNumCuotas = npNumCuotas;
  }


  public int getNpNumCuotas()
  {
    return npNumCuotas;
  }


  public void setNpAmountVep(double npAmountVep)
  {
    this.npAmountVep = npAmountVep;
  }


  public double getNpAmountVep()
  {
    return npAmountVep;
  }


  public void setNpInvoicesGenerated(String npInvoicesGenerated)
  {
    this.npInvoicesGenerated = npInvoicesGenerated;
  }


  public String getNpInvoicesGenerated()
  {
    return npInvoicesGenerated;
  }


}

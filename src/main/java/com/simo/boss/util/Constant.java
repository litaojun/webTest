package com.simo.boss.util;

public class Constant {

	  public static int CHARGELOG_STATUS_SUCCESS = 0;

	  public static int CHARGELOG_STATUS_FAILED = 1;

	  public static int CHARGELOG_STATUS_WAIT = 2;

	  public static enum RandomCodeStatus
	  {
	    VALID(0), NO_VALID(1);

	    private int value;

	    private RandomCodeStatus(int value) { this.value = value; }

	    public int getValue() {
	      return this.value;
	    }
	  }

	  public static enum MaintainType
	  {
	    DEFAULT(0), MONTH(1), DAY(2);

	    private int value;

	    private MaintainType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum ServiceUseFlag
	  {
	    NOUSED(0), USE(1);

	    private int value;

	    private ServiceUseFlag(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum TimeZone
	  {
	    WEST11(-11), WEST10(-10), WEST9(-9), WEST8(-8), WEST7(-7), WEST6(-6), WEST5(-5), 
	    WEST4(-4), WEST3(-3), WEST2(-2), WEST1(-1), GMT0(0), EAST1(1), 
	    EAST2(2), EAST3(3), EAST4(4), EAST5(5), EAST6(6), EAST7(7), EAST8(8), 
	    EAST9(9), EAST10(10), EAST11(11), EAST12(12);

	    private int value;

	    private TimeZone(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum CycleType
	  {
	    DEFAULT(0), DAY(1), MONTH(2), SEASON(3), YEAR(4);

	    private int value;

	    private CycleType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum EntityType
	  {
	    USER(0), SIM(1), BUILTSIM(2);

	    private int value;

	    private EntityType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum CdrType
	  {
	    VOICE_CALLER(1), VOICE_CALLED(2), SMS_SENDER(3), SMS_RECEIVER(4), DATA(5);

	    private int value;

	    private CdrType(int value) { this.value = value; }

	    public static CdrType valueOf(int value)
	    {
	      switch (value) {
	      case 1:
	        return VOICE_CALLER;
	      case 2:
	        return VOICE_CALLED;
	      case 3:
	        return SMS_SENDER;
	      case 4:
	        return SMS_RECEIVER;
	      case 5:
	        return DATA;
	      }
	      return null;
	    }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum QuantityUnit
	  {
	    SECOND(0), MINUTE(1), BYTE(2), MB(3), KB(4), GB(5), A(6), DEFAULT(7);

	    private int value;

	    private QuantityUnit(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum CdrStatus
	  {
	    ERROR(0), SUCCESS(1);

	    private int value;

	    private CdrStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum ChargeStatus
	  {
	    FAILED(0), SUCCESS(1);

	    private int value;

	    private ChargeStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum OrderLineStatus
	  {
	    FAILED(0), SUCCESS(1), USERERR(2), QUANTITYERR(3);

	    private int value;

	    private OrderLineStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum EventType
	  {
	    Logout(0, "退出系统"), Login(1, "登录系统"), CreateRole(2, "新增角色"), UpdRole(3, "修改角色"), 
	    DelRole(4, "删除角色"), UpdRoleRight(5, "修改角色"), CreateUser(6, "新增用户"), 
	    UpdUser(7, "修改用户"), DelUser(8, "删除用户"), ExportSim(9, "导出SIM卡"), 
	    ChangeToIdle(10, "挂起"), ChangeToForbit(10, "禁用"), ChangeToOn(10, "启用");

	    private int event;
	    private String eventName;

	    private EventType(int event, String eventName) { this.event = event;
	      this.eventName = eventName; }

	    public int getEvent()
	    {
	      return this.event;
	    }

	    public String getEventName() {
	      return this.eventName;
	    }

	    public String toString() {
	      return "[event:" + this.event + ",eventName:" + this.eventName + "]";
	    }
	  }

	  public static enum ModuleType
	  {
	    System(1, "系统操作"), SimBalanceMonitor(2, "Sim余额监控"), ResourceMonitor(3, "Sim状态监控");

	    private int module;
	    private String moduleName;

	    private ModuleType(int module, String moduleName) { this.module = module;
	      this.moduleName = moduleName; }

	    public int getModule()
	    {
	      return this.module;
	    }

	    public String getModuleName() {
	      return this.moduleName;
	    }

	    public String toString() {
	      return "[module:" + this.module + ",moduleName:" + this.moduleName + "]";
	    }
	  }

	  public static enum OperateStatus
	  {
	    Export(0), 
	    Retrieve(1);

	    private int value;

	    private OperateStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum InnerSimUseStatus
	  {
	    Unused(0), 
	    ToBeUsed(1), 
	    Used(2);

	    private int value;

	    private InnerSimUseStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum SimCardType
	  {
	    Mileage(0), 
	    comp1(1), 
	    comp2(2);

	    private int value;

	    private SimCardType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum Server
	  {
	    AAA(0), 
	    Agency(1);

	    private int value;

	    private Server(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum SendStatus
	  {
	    NoSend(0), 
	    Send(1);

	    private int value;

	    private SendStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum DefStatusType
	  {
	    effective(0, "有效"), invalidate(1, "失效");

	    private int value;
	    private String msg;

	    private DefStatusType(int value) { this.value = value; }

	    private DefStatusType(int value, String msg)
	    {
	      this.value = value;
	      this.msg = msg;
	    }

	    public int getValue() {
	      return this.value;
	    }

	    public String getMsg() {
	      return this.msg;
	    }
	  }

	  public static enum ParameterInvalid
	  {
	    invalidParams(12289), 
	    ObjectToJsonError(12292), 
	    JsonToObjectError(12293), 
	    PAPCHAP(3);

	    private int value;

	    private ParameterInvalid(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum CreditGrade
	  {
	    ONE(0), 
	    TWO(1), 
	    TRHEE(2);

	    private int value;

	    private CreditGrade(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum Grade
	  {
	    ONE(0), 
	    TWO(1), 
	    TRHEE(2);

	    private int value;

	    private Grade(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum SimType
	  {
	    Sim(0), 
	    RoamSim(1);

	    private int value;

	    private SimType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum CertificateType
	  {
	    ID(0), 
	    DrivingLicense(1), 
	    Passport(2);

	    private int value;

	    private CertificateType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum CallType
	  {
	    Calling(0), 
	    Called(1), 
	    Insensitive(2);

	    private int value;

	    private CallType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum ProductItemType
	  {
	    Day(0), 
	    Weekend(1), 
	    Holiday(2), 
	    Several(3), 
	    Time(4), 
	    Normal(5);

	    private int value;

	    private ProductItemType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum CarrierType
	  {
	    skyroam(0), 
	    carrier(1);

	    private int value;

	    private CarrierType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum AuthType
	  {
	    NO(0), 
	    PAP(1), 
	    CHAP(2), 
	    PAPCHAP(3);

	    private int value;

	    private AuthType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum CarrierStatus
	  {
	    On(0), 
	    Suspend(1), 
	    Off(2);

	    private int value;

	    private CarrierStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum BladgeStatus
	  {
	    PreAudited(0), 
	    OnStock(1), 
	    OnButEject(2), 
	    OffAudited(3), 
	    PreOffStock(3), 
	    OffStock(4);

	    private int value;

	    private BladgeStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum StatementType
	  {
	    Time(0), 
	    Daily(1), 
	    Monthly(2), 
	    Annual(3), 
	    Thailand(4);

	    private int value;

	    private StatementType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum TerminalType
	  {
	    Gmate, Gmate3;

	    private String name;

	    private TerminalType() {
	    }
	    private TerminalType(String name) {
	      this.name = name;
	    }
	  }

	  public static enum PaymentType
	  {
	    pre_payment, 
	    after_payment;

	    private String name;

	    private PaymentType() {
	    }
	    private PaymentType(String name) {
	      this.name = name;
	    }
	  }

	  public static enum NetworkStandard
	  {
	    GSM(0), 
	    WCDMA(1), 
	    CDMA(2), TCDMA(3);

	    private int value;

	    private NetworkStandard(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum NetworkType
	  {
	    G2(1), 
	    G3(2), 
	    G3G2(3);

	    private int value;

	    private NetworkType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum ProductStatus
	  {
	    PreAudited(0), 
	    OnStock(1), 
	    PreOffStock(2), 
	    OffStock(3);

	    private int value;

	    private ProductStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum LoginStatus
	  {
	    On(0), 
	    Off(1);

	    private int value;

	    private LoginStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum SimCarrierPackageStatus
	  {
	    PreOn(0), On(1), 
	    PreOff(2), 
	    Off(3);

	    private int value;

	    private SimCarrierPackageStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum CumulantType
	  {
	    Flux(0), 
	    Sms(1), 
	    Voice(2), 
	    Monetary(3);

	    private int value;

	    private CumulantType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum AssignedStatus
	  {
	    Assigned(0), 
	    Released(1);

	    private int value;

	    private AssignedStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum UserStatus
	  {
	    BeActivated(0), 
	    Trial(1), 
	    Activation(2), 
	    FrozenByFee(3), 
	    FrozenByUser(4), 
	    FrozenBySystem(5), 
	    pendingWriteOff(6), 
	    Canceled(7);

	    private int value;

	    private UserStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum Subject
	  {
	    Flux(0), 
	    Voice(1), 
	    Sms(2), 
	    Recharge(3), 
	    Refund(4), 
	    MaintanceCharge(5), 
	    Deduct(6);

	    private int value;

	    private Subject(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum LimitStatus
	  {
	    Normal(0), 
	    SpeedLimited(1), 
	    TimeLimited(2);

	    private int value;

	    private LimitStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum SimStatus
	  {
	    InStorage(0), 
	    Related(1), 
	    OnAudited(2), 
	    PreOnStock(3), 
	    OnStock(4), 

	    OffAudited(5), 
	    OffStock(6), 
	    Deleted(7);

	    private int value;

	    private SimStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum SimAccountStatus
	  {
	    On(0), 
	    Suspend(1), 
	    Canceled(2);

	    private int value;

	    private SimAccountStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum ReleaseReason
	  {
	    OVERTIME(0), 
	    ByUser(1), 
	    ByTerminal(2), 
	    ByApplyTimes(3), 
	    HearBeat(4), 
	    SimNoBalance(5), 
	    UserNoBalance(6), 
	    Eject(7);

	    private int value;

	    private ReleaseReason(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum ChargeType
	  {
	    CDR(0), 
	    MaintainceCharge(1), 
	    Refund(2), 
	    Recharge(3), 
	    Deduct(3);

	    private int value;

	    private ChargeType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum SIMCarrierPackStatus
	  {
	    On, 
	    Off;
	  }

	  public static enum CarrierPackageStatus
	  {
	    On, 
	    Off;
	  }

	  public static enum ApplySimStatus
	  {
	    On, 
	    Released;
	  }

	  public static enum MCCStatus
	  {
	    Surported, 
	    UnSurported;
	  }

	  public static enum ServiceType
	  {
	    Flux(1), 
	    Voice(2), 
	    Sms(3), 
	    FluxVoice(4), 
	    FluxSMS(5), 
	    VoiceSMS(6), 
	    All(7);

	    public int value;

	    private ServiceType(int value) { this.value = value; }
        public void setValue(int value)
        {
        	this.value = value;
        }
	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum AccountStatus
	  {
	    On(0), 
	    Frozen(1), 
	    Canceled(2);

	    private int value;

	    private AccountStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum OrderStatus
	  {
	    booked(0), 
	    canceled(1);

	    private int value;

	    private OrderStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum AssetStatus
	  {
	    On(0), Off(1);

	    private int value;

	    private AssetStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum Sex
	  {
	    MALE(0), FEMALE(1);

	    private int value;

	    private Sex(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum ProductType
	  {
	    skyroam(0), 
	    carrier(1);

	    private int value;

	    private ProductType(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum Status
	  {
	    Invalid(0), 
	    Valid(1);

	    private int value;

	    private Status(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }

	  public static enum ProductPackageStatus
	  {
	    PreAudited(0), 
	    OnStock(1), 
	    OffAudited(2), 
	    PreOffStock(3), 
	    OffStock(4);

	    private int value;

	    private ProductPackageStatus(int value) { this.value = value; }

	    public int getValue()
	    {
	      return this.value;
	    }
	  }
}

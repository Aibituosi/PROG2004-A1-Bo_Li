import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 预约类：封装预约信息，关联健康专业人员（支持任意子类医生）
 * 核心职责：存储预约数据、打印预约详情、确保数据有效性
 * 体现OOP原则：封装（私有属性+验证）、多态（关联父类引用）、内聚（仅处理预约逻辑）
 */
public class Appointment {
    // 私有实例变量（封装：仅允许通过构造或Setter修改）
    private String patientName;    // 患者姓名
    private String patientMobile;  // 患者手机号（10位数字验证）
    private String timeSlot;       // 时间槽（HH:mm格式验证）
    private HealthProfessional selectedDoctor; // 关联医生（父类引用，支持多态）

    /**
     * 默认构造函数：初始化默认值
     */
    public Appointment() {
        this.patientName = "Unknown Patient";
        this.patientMobile = "0000000000";
        this.timeSlot = "09:00";
        this.selectedDoctor = new HealthProfessional();
    }

    /**
     * 带参构造函数：初始化所有属性，严格验证参数
     * @param patientName 患者姓名（非空）
     * @param patientMobile 患者手机号（10位数字）
     * @param timeSlot 时间槽（HH:mm格式，如08:30、14:00）
     * @param selectedDoctor 关联医生（非空，支持任意子类）
     */
    public Appointment(String patientName, String patientMobile, String timeSlot, HealthProfessional selectedDoctor) {
        // 1. 验证患者姓名
        if (patientName == null || patientName.trim().isEmpty())
            throw new IllegalArgumentException("Patient name cannot be empty");
        // 2. 验证手机号（10位数字正则）
        Pattern mobilePattern = Pattern.compile("^\\d{10}$");
        Matcher mobileMatcher = mobilePattern.matcher(patientMobile);
        if (!mobileMatcher.matches())
            throw new IllegalArgumentException("Mobile must be 10 digits (e.g., 1234567890)");
        // 3. 验证时间槽（HH:mm格式正则）
        Pattern timePattern = Pattern.compile("^([01]\\d|2[0-3]):[0-5]\\d$");
        Matcher timeMatcher = timePattern.matcher(timeSlot);
        if (!timeMatcher.matches())
            throw new IllegalArgumentException("Time slot must be HH:mm (e.g., 08:30, 17:00)");
        // 4. 验证医生非空
        if (selectedDoctor == null)
            throw new IllegalArgumentException("Selected doctor cannot be null");

        // 验证通过，赋值
        this.patientName = patientName;
        this.patientMobile = patientMobile;
        this.timeSlot = timeSlot;
        this.selectedDoctor = selectedDoctor;
    }

    /**
     * 打印预约详情：调用关联医生的printDetails（多态）
     */
    public void printAppointmentDetails() {
        System.out.println("=== Appointment Details ===");
        System.out.println("Patient Name: " + patientName);
        System.out.println("Patient Mobile: " + patientMobile);
        System.out.println("Preferred Time Slot: " + timeSlot);
        System.out.println("Assigned Doctor Info:");
        selectedDoctor.printDetails(); // 多态：自动调用子类医生的printDetails
        System.out.println("===========================");
    }

    // Getter/Setter（封装：仅暴露必要访问，修改时二次验证）
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) {
        if (patientName != null && !patientName.trim().isEmpty())
            this.patientName = patientName;
        else System.out.println("Error: Patient name cannot be empty");
    }

    public String getPatientMobile() { return patientMobile; }
    public void setPatientMobile(String patientMobile) {
        Pattern mobilePattern = Pattern.compile("^\\d{10}$");
        if (mobilePattern.matcher(patientMobile).matches())
            this.patientMobile = patientMobile;
        else System.out.println("Error: Mobile must be 10 digits");
    }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) {
        Pattern timePattern = Pattern.compile("^([01]\\d|2[0-3]):[0-5]\\d$");
        if (timePattern.matcher(timeSlot).matches())
            this.timeSlot = timeSlot;
        else System.out.println("Error: Time slot must be HH:mm");
    }

    public HealthProfessional getSelectedDoctor() { return selectedDoctor; }
    public void setSelectedDoctor(HealthProfessional selectedDoctor) {
        if (selectedDoctor != null)
            this.selectedDoctor = selectedDoctor;
        else System.out.println("Error: Doctor cannot be null");
    }
}
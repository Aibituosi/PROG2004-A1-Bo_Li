/**
 * 心脏病专家子类：继承HealthProfessional，扩展心脏科特有属性
 * 核心差异：亚专业方向（subSpecialty，如介入心脏科、电生理）
 * 体现OOP原则：继承（复用基类）、多态（重写方法）、内聚（仅处理心脏科特有逻辑）
 */
public class Cardiologist extends HealthProfessional {
    // 特有属性：亚专业方向（心脏科医生的核心差异化特征）
    private String subSpecialty;

    /**
     * 默认构造函数：调用父类默认构造，初始化特有属性
     */
    public Cardiologist() {
        super();
        this.subSpecialty = "General Cardiology";
    }

    /**
     * 带参构造函数：调用父类带参构造，初始化所有属性
     * @param id 医生ID
     * @param name 医生姓名
     * @param subSpecialty 亚专业方向（如"Interventional Cardiology"）
     */
    public Cardiologist(int id, String name, String subSpecialty) {
        super(id, name, "Cardiology"); // 直接传递字符串参数
        if (subSpecialty == null || subSpecialty.trim().isEmpty())
            throw new IllegalArgumentException("Sub-specialty cannot be empty");
        this.subSpecialty = subSpecialty;
    }

    /**
     * 重写printDetails：添加心脏科医生特有信息，体现多态
     */
    @Override
    public void printDetails() {
        super.printDetails(); // 复用父类打印逻辑
        System.out.println("Health Professional Type: Cardiologist");
        System.out.println("Sub-specialty: " + subSpecialty);
        System.out.println("----------------------------------------");
    }

    // Getter/Setter（封装特有属性）
    public String getSubSpecialty() { return subSpecialty; }
    public void setSubSpecialty(String subSpecialty) {
        if (subSpecialty != null && !subSpecialty.trim().isEmpty())
            this.subSpecialty = subSpecialty;
        else System.out.println("Error: Sub-specialty cannot be empty");
    }
}
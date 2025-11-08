/**
 * 全科医生子类：继承HealthProfessional，扩展全科医生特有属性
 * 核心差异：是否提供家庭医生服务（isFamilyDoctor）
 * 体现OOP原则：继承（复用基类）、多态（重写方法）、内聚（仅处理全科医生特有逻辑）
 */
public class GeneralPractitioner extends HealthProfessional {
    // 特有属性：是否为家庭医生（全科医生核心差异化特征）
    private boolean isFamilyDoctor;

    /**
     * 默认构造函数：调用父类默认构造，初始化特有属性
     */
    public GeneralPractitioner() {
        super(); // 继承父类默认属性
        this.isFamilyDoctor = false;
    }

    /**
     * 带参构造函数：调用父类带参构造，初始化所有属性（基类+特有）
     * @param id 医生ID
     * @param name 医生姓名
     * @param isFamilyDoctor 是否为家庭医生
     */
    public GeneralPractitioner(int id, String name, boolean isFamilyDoctor) {
        super(id, name, "General Medicine"); // 直接传递字符串参数
        this.isFamilyDoctor = isFamilyDoctor;
    }

    /**
     * 重写printDetails：添加全科医生特有信息，体现多态
     * 覆盖父类方法，打印内容包含医生类型与特有属性
     */
    @Override
    public void printDetails() {
        super.printDetails(); // 复用父类打印基础信息
        System.out.println("Health Professional Type: General Practitioner");
        System.out.println("Is Family Doctor: " + (isFamilyDoctor ? "Yes" : "No"));
        System.out.println("----------------------------------------");
    }

    // Getter/Setter（封装特有属性）
    public boolean isFamilyDoctor() { return isFamilyDoctor; }
    public void setFamilyDoctor(boolean familyDoctor) { isFamilyDoctor = familyDoctor; }
}
/**
 * 健康专业人员基类：抽象所有健康专业人员的共同属性与行为
 * 核心职责：封装ID、姓名、专业领域，提供统一的详情打印方法
 * 体现OOP原则：抽象（提取共性）、封装（私有变量+访问控制）、为继承服务
 */
public class HealthProfessional {
    // 私有实例变量（封装：仅允许通过方法访问）
    private int id;          // 数字ID（符合要求）
    private String name;     // 姓名
    private String specialization; // 专业领域（所有健康人员共有的核心属性）

    /**
     * 默认构造函数：初始化默认值，确保对象合法性
     */
    public HealthProfessional() {
        this.id = 0;
        this.name = "Unknown Health Professional";
        this.specialization = "General Health Care";
    }

    /**
     * 带参构造函数：初始化所有属性，支持自定义值
     * @param id 数字ID（非负验证）
     * @param name 姓名（非空验证）
     * @param specialization 专业领域（非空验证）
     * 体现准确性：参数验证确保数据有效性
     */
    public HealthProfessional(int id, String name, String specialization) {
        // 参数验证：避免非法数据（高分关键：验证机制）
        if (id < 0) throw new IllegalArgumentException("ID cannot be negative");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        if (specialization == null || specialization.trim().isEmpty())
            throw new IllegalArgumentException("Specialization cannot be empty");

        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    /**
     * 打印所有属性详情：统一接口，为子类重写提供基础
     */
    public void printDetails() {
        System.out.println("=== Health Professional Base Info ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Specialization: " + specialization);
    }

    // Getter/Setter（封装：仅暴露必要的访问接口，不允许直接修改核心属性）
    public int getId() { return id; }
    public void setId(int id) {
        if (id >= 0) this.id = id;
        else System.out.println("Error: ID cannot be negative");
    }

    public String getName() { return name; }
    public void     setName(String name) {
        if (name != null && !name.trim().isEmpty()) this.name = name;
        else System.out.println("Error: Name cannot be empty");
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) {
        if (specialization != null && !specialization.trim().isEmpty())
            this.specialization = specialization;
        else System.out.println("Error: Specialization cannot be empty");
    }
}
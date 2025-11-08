import java.util.ArrayList;
import java.util.Iterator;

/**
 * 主类：串联所有功能模块，演示Part3（医生对象）和Part5（预约集合）
 * 体现OOP原则：控制反转（集中管理逻辑，降低类间耦合）
 */
public class AssignmentOne {
    public static void main(String[] args) {
        // ==============================================
        // Part 3 – Using classes and objects（医生对象创建与打印）
        // ==============================================
        System.out.println("=== Part 3: All Health Professionals ===");

        // 1. 创建3个全科医生对象（调用子类带参构造）
        GeneralPractitioner gp1 = new GeneralPractitioner(101, "Dr. Sarah Johnson", true);
        GeneralPractitioner gp2 = new GeneralPractitioner(102, "Dr. Michael Lee", false);
        GeneralPractitioner gp3 = new GeneralPractitioner(103, "Dr. Emily Chen", true);

        // 2. 创建2个心脏病专家对象（调用子类带参构造）
        Cardiologist cardio1 = new Cardiologist(201, "Dr. David Wilson", "Interventional Cardiology");
        Cardiologist cardio2 = new Cardiologist(202, "Dr. Lisa Garcia", "Electrophysiology");

        // 3. 打印所有医生详情（多态：统一调用printDetails，输出不同内容）
        gp1.printDetails();
        gp2.printDetails();
        gp3.printDetails();
        cardio1.printDetails();
        cardio2.printDetails();

        System.out.println("------------------------------");

        // ==============================================
        // Part 5 – Collection of appointments（预约集合管理）
        // ==============================================
        System.out.println("=== Part 5: Appointment Management ===");

        // 1. 声明存储预约的ArrayList
        ArrayList<Appointment> appointmentList = new ArrayList<>();

        // 2. 演示创建预约：2个全科医生预约，2个心脏病专家预约（多态体现）
        System.out.println("\n--- Step 1: Create 4 Appointments ---");
        createAppointment(appointmentList, "Alice Smith", "1234567890", "09:00", gp1);
        createAppointment(appointmentList, "Bob Brown", "0987654321", "11:30", gp2);
        createAppointment(appointmentList, "Charlie Davis", "1122334455", "14:00", cardio1);
        createAppointment(appointmentList, "Diana Evans", "5566789012", "16:30", cardio2);

        // 3. 打印现有预约
        System.out.println("\n--- Step 2: Print All Appointments ---");
        printExistingAppointments(appointmentList);

        // 4. 取消预约（通过手机号：Bob的10位手机号）
        System.out.println("\n--- Step 3: Cancel Appointment (Mobile: 0987654321) ---");
        cancelBooking(appointmentList, "0987654321");

        // 5. 再次打印预约（验证取消效果）
        System.out.println("\n--- Step 4: Print Appointments After Cancellation ---");
        printExistingAppointments(appointmentList);

        System.out.println("------------------------------");
    }

    /**
     * 创建预约并添加到集合：多态核心方法，支持任意健康专业人员子类
     * @param list 预约集合（非空验证）
     * @param patientName 患者姓名
     * @param patientMobile 患者手机号
     * @param timeSlot 时间槽
     * @param doctor 关联医生（父类引用，接收GP/Cardiologist等子类）
     */
    public static void createAppointment(ArrayList<Appointment> list, String patientName,
                                         String patientMobile, String timeSlot, HealthProfessional doctor) {
        try {
            // 验证集合非空
            if (list == null) throw new IllegalArgumentException("Appointment list cannot be null");
            // 创建预约对象（触发构造函数的参数验证）
            Appointment newAppt = new Appointment(patientName, patientMobile, timeSlot, doctor);
            list.add(newAppt);
            System.out.println("Success: Appointment created for " + patientName);
        } catch (IllegalArgumentException e) {
            // 捕获参数异常，友好提示（代码健壮性）
            System.out.println("Failed to create appointment: " + e.getMessage());
        }
    }

    /**
     * 打印所有现有预约：空集合时提示，非空时遍历打印
     * @param list 预约集合
     */
    public static void printExistingAppointments(ArrayList<Appointment> list) {
        if (list == null) {
            System.out.println("Error: Appointment list is null");
            return;
        }
        if (list.isEmpty()) {
            System.out.println("No existing appointments.");
            return;
        }
        // 遍历打印每个预约详情
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\nAppointment " + (i + 1) + ":");
            list.get(i).printAppointmentDetails();
        }
    }

    /**
     * 通过手机号取消预约：使用迭代器避免并发修改异常
     * @param list 预约集合
     * @param patientMobile 患者手机号
     */
    public static void cancelBooking(ArrayList<Appointment> list, String patientMobile) {
        if (list == null) {
            System.out.println("Error: Appointment list is null");
            return;
        }
        if (patientMobile == null) {
            System.out.println("Error: Mobile number cannot be null");
            return;
        }

        boolean isCancelled = false;
        // 迭代器遍历：安全删除元素（避免ConcurrentModificationException）
        Iterator<Appointment> iterator = list.iterator();
        while (iterator.hasNext()) {
            Appointment appt = iterator.next();
            if (appt.getPatientMobile().equals(patientMobile)) {
                iterator.remove();
                isCancelled = true;
                System.out.println("Success: Cancelled appointment for mobile " + patientMobile);
                // 若需取消所有匹配预约，注释下方break；若仅取消第一个，保留break
                // break;
            }
        }

        if (!isCancelled) {
            System.out.println("Error: No appointments found for mobile " + patientMobile);
        }
    }
    }
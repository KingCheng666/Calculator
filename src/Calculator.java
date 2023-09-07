import java.math.BigDecimal;
import java.util.Stack;

/**
 * @description: 计算器工具，实现两个数的加、减、乘、除运算，并可以进行undo和redo操作
 * @author: jinyc
 * @since: 2023-09-06 20:36
 **/

public class Calculator {
    private double result;
    private Stack<Double> history;
    private Stack<Double> redoHistory;

    public Calculator() {
        result = 0.0; //计算结果
        history = new Stack<>(); //存放历史操作记录
        redoHistory = new Stack<>(); //存放需要重做的操作记录
    }

    public double getResult() {
        return result;
    }

    /**
     * 两数相加
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return void
     */
    public void add(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(num1));
        BigDecimal b2 = new BigDecimal(String.valueOf(num2));
        result = b1.add(b2).doubleValue();
        history.push(result);
        redoHistory.clear();
    }

    /**
     * 两数相减
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return void
     */
    public void subtract(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(num1));
        BigDecimal b2 = new BigDecimal(String.valueOf(num2));
        result = b1.subtract(b2).doubleValue();
        history.push(result);
        redoHistory.clear();
    }

    /**
     * 两数相乘
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return void
     */
    public void multiply(double num1, double num2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(num1));
        BigDecimal b2 = new BigDecimal(String.valueOf(num2));
        result = b1.multiply(b2).doubleValue();
        history.push(result);
        redoHistory.clear();
    }

    /**
     * 两数相除
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return void
     */
    public void divide(double num1, double num2) {
        if (num2 != 0) {
            BigDecimal b1 = new BigDecimal(String.valueOf(num1));
            BigDecimal b2 = new BigDecimal(String.valueOf(num2));
            result = b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP).doubleValue(); //保留3位小数，四舍五入
            history.push(result);
            redoHistory.clear();
        } else {
            System.out.println("除数不能为0！");
        }
    }

    /**
     * 撤销操作（undo）
     *
     * @param
     * @return void
     */
    public void undo() {
        if (!history.empty()) {
            double lastResult = history.pop();
            redoHistory.push(lastResult);
            if (!history.empty()) {
                result = history.peek();
            } else {
                result = 0.0;
            }
        } else {
            System.out.println("无法执行undo操作！");
        }
    }

    /**
     * 重置操作（redo）
     *
     * @param
     * @return void
     */
    public void redo() {
        if (!redoHistory.empty()) {
            double nextResult = redoHistory.pop();
            history.push(nextResult);
            result = nextResult;
        } else {
            System.out.println("无法执行redo操作！");
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        calculator.add(2, 3);
        System.out.println("两数相加，运算结果为：" + calculator.getResult());

        calculator.subtract(10, 2);
        System.out.println("两数相减，运算结果为：" + calculator.getResult());

        calculator.multiply(1.2, 6.6);
        System.out.println("两数相乘，运算结果为：" + calculator.getResult());

        calculator.divide(2, 3);
        System.out.println("两数相除，运算结果为：" + calculator.getResult());

        calculator.undo();
        System.out.println("undo运算结果为：" + calculator.getResult());

        calculator.redo();
        System.out.println("redo运算结果为：" + calculator.getResult());

    }
}

package view;

import controller.Controller;
import domain.PrgState;
import domain.expressions.*;
import domain.statements.*;
import utils.IStackADT;
import utils.MyLibStack;
import utils.exceptions.*;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexandra on 02/11/2015.
 */
public class UI {
    Controller ctrl; // contains a reference to the controller
    Scanner input; // class that allows input from the user

    // controller
    public UI(Controller ctrl) {
        this.ctrl = ctrl;
        input = new Scanner(System.in);
    }

    public void mainMenu() {
        System.out.println("______________________________\n");
        System.out.println("\tWELCOME TO THE INTERPRETER !\n");
        System.out.println("______________________________\n");
        System.out.println("\t||  Main Menu  ||\n");
        System.out.println("\t1 - Step by Step Execution\n");
        System.out.println("\t2 - Complete Execution\n");
        System.out.println("\t3 - Display Current Program State\n");
        System.out.println("\t4 - Execute an implicit example.\n");
        System.out.println("\t5 - Serialize program to file.\n");
        System.out.println("\t6 - Deserialize program from file.\n");
        System.out.println("\t7 - Save current program state to file.\n");
        System.out.println("\tquit - Terminate the program.");
        System.out.println("\t0 - INPUT PROGRAM: \n");


    }

    /*
     * sub-menu for the types of statements
     */
    public void StmtSubmenu() {
        System.out.println("Please choose the type of statement: ");
        System.out.println("1 - Compound Statement\n");
        System.out.println("2 - Assignment Statement\n");
        System.out.println("3 - Conditional Statement\n");
        System.out.println("4 - Print Statement\n");
        System.out.println("5 - While Statement\n");
        System.out.println("6 - If Then Skip Statement\n");
        System.out.println("7 - Switch Statement\n");
        System.out.println("8 - Skip Statement\n");
        System.out.println("9 - Heap Allocation Statement\n");
        System.out.println("10 - Heap Writing Statement\n");
        System.out.println("11 - Fork Statement\n");
    }

    /*
     * sub-menu for the types of expressions
     */
    public void ExprSubmenu() {
        System.out.println("Please choose the type of expression: ");
        System.out.println("1 - Constant Expression\n");
        System.out.println("2 - Variable Expression\n");
        System.out.println("3 - Arithmetic Expression\n");
        System.out.println("4 - Relational Operator Expression\n");
        System.out.println("5 - Logical Operator Expression\n");
        System.out.println("6 - Read Expression\n");
        System.out.println("7 - Heap Reading Expression\n");

    }

    /*
     * recursive method that prompts the user to select the type of statement
     * pre: s - statement of type IStmt
     * post: returns s as one of the defined statement types
     * throws an exception if the user inputs an invalid choice
     */
    public IStmt chooseStmt() throws InvalidInputException {

        StmtSubmenu();
        IStmt s;

        String opt = input.next();
        if (opt.equals("1")) {  // COMPOUND STATEMENT part
            System.out.println("First Statement pls: \n");
            IStmt first = chooseStmt();
            System.out.println("\nSecond Statement pls: \n");
            IStmt snd = chooseStmt();

            s = new CompStmt(first, snd);
        }

        else if (opt.equals("2")) {  // ASSIGNMENT STATEMENT part
            String var;
            Exp expr;

            System.out.println("\nEnter the variable name: \n");
            var = input.next();
            expr = chooseExpr();

            s = new AssignStmt(var, expr);
        }

        else if (opt.equals("3")) {  // CONDITIONAL STATEMENT part

            System.out.println("Condition: ");
            Exp cond = chooseExpr();

            System.out.println("\nGive the THEN statement:\n");
            IStmt t = chooseStmt();

            System.out.println("\nGive the ELSE statement:\n");
            IStmt e = chooseStmt();

            s = new IfStmt(cond, t, e);
        }

        else if (opt.equals("4")) {  // PRINT STATEMENT
            Exp expr = chooseExpr();
            s = new PrintStmt(expr);
        }
        else if (opt.equals("5")) {  // WHILE STATEMENT
            Exp expr = chooseExpr();
            IStmt st = chooseStmt();
            s = new WhileStmt(expr,st);
        }

        else if (opt.equals("6")) {
            System.out.println("Choose the conditional expression: ");
            Exp expr = chooseExpr();
            System.out.println("Give the THEN statement: ");
            IStmt st = chooseStmt();
            s = new IfThenStmt(expr, st);

        }

        else if (opt.equals("7")) {
            // Switch (varname) case (expression1): stmt1 case (expression2): stmt2 default: stmt3
            System.out.println("\nEnter the variable name: \n");
            Exp var = chooseExpr();
            System.out.println("\nGive the 1st expression:\n");
            Exp exp1 = chooseExpr();
            System.out.println("\nGive the 1st statement:\n");
            IStmt st1 = chooseStmt();
            System.out.println("\nGive the 2nd expression:\n");
            Exp exp2 = chooseExpr();
            System.out.println("\nGive the 2nd statement:\n");
            IStmt st2 = chooseStmt();
            System.out.println("\nGive the default statement:\n");
            IStmt st3 = chooseStmt();

            s = new SwitchStmt(var, exp1, exp2, st1, st2, st3);
        }

        else if (opt.equals("8")) {  // SKIP STATEMENT
            s = new SkipStmt();
        }

        else if (opt.equals("9")) {  // HEAP ALLOCATION STATEMENT
            String var;
            Exp expr;
            System.out.println("\nEnter the variable name: \n");
            var = input.next();
            expr = chooseExpr();
            s = new AllocHeapStmt(var, expr);
        }

        else if (opt.equals("10")) {  // HEAP WRITING STATEMENT
            String var;
            Exp expr;
            System.out.println("\nEnter the variable name: \n");
            var = input.next();
            expr = chooseExpr();
            s = new WriteHeapStmt(var, expr);
        }

        else if (opt.equals("11")) {  // FORK STATEMENT
            System.out.println("\nGive the statement:\n");
            IStmt st = chooseStmt();
            s = new ForkStmt(st);
        }


        else throw new InvalidInputException("Invalid Input!! Please try again.");

        return s;
    }


    /*
    * recursive method that prompts the user to select the type of expression
    * pre: expr - expression of type Exp
    * post: returns expr as one of the defined expression types
    * throws an exception if the user inputs an invalid choice
    */
    public Exp chooseExpr() throws InvalidInputException {
        ExprSubmenu();
        Exp expr = null;
        String opt = input.next();
        if (opt.equals("1")) {   // CONSTANT EXPRESSION part
            System.out.println("\nEnter the value of the expression: \n");
            int value = input.nextInt();
            expr = new ConstExp(value);
        }

        else if (opt.equals("2")) {   // VARIABLE EXPRESSION part
            System.out.println("\nEnter the name of the variable: \n");
            String var = input.next();
            expr = new VarExp(var);
        }

        else if (opt.equals("3")) {  // ARITHMETIC EXPRESSION
            System.out.println("\nEnter the expression on the left: \n");
            Exp exp1 = chooseExpr();
            System.out.println("\nEnter the operator: \n");
            System.out.println("\t1 for +  |  2 for -  |   3 for *  |   4 for /");
            int op = input.nextInt();
            System.out.println("\nEnter the expression on the right: \n");
            Exp exp2 = chooseExpr();

            expr = new ArithExp(exp1, exp2, op);
        }

        else if (opt.equals("4")) {  // RELATIONAL OPERATOR EXPRESSION
            System.out.println("\nEnter the expression on the left: \n");
            Exp exp1 = chooseExpr();
            System.out.println("\nEnter the operator: \n");
            System.out.println("\t<, <=, ==, !=, >, >=");
            String op = input.next();
            System.out.println("\nEnter the expression on the right: \n");
            Exp exp2 = chooseExpr();

            expr = new RelOpExpr(exp1, exp2, op);
        }


        else if (opt.equals("5")) {  // LOGICAL OPERATOR EXPRESSION

            System.out.println("\nLOGICAL OPERATOR EXPRESSION: \n");
            System.out.println("\n1. AND, OR operators \n");
            System.out.println("\n2. NOT operator \n");
            String op = input.next();
            if (op.equals("1")) {
                System.out.println("\nEnter the expression on the left: \n");
                Exp exp1 = chooseExpr();
                System.out.println("\nEnter the logical operator: \n");
                System.out.println("\t&&, ||");
                String option = input.next();
                System.out.println("\nEnter the expression on the right: \n");
                Exp exp2 = chooseExpr();
                expr = new LogicalOpExpr(exp1, exp2, option);
            }
            else if (op.equals("2")) {
                System.out.println("\nEnter the NOT (!) operator: \n");
                String option = input.next();
                System.out.println("\nEnter the expression: \n");
                Exp exp = chooseExpr();
                expr = new NotExpr(exp, option);
            }

        }

        else if (opt.equals("6")) {  // READ EXPRESSION
            expr = new ReadExpr();
        }

        else if (opt.equals("7")) {  // HEAP READING EXPRESSION
            String var;
            System.out.println("\nEnter the variable name: \n");
            var = input.next();
            expr = new ReadHeapExp(var);
        }

        else throw new InvalidInputException("Invalid Input!! Try again pls.");

        return expr;
    }


    /*
     * ui method for adding a new statement
     * pre: st - statement of type IStmt
     * post: the statement st is added
     */
    public void addStmtUI() throws Exception {
        IStmt st;
        st = chooseStmt();
        ctrl.addStatement(st);

        System.out.println("Statement has been added.");

    }


    /*
     * ui method for the one step execution of a program
     *
     */
    public void oneStepUI() {
        List<PrgState> state = ctrl.removeCompletedPrg(ctrl.getPrograms());
        try {
            if (ctrl.getPrograms() != null) {
                ctrl.oneStepForAllPrg(state);

            }
        }
        catch(InterruptedException msg) {
                System.out.println(msg.getMessage());
            } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*
     * ui method for the all step execution of a program
     */
    public void allStepUI() {
        // if (ctrl.getPrograms() != null) {
            try {
                ctrl.allStep();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }


    /*
     * ui method for printing the state of a program
     * it returns a string with the representation
     */
    public void printStateUI() {
        String str = ctrl.toString();
        System.out.print(str);
    }


    /*
     * method that runs the user interface
     * the user inputs a string, opt and based on the option, it calls the corresponding
     * function from the controller
     */
    public void runUI() {
        Scanner input = new Scanner(System.in);
        String opt = "";

        while (!opt.equals("quit")) {
            mainMenu();
            opt = input.next();
            if (opt.equals("0")) {
                try {
                    addStmtUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (opt.equals("1")) {
                System.out.println("Choose how to display the result: ");
                System.out.println("\tsave to file (f)  OR  display to console (c)");
                String next = input.next();
                if (next.equals("file") || next.equals("f")) {
                    ctrl.setFlag("file");
                    oneStepUI();
                    ctrl.getPrograms().forEach(ctrl::writeToFile);
                }
                else if (next.equals("c") || next.equals("console")) {
                    ctrl.setFlag("console");
                    oneStepUI();
                    ctrl.getPrograms().forEach(p -> System.out.println(p.toString()));
                }

            }

            else if (opt.equals("2")) {

                System.out.println("Choose how to display the result: ");
                System.out.println("\tsave to file (f)  OR  display to console (c)");
                String next = input.next();
                if (next.equals("file") || next.equals("f")) {
                    ctrl.setFlag("file");
                    allStepUI();
                    ctrl.getPrograms().forEach(p -> ctrl.writeToFile(p));
                }

                else if (next.equals("c") || next.equals("console")) {
                    ctrl.setFlag("console");
                    allStepUI();
                    ctrl.getPrograms().forEach(p -> System.out.println(p.toString()));
                }


                /*if (ctrl.getFlag()) // if the debug flag is set to ON (true), it prints the program state
                    printStateUI();
                 */

            }

            else if (opt.equals("3")) {
                printStateUI();
            }

            else if (opt.equals("4")) {
                addHeapEx();
            }

            else if (opt.equals("5")) {
                ctrl.serializareObj();
            }

            else if (opt.equals("6")) {
                ctrl.deserializareObj();
            }

            else if (opt.equals("7")) {
                ctrl.getPrograms().forEach(p -> ctrl.writeToFile(p));
            }

            else {
                System.out.println("Invalid Input!! Please try again. ");
            }
        }
        input.close();
    }


    public void addEx4() {
        // SKIP STATEMENT
        // Example: v=6; skip;if v then print(v);
        Exp e1 = new ConstExp(6);
        IStmt s1 = new AssignStmt("v",e1);
        Exp e2 = new VarExp("v");
        IStmt s2 = new SkipStmt();
        IStmt s3 = new PrintStmt(e2);
        IStmt s4 = new IfThenStmt(e2,s3);
        IStmt s5 = new CompStmt(s2, s4);
        IStmt s6 = new CompStmt(s1,s5);

        try {
            ctrl.addStatement(s6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addHeapEx() {
        // v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a)) at the end of execution
        // Heap={1->20, 2->30}, SymTable={v->1, a->2} and Out={2,30}
        Exp e1 = new ConstExp(10);
        Exp e2 = new ConstExp(20);
        Exp e3 = new ConstExp(22);
        Exp e4 = new ConstExp(30);
        Exp e5 = new VarExp("a");
        IStmt s1 = new AssignStmt("v", e1);
        IStmt s2 = new AllocHeapStmt("v", e2);
        IStmt s3 = new AllocHeapStmt("a", e3);
        IStmt s4 = new WriteHeapStmt("a", e4);
        IStmt s5 = new PrintStmt(e5);
        Exp e6 = new ReadHeapExp("a");
        IStmt s6 = new PrintStmt(e6);
        IStmt s7 = new CompStmt(s5,s6);
        IStmt s8 = new CompStmt(s4,s7);
        IStmt s9 = new CompStmt(s3,s8);
        IStmt s10 = new CompStmt(s2,s9);
        IStmt s11 = new CompStmt(s1,s10);

        try {
            ctrl.addStatement(s11);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void addEx1() {  // SWITCH STATEMENT EXAMPLE
        // v=2; a=v+10; switch (v) case(a-11): a=a+1; print(a) case(a-10): a=a+2; print(a) default: print(a)
        Exp e1 = new ConstExp(2);
        Exp e2 = new VarExp("v");
        Exp e3 = new ConstExp(10);
        Exp e4 = new ArithExp(e2, e3, 1);
        IStmt s1 = new AssignStmt("v", e1);
        IStmt s2 = new AssignStmt("a", e4);

        Exp e5 = new VarExp("a");
        Exp e6 = new ConstExp(11);
        Exp exp1 = new ArithExp(e5, e6, 2);
        Exp e7 = new ConstExp(1);
        Exp e8 = new ArithExp(e5, e7, 1);
        IStmt s3 = new AssignStmt("a", e8);
        IStmt s4 = new PrintStmt(e5);
        IStmt st1 = new CompStmt(s3, s4);
        Exp exp2 = new ArithExp(e5, e3, 2);
        Exp e9 = new ArithExp(e5, e1, 1);
        IStmt s5 = new AssignStmt("a", e9);
        IStmt st2 = new CompStmt(s5, s4);
        IStmt s6 = new SwitchStmt(e2, exp1, exp2, st1, st2, s4);
        IStmt s7 = new CompStmt(s2, s6);
        IStmt s8 = new CompStmt(s1, s7);

        try {
            ctrl.addStatement(s8);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void addEx2() {  // relational operators example
        Exp exp1 = new ConstExp(2);
        Exp exp2 = new ConstExp(5);
        Exp e2 = new VarExp("v");
        IStmt s1 = new AssignStmt("v", exp1);

        Exp expr2 = new ArithExp(exp1,exp2,1);
        Exp e3 = new RelOpExpr(e2, expr2, "==");
        IStmt s3 = new PrintStmt(e3);

        Exp exp4 = new ConstExp(4);
        Exp exp5 = new ConstExp(8);
        Exp e4 = new ArithExp(exp4, exp5, 3);
        Exp e5 = new RelOpExpr(e2,e4, "!=");
        Exp exp6 = new ConstExp(10);
        Exp e6 = new RelOpExpr(e2, exp6,"<=" );
        IStmt st2 = new PrintStmt(e6);
        Exp e7 = new RelOpExpr(e2, exp1,"==" );
        IStmt st3 = new PrintStmt(e7);
        IStmt st1 = new IfStmt(e5, st2, st3);

        IStmt st4 = new CompStmt(s1,st1);

        try {
            ctrl.addStatement(st4);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addEx3() {
        //Example: v=6; (while (v-4) print(v);v=v-1);print(v)
        Exp e1 = new ConstExp(6);
        IStmt s1 = new AssignStmt("v", e1);
        Exp e2 = new VarExp("v");
        Exp e3 = new ConstExp(4);
        Exp e4 = new ArithExp(e2, e3, 2);
        IStmt s2 = new PrintStmt(e2);
        Exp e5 = new ConstExp(1);
        Exp e6 = new ArithExp(e2, e5, 2);
        IStmt s3 = new AssignStmt("v", e6);
        IStmt s4 = new CompStmt(s2,s3);
        IStmt s5 = new WhileStmt(e4, s4);
        IStmt s6 = new CompStmt(s5, s2);
        IStmt s7 = new CompStmt(s1, s6);

        try {
            ctrl.addStatement(s7);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addExample() {
        Exp exp1 = new ConstExp(2);
        Exp exp2 = new ConstExp(2);
        Exp e = new ConstExp(3);
        Exp exp3 = new VarExp("a");
        Exp exp4 = new ArithExp(exp1, exp2, 2);
        IStmt s1 = new AssignStmt("a", exp4);
        Exp e2 = new VarExp("v");

        IStmt s2 = new AssignStmt("v", exp2);
        IStmt s3 = new AssignStmt("v", e);
        IStmt s4 = new IfStmt(exp3, s2, s3);
        IStmt s5 = new PrintStmt(e2);
        IStmt s6 = new CompStmt(s4, s5);
        IStmt s7 = new CompStmt(s1 ,s6);

        try {
            ctrl.addStatement(s7);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        /*
        System.out.println("Statement was added.");
        System.out.println(ctrl.toString());

        oneStepUI();
        printStateUI();
        oneStepUI();
        printStateUI();
        oneStepUI();
        printStateUI();
        oneStepUI();
        printStateUI();
        oneStepUI();
        printStateUI();
        oneStepUI();
        printStateUI();
        */

    }
}


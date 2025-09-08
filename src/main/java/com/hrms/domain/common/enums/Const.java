package com.hrms.domain.common.enums;

import java.time.LocalDate;

public class Const {
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    public static final int NO_ASSIGNED_JOB = 0;// 未分配工作
    public static final int NO_ASSIGNED_DEPARTMENT = 0;// 未分配部门

    public static final int ROOT_DEPARTMENT = 0;// 顶级部门
    public static final int NO_SUPERVISOR = 0;// 无主管

    public static final LocalDate NOT_RESIGNED_DATA = null;// 未离职日期

    public static final int WORKING = 1;//工作状态为在职
    public static final int NOT_WORKING = 0;//工作状态为离职

    public static final int SALARY_MORE = 20000;//薪资水平最高
    public static final int SALARY_HIGH = 12000;//薪资水平较高
    public static final int SALARY_MiDDLE = 8000;//薪资水平适中
    public static final int SALARY_LOW = 5000;
    public static final int SALARY_FEW = 0;

    public static final int PERMANENT_WORKER = 1;//正式工
    public static final int TEMPORARY_WORKER = 2;//临时工
    public static final int INTERN_WORKER = 3;//实习生

    public static final int MALE = 1;//性别为男
    public static final int FEMALE = 2;//性别为女

    public static final int MIDDLE_AGE = 35;//中年分界线
    public static final int OLD_AGE = 50;//老年分界线
}

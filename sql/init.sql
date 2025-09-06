drop database hrms;

create database hrms;

use hrms;

create table department
(
    id         int auto_increment
        primary key,
    name       varchar(192)  not null,/*部门名称，不可重复*/
    supervisor int default 0 not null,/*如果有，则为worker表的主键id，没有则默认为0，表示该部门没有主管*/
    uper_id    int default 0 null/*为上级部门id，即department表的主键id，此处如果为0则代表公司总部，因此只能有一个部门的uper_id为0*/
);

create table job
(
    id           int auto_increment
        primary key,
    name         varchar(192)  not null,/*岗位名称，不可重复*/
    demand       text          not null,/*该岗位对员工的要求，*/
    type         tinyint       not null,/*1为正式工，2为临时工，3为实习生，只能为这三个数字*/
    salary       int           not null,/*该岗位的基础工资*/
    overtime_pay int           not null,/*加班工资，每加班满一小时，就多加多少钱，加班工资只能在20到100之间*/
    introduction text          not null,/*岗位工作内容的介绍*/
    total_worker int default 0 null,
    check (`type` in (1, 2, 3)),
    check ((`salary` >= 2000) and (`salary` <= 60000)),
    check ((`overtime_pay` >= 20) and (`overtime_pay` <= 100))
);

create table lesson
(
    id        int auto_increment
        primary key,
    name      varchar(192)  not null,/*岗位课程名称，不可重复*/
    schedule  date          not null,/*课程的开始时间，格式为yyyy-MM-dd*/
    time_plan int           not null,/*培训持续天数*/
    address   varchar(384)  not null,/*培训地点，模拟数据省略区县乡镇街道，即地址必须是广东省某市某地某楼几0几*/
    info      varchar(3072) not null,/*培训课程的内容介绍*/
    check (char_length(`info`) between 1 and 1024)
);

/*log表不需要任何模拟数据的sql插入语句*/
create table log
(
    id            int auto_increment
        primary key,
    operate_user  int          not null,
    operate_time  datetime     not null,
    class_name    varchar(128) not null,
    method_name   varchar(128) not null,
    method_params text         null,
    return_value  text         null,
    cost_time     int          not null
);

create index operate_user
    on log (operate_user);

/*在程序中，该表的数据通过spring schedule定时任务在每天的凌晨0点自动生成，因此只需从2025年8月1日（包含8月1日）开始生成至今（2025年9月6日），每天插入一条数据即可。部分字段没有详细注释说明的，请根据实际可能出现的情况自拟*/
create table payment
(
    id              int auto_increment
        primary key,
    profit          decimal(12, 3) default 0.000 not null,/*本日收益*/
    tax             decimal(12, 3) default 0.000 null,/*缴纳税收*/
    social_security decimal(12, 3) default 0.000 null,/*缴纳社保*/
    water           decimal(12, 3) default 0.000 null,/*水费*/
    electricity     decimal(12, 3) default 0.000 null,/*电费*/
    salary          decimal(12, 3) default 0.000 null,/*根据salary表中该天（即满足payment.date = salary.settlement_time）的salary列计算而得，对该天的薪资数据的salary求和，求和结果为本表的salary字段值*/
    outlay          decimal(12, 3) default 0.000 null,/*总支出数，即不计算profit，将tax，social_security, water, electricity, salary这几个值求和而得*/
    date            date                         not null/*日期格式为yyyy-MM-dd*/
);

/*存储某次结算薪资的数据，即薪资条*/
create table salary
(
    id              int auto_increment
        primary key,
    job_id          int           not null,/*职业id，指向job表的主键id*/
    worker_id       int           not null,/*员工id，指向worker表的主键id*/
    settlement_time date          not null,/*薪资条创建日期*/
    cycle           tinyint       not null,/*薪资的结算办法，1为一月结算一次，2为一日结算一次，3为一年结算一次*/
    salary_offset   int default 0 not null,/*员工可能因为加班或得到年终奖而得到更多的钱，这个列存储的就是这部分多发的薪资数据*/
    salary          int           not null,/*员工的实际薪资，由基础薪资（定义在job表的salary中）加上salary_offset计算而得*/
    check (`cycle` in (1, 2, 3)),
    check ((`salary_offset` >= 20) and (`salary_offset` <= 2000)),
    check ((`salary` >= 2000) and (`salary` <= 60000))
);

create index job_id
    on salary (job_id);

create index worker_id
    on salary (worker_id);

create table trainee
(
    id        int auto_increment
        primary key,
    les_id    int not null,/*指向lesson表，存储的是lesson课程的主键id*/
    worker_id int not null/*指向worker表，该字段存储的是worker表中数据的主键id*/
);

create index les_id
    on trainee (les_id);

create index worker_id
    on trainee (worker_id);

/*你不需要为该表（user）编写插入模拟数据的sql语句*/
create table user
(
    id       int auto_increment
        primary key,
    name     varchar(192)                   not null,
    password varchar(64) default '88888888' not null,
    worker   int                            null
);

/*员工信息表，不包括ceo*/
create table worker
(
    id              int auto_increment
        primary key,
    name            varchar(192)  not null,/*员工姓名*/
    gender          tinyint       not null,/*员工性别*/
    degree          tinyint       not null,/*员工学历，分五种，1为本科以上学历，2为本科，3为大专，4为高中，5为初中及以下学历*/
    birthday        date          not null,/*员工生日*/
    social_security tinyint       not null,/*员工的社保缴纳情况，由于是本项目并非企业级需要投入实际生产的项目，故在此作了简化设计，1为已缴清，0为未缴*/
    rp_record       text          null,/*奖惩情况说明，没有则为null*/
    transfer        text          null,/*调动记录说明，没有则为null*/
    work_status     tinyint       not null,/*0为离职，1为在任*/
    certificate     text          null,/*获取过的技能证书说明，没有则为null*/
    labor_status    tinyint       not null,/*1为正式工，2为临时工，3为实习生*/
    job             int default 0 not null,/*指向job表的主键id，用于给员工分配工作，不考虑兼任岗位*/
    department      int default 0 not null,/*指向department表的主键id，用于给员工分配其隶属的部门*/
    onboarding_time date          not null,/*入职时间，从2025年8月1日至今（2025年9月6日），模拟数据中的大部分员工须在2025年8月15日前入职，此后至今陆陆续续有员工入职或离职*/
    departure_time  date          null,/*离职时间，从2025年8月15日以后开始，陆续有员工离职，大部分员工此值为null，即在岗*/
    check (`social_security` in (0, 1)),
    check (`work_status` in (0, 1)),
    check (`labor_status` in (1, 2, 3))
);

create index job
    on worker (job);


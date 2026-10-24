CREATE TABLE APP_USER(
    user_id NUMBER PRIMARY KEY NOT NULL,
    username VARCHAR2(20) NOT NULL,
    password VARCHAR2(20) NOT NULL,
    role VARCHAR2(20) NOT NULL   
);

CREATE TABLE BUDGET(
    budget_id NUMBER PRIMARY KEY NOT NULL,
    budget_period VARCHAR2(50) NOT NULL,
    created_by NUMBER NOT NULL,
    FOREIGN KEY(created_by) REFERENCES APP_USER(user_id)
);

CREATE TABLE BUDGET_DETAILS(
    detail_id NUMBER PRIMARY KEY NOT NULL,
    budget_id NUMBER NOT NULL,
    category VARCHAR2(20) NOT NULL,
    amount NUMBER(10,2) NOT NULL,
    FOREIGN KEY(budget_id) REFERENCES BUDGET(budget_id)
);

CREATE TABLE EXPENSE(
    expense_id NUMBER PRIMARY KEY NOT NULL,
    expense_date DATE NOT NULL,
    category VARCHAR2(50) NOT NULL,
    amount NUMBER(10,2) NOT NULL,
    description VARCHAR2(50) NOT NULL,
    recorded_by NUMBER NOT NULL,
    budget_detail_id NUMBER NOT NULL,

    FOREIGN KEY(recorded_by) REFERENCES APP_USER(user_id),
    FOREIGN KEY(budget_detail_id) REFERENCES BUDGET_DETAILS(detail_id)
);

CREATE TABLE REVENUE(
    revenue_id NUMBER PRIMARY KEY NOT NULL,
    revenue_date DATE NOT NULL,
    category VARCHAR2(50) NOT NULL,
    amount NUMBER(10,2) NOT NULL,
    description VARCHAR2(255) NOT NULL,
    recorded_by NUMBER NOT NULL,

    FOREIGN KEY(recorded_by) REFERENCES APP_USER(user_id)
);

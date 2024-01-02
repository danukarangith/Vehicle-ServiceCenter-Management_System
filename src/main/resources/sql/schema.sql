 DROP DATABASE IF EXISTS  LEO_AUTO_CARE_SERVICE;
 CREATE DATABASE IF NOT EXISTS LEO_AUTO_CARE_SERVICE;

 USE LEO_AUTO_CARE_SERVICE;




 CREATE TABLE admin (
                        email VARCHAR(30) NOT NULL,
                        user_name VARCHAR(20)  NOT NULL,
                        user_pas VARCHAR(20) NOT NULL,
                        PRIMARY KEY (user_name)
 );

 CREATE TABLE stock (
                        stock_id VARCHAR(20) NOT NULL,
                        stock_name VARCHAR(20) NOT NULL,
                        stock_price VARCHAR(20),
                        stock_remain  varchar(20),
                        user_name VARCHAR(20),
                        PRIMARY KEY (stock_id),
                        FOREIGN KEY (user_name) REFERENCES admin(user_name)ON DELETE CASCADE ON UPDATE CASCADE
 );
 CREATE TABLE stock_supplier (
                                 stock_supplier_id VARCHAR(20) NOT NULL,
                                 sup_name VARCHAR(20) NOT NULL,
                                 sup_address VARCHAR(20) NOT NULL,
                                 PRIMARY KEY (stock_supplier_id)
 );

 CREATE TABLE stocks_detail (

                                stock_id VARCHAR(20) NOT NULL,
                                stock_supplier_id VARCHAR(20) NOT NULL,
                                sup_date VARCHAR(10) NOT NULL,
                                FOREIGN KEY (stock_id) REFERENCES stock(stock_id)ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY (stock_supplier_id) REFERENCES stock_supplier(stock_supplier_id)ON DELETE CASCADE ON UPDATE CASCADE
 );



 CREATE TABLE employee (
                           emp_name VARCHAR(20) NOT NULL,
                           employee_id VARCHAR(20) NOT NULL,
                           emp_address VARCHAR(20),
                           emp_contact_Number VARCHAR(20),
                           emp_type VARCHAR(20),
                           user_name VARCHAR(20),
                           PRIMARY KEY (emp_name),
                           FOREIGN KEY (user_name) REFERENCES admin(user_name)ON DELETE CASCADE ON UPDATE CASCADE
 );

 CREATE TABLE customer (
                           customer_id VARCHAR(20) NOT NULL,
                           cu_name VARCHAR(20) NOT NULL,
                           cu_address VARCHAR(30) NOT NULL,
                           cu_email VARCHAR(30) NOT NULL,
                           cu_contact VARCHAR(10) NOT NULL,
                           PRIMARY KEY (cu_name)
 );

 CREATE TABLE vehicle (
                          vehicle_id VARCHAR(20) NOT NULL,
                          customer_name VARCHAR(20) NOT NULL ,
                          veh_num VARCHAR(10) NOT NULL,
                          veh_brand VARCHAR(10) NOT NULL,
                          veh_type VARCHAR(10) NOT NULL,
                          FOREIGN KEY (customer_name) REFERENCES customer(cu_name)ON DELETE CASCADE ON UPDATE CASCADE,
                          PRIMARY KEY (veh_num)
 );

 CREATE TABLE service_detail (
                                 service_id VARCHAR(20) NOT NULL ,
                                 customer_name VARCHAR(20) NOT NULL,
                                 vehicle_number VARCHAR(20) NOT NULL,
                                 service_date VARCHAR(20) NOT NULL,
                                 service_time VARCHAR(10) NOT NULL,
                                 details VARCHAR(60) NOT NULL ,
                                 employee_name VARCHAR(20) NOT NULL,
                                 PRIMARY KEY (service_id),
                                 FOREIGN KEY (customer_name) REFERENCES customer(cu_name)ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY (vehicle_number) REFERENCES vehicle(veh_num)ON DELETE CASCADE ON UPDATE CASCADE,
                                 FOREIGN KEY (employee_name) REFERENCES employee(emp_name)
 );


 CREATE TABLE payment (
                          payment_id VARCHAR(20) NOT NULL ,
                          customer_name VARCHAR(20) NOT NULL ,
                          payment_amount VARCHAR(20) NOT NULL ,
                          payment_method VARCHAR(255) NOT NULL,
                          detail VARCHAR(40),
                          PRIMARY KEY (payment_id),
                          FOREIGN KEY (customer_name) REFERENCES customer(cu_name)ON DELETE CASCADE ON UPDATE CASCADE
 );

 CREATE TABLE reservation (
                              reservation_id VARCHAR(20) NOT NULL,
                              reservation_email VARCHAR(30) NOT NULL ,
                              reservation_vehNum VARCHAR(20) NOT NULL ,
                              reservation_date VARCHAR(20) NOT NULL ,
                              reservation_time VARCHAR(20) NOT NULL,
                              PRIMARY KEY (reservation_id)
 );


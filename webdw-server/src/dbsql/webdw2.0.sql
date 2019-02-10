/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : webdw2.0

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-02-11 07:52:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus` (
  `emp_id` int(11) NOT NULL,
  `bonus_date` date NOT NULL,
  `bonus_amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bonus
-- ----------------------------
INSERT INTO `bonus` VALUES ('1', '2018-12-11', '1222');

-- ----------------------------
-- Table structure for col
-- ----------------------------
DROP TABLE IF EXISTS `col`;
CREATE TABLE `col` (
  `TNAME` varchar(20) NOT NULL,
  `COLNO` int(11) NOT NULL,
  `CNAME` varchar(20) NOT NULL,
  `COLTYPE` varchar(20) NOT NULL,
  `WIDTH` int(11) DEFAULT NULL,
  PRIMARY KEY (`TNAME`,`COLNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of col
-- ----------------------------
INSERT INTO `col` VALUES ('product', '1', 'id', 'int', '4');
INSERT INTO `col` VALUES ('product', '2', 'name', 'varchar2', '20');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `DEPT_ID` int(3) DEFAULT NULL,
  `DEPT_NAME` varchar(40) DEFAULT NULL,
  `DEPT_HEAD_ID` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('100', 'RAndD', '501');
INSERT INTO `department` VALUES ('200', 'Sales', '902');
INSERT INTO `department` VALUES ('300', 'Finance', '1293');
INSERT INTO `department` VALUES ('400', 'Marketing', '1576');
INSERT INTO `department` VALUES ('500', 'Shipping', '703');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `EMP_ID` int(3) DEFAULT NULL,
  `MANAGER_ID` int(3) DEFAULT NULL,
  `EMP_FNAME` varchar(20) DEFAULT NULL,
  `EMP_LNAME` varchar(20) DEFAULT NULL,
  `DEPT_ID` int(3) DEFAULT NULL,
  `STREET` varchar(40) DEFAULT NULL,
  `CITY` varchar(20) DEFAULT NULL,
  `STATE` varchar(4) DEFAULT NULL,
  `ZIP_CODE` varchar(9) DEFAULT NULL,
  `PHONE` varchar(10) DEFAULT NULL,
  `STATUS` varchar(1) DEFAULT NULL,
  `SS_NUMBER` varchar(11) DEFAULT NULL,
  `SALARY` decimal(16,3) DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `TERMINATION_DATE` date DEFAULT NULL,
  `BIRTH_DATE` date DEFAULT NULL,
  `BENE_HEALTH_INS` varchar(1) DEFAULT NULL,
  `BENE_LIFE_INS` varchar(1) DEFAULT NULL,
  `BENE_DAY_CARE` varchar(1) DEFAULT NULL,
  `SEX` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('101', null, 'aaa', 'aaa', '200', null, null, '2', '100001', '1391057777', 'A', null, null, null, null, null, 'Y', null, null, 'F');
INSERT INTO `employee` VALUES ('103', null, 'bbb', 'bbb', '500', null, null, '4', '100004', '1391234567', 'T', null, null, null, null, null, 'Y', null, null, 'F');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` char(15) NOT NULL,
  `description` char(30) NOT NULL,
  `prod_size` char(18) NOT NULL,
  `color` char(6) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` bigint(20) NOT NULL,
  `picture_name` char(255) DEFAULT NULL,
  `catalog_picture` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('300', 'Tee Shirt111', 'Tank Top3333', 'Small', 'White', '29', '9', 'tshirtw.bmp', '');
INSERT INTO `product` VALUES ('302', 'Tee Shirt222', 'Crew Neck', 'One size fits all', 'Black', '72', '14', 'tshirtb.bmp', '');
INSERT INTO `product` VALUES ('400', 'Baseball Cap', 'Cotton Cap', 'One size fits all', 'Black', '92', '9', 'capb.bmp', '');
INSERT INTO `product` VALUES ('401', 'Baseball Cap', 'Wool cap', 'One size fits all', 'White', '12', '10', 'capw.bmp', '');
INSERT INTO `product` VALUES ('500', 'Visor', 'Cloth Visor', 'One size fits all', 'White', '36', '7', 'visorw.bmp', '');
INSERT INTO `product` VALUES ('501', 'Visor', 'Plastic Visor', 'One size fits all', 'Black', '28', '7', 'visorb.bmp', '');
INSERT INTO `product` VALUES ('600', 'Sweatshirt', 'Hooded Sweatshirt', 'Large', 'Green', '39', '24', 'sshirtg.bmp', '');
INSERT INTO `product` VALUES ('601', 'Sweatshirt', 'Zipped Sweatshirt', 'Large', 'Blue', '32', '24', 'sshirtb.bmp', '');
INSERT INTO `product` VALUES ('700', 'Shorts', 'Cotton Shorts', 'Medium', 'Black', '80', '15', 'shortsb.bmp', '');

-- ----------------------------
-- Table structure for tab
-- ----------------------------
DROP TABLE IF EXISTS `tab`;
CREATE TABLE `tab` (
  `TNAME` varchar(20) NOT NULL,
  `TABTYPE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`TNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab
-- ----------------------------
INSERT INTO `tab` VALUES ('product', 'TABLE');

-- ----------------------------
-- Table structure for test_text
-- ----------------------------
DROP TABLE IF EXISTS `test_text`;
CREATE TABLE `test_text` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `define` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_text
-- ----------------------------
INSERT INTO `test_text` VALUES ('1', 'release 7;\r\ndatawindow(units=0 timer_interval=0 color=16777215 processing=1 HTMLDW=no print.documentname=\"\" print.orientation = 0 print.margin.left = 110 print.margin.right = 110 print.margin.top = 96 print.margin.bottom = 96 print.paper.source = 0 print.paper.size = 0 print.prompt=no print.buttons=no print.preview.buttons=no grid.lines=0 )\r\nheader(height=68 color=\"536870912\" )\r\nsummary(height=0 color=\"536870912\" )\r\nfooter(height=0 color=\"536870912\" )\r\ndetail(height=80 color=\"536870912\" )\r\ntable(column=(type=long update=yes updatewhereclause=yes key=yes name=emp_id dbname=\"bonus.emp_id\" )\r\n column=(type=date update=yes updatewhereclause=yes key=yes name=bonus_date dbname=\"bonus.bonus_date\" initial=\"today\" )\r\n column=(type=decimal(2) update=yes updatewhereclause=yes name=bonus_amount dbname=\"bonus.bonus_amount\" )\r\n retrieve=\"PBSELECT(TABLE(NAME=~\"bonus~\") COLUMN(NAME=~\"bonus.emp_id~\")COLUMN(NAME=~\"bonus.bonus_date~\")COLUMN(NAME=~\"bonus.bonus_amount~\"))\" update=\"bonus\" updatewhere=1 updatekeyinplace=no )\r\ntext(band=header alignment=\"2\" text=\"Emp Id\" border=\"0\" color=\"0\" x=\"9\" y=\"8\" height=\"52\" width=\"178\"  name=emp_id_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ntext(band=header alignment=\"2\" text=\"Bonus Date\" border=\"0\" color=\"0\" x=\"197\" y=\"8\" height=\"52\" width=\"302\"  name=bonus_date_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ntext(band=header alignment=\"2\" text=\"Bonus Amount\" border=\"0\" color=\"0\" x=\"507\" y=\"8\" height=\"52\" width=\"370\"  name=bonus_amount_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=1 alignment=\"1\" tabsequence=10 border=\"0\" color=\"0\" x=\"9\" y=\"8\" height=\"64\" width=\"178\" format=\"[general]\"  name=emp_id edit.limit=0 edit.case=any edit.focusrectangle=no edit.autoselect=yes edit.autohscroll=yes  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=2 alignment=\"0\" tabsequence=20 border=\"0\" color=\"0\" x=\"197\" y=\"8\" height=\"64\" width=\"302\" format=\"[general]\"  name=bonus_date edit.limit=0 edit.case=any edit.focusrectangle=no edit.autoselect=yes edit.autohscroll=yes  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=3 alignment=\"1\" tabsequence=30 border=\"0\" color=\"0\" x=\"507\" y=\"8\" height=\"64\" width=\"370\" format=\"[general]\"  name=bonus_amount edit.name=\"###,###.00\" editmask.mask=\"###,###.00\" editmask.focusrectangle=no  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\nhtmltable(border=\"1\" )\r\nhtmlgen(clientevents=\"1\" clientvalidation=\"1\" clientcomputedfields=\"1\" clientformatting=\"0\" clientscriptable=\"0\" generatejavascript=\"1\" )\r\n ');

-- ----------------------------
-- Table structure for ts_coldef
-- ----------------------------
DROP TABLE IF EXISTS `ts_coldef`;
CREATE TABLE `ts_coldef` (
  `table_id` int(11) NOT NULL,
  `table_name` char(40) NOT NULL,
  `col_id` int(11) NOT NULL,
  `col_name` char(40) NOT NULL,
  `col_cname` char(40) NOT NULL,
  `col_datatype` char(20) NOT NULL,
  `col_length` int(11) NOT NULL,
  `col_ispk` int(11) NOT NULL,
  PRIMARY KEY (`table_id`,`col_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ts_coldef
-- ----------------------------
INSERT INTO `ts_coldef` VALUES ('0', 'TS_coldef', '1', 'table_id', '数据表编号', 'INT', '0', '1');
INSERT INTO `ts_coldef` VALUES ('0', 'TS_coldef', '2', 'table_name', '数据表名称', 'CHAR', '40', '0');
INSERT INTO `ts_coldef` VALUES ('0', 'TS_coldef', '3', 'col_id', '列编号', 'INT', '0', '0');
INSERT INTO `ts_coldef` VALUES ('0', 'TS_coldef', '4', 'col_name', '列名称', 'CHAR', '40', '0');
INSERT INTO `ts_coldef` VALUES ('0', 'TS_coldef', '5', 'col_cname', '列中文名称', 'CHAR', '40', '0');
INSERT INTO `ts_coldef` VALUES ('0', 'TS_coldef', '6', 'col_datatype', '列数据类型', 'CHAR', '20', '0');
INSERT INTO `ts_coldef` VALUES ('0', 'TS_coldef', '7', 'col_length', '列宽度', 'INT', '0', '0');
INSERT INTO `ts_coldef` VALUES ('0', 'TS_coldef', '8', 'col_name', '是否主键', 'INT', '0', '0');
INSERT INTO `ts_coldef` VALUES ('1', 'T_BOOK', '1', 'book_id', '书本编号', 'CHAR', '20', '1');
INSERT INTO `ts_coldef` VALUES ('1', 'T_BOOK', '2', 'book_name', '书本名称', 'CHAR', '10', '0');
INSERT INTO `ts_coldef` VALUES ('1', 'T_BOOK', '3', 'book_memo', '书本备注', 'CHAR', '20', '0');
INSERT INTO `ts_coldef` VALUES ('1', 'T_BOOK', '4', 'book_others', '其它', 'CHAR', '255', '0');
INSERT INTO `ts_coldef` VALUES ('2', 'T_KJZT', '1', 'kjzt_code', '会计主体编号', 'CHAR', '20', '1');
INSERT INTO `ts_coldef` VALUES ('2', 'T_KJZT', '2', 'kjzt_name', '会计主体名称', 'CHAR', '40', '0');
INSERT INTO `ts_coldef` VALUES ('2', 'T_KJZT', '3', 'kjzt_memo', '会计主体备注', 'CHAR', '20', '0');
INSERT INTO `ts_coldef` VALUES ('3', 'TS_DivDef', '1', 'div_id', 'div编号', 'CHAR', '10', '1');
INSERT INTO `ts_coldef` VALUES ('3', 'TS_DivDef', '2', 'div_type', 'div类型', 'CHAR', '10', '0');
INSERT INTO `ts_coldef` VALUES ('3', 'TS_DivDef', '3', 'div_content', 'div内容', 'CHAR', '400', '0');
INSERT INTO `ts_coldef` VALUES ('3', 'TS_DivDef', '4', 'div_memo', 'div备注', 'CHAR', '100', '0');

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `book_id` int(11) NOT NULL,
  `book_name` char(10) NOT NULL,
  `book_memo` char(20) NOT NULL,
  `book_others` char(255) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------

-- ----------------------------
-- Table structure for t_datawindow
-- ----------------------------
DROP TABLE IF EXISTS `t_datawindow`;
CREATE TABLE `t_datawindow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dw_name` varchar(100) NOT NULL,
  `dw_define` text NOT NULL,
  `dw_selectsql` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_datawindow
-- ----------------------------
INSERT INTO `t_datawindow` VALUES ('1', 'd_products', '$PBExportHeader$d_products.srd\r\n$PBExportComments$List of all products\r\nrelease 7;\r\ndatawindow(units=0 timer_interval=0 color=16777215 processing=1 HTMLDW=no print.documentname=\"\" print.orientation = 0 print.margin.left = 110 print.margin.right = 110 print.margin.top = 96 print.margin.bottom = 96 print.paper.source = 0 print.paper.size = 0 print.prompt=no print.buttons=no print.preview.buttons=no grid.lines=0 )\r\nheader(height=84 color=\"536870912\" )\r\nsummary(height=0 color=\"536870912\" )\r\nfooter(height=0 color=\"536870912\" )\r\ndetail(height=108 color=\"536870912\" )\r\ntable(column=(type=char(30) update=yes updatewhereclause=yes name=description dbname=\"product.description\" )\r\n column=(type=char(15) update=yes updatewhereclause=yes name=name dbname=\"product.name\" )\r\n column=(type=long update=yes updatewhereclause=yes key=yes name=id dbname=\"product.id\" )\r\n column=(type=char(18) update=yes updatewhereclause=yes name=prod_size dbname=\"product.prod_size\" )\r\n column=(type=char(6) update=yes updatewhereclause=yes name=color dbname=\"product.color\" )\r\n column=(type=long update=yes updatewhereclause=yes name=quantity dbname=\"product.quantity\" )\r\n column=(type=decimal(2) update=yes updatewhereclause=yes name=unit_price dbname=\"product.unit_price\" )\r\n column=(type=char(255) update=yes updatewhereclause=yes name=picture_name dbname=\"product.picture_name\" )\r\n retrieve=\"PBSELECT( VERSION(400) TABLE(NAME=~\"product~\" ) COLUMN(NAME=~\"product.description~\") COLUMN(NAME=~\"product.name~\") COLUMN(NAME=~\"product.id~\") COLUMN(NAME=~\"product.prod_size~\") COLUMN(NAME=~\"product.color~\") COLUMN(NAME=~\"product.quantity~\") COLUMN(NAME=~\"product.unit_price~\") COLUMN(NAME=~\"product.picture_name~\")) \" update=\"product\" updatewhere=1 updatekeyinplace=no )\r\ntext(band=header alignment=\"2\" text=\"Product Description\" border=\"0\" color=\"0\" x=\"9\" y=\"8\" height=\"52\" width=\"576\"  name=description_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ntext(band=header alignment=\"2\" text=\"Product Name\" border=\"0\" color=\"0\" x=\"594\" y=\"8\" height=\"52\" width=\"379\"  name=name_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ntext(band=header alignment=\"2\" text=\"ID\" border=\"0\" color=\"0\" x=\"983\" y=\"8\" height=\"52\" width=\"183\"  name=id_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ntext(band=header alignment=\"2\" text=\"Size\" border=\"0\" color=\"0\" x=\"1175\" y=\"8\" height=\"52\" width=\"800\"  name=prod_size_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ntext(band=header alignment=\"2\" text=\"Color\" border=\"0\" color=\"0\" x=\"1984\" y=\"8\" height=\"52\" width=\"229\"  name=color_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ntext(band=header alignment=\"2\" text=\"Qty\" border=\"0\" color=\"0\" x=\"2222\" y=\"8\" height=\"52\" width=\"183\"  name=quantity_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ntext(band=header alignment=\"2\" text=\"Unit Price\" border=\"0\" color=\"0\" x=\"2414\" y=\"8\" height=\"52\" width=\"279\"  name=unit_price_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ntext(band=header alignment=\"0\" text=\"Picture Name\" border=\"0\" color=\"0\" x=\"2702\" y=\"8\" height=\"52\" width=\"398\"  name=picture_name_t  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"700\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\nline(band=header x1=\"37\" y1=\"76\" x2=\"3392\" y2=\"76\"  name=l_2 pen.style=\"0\" pen.width=\"5\" pen.color=\"0\"  background.mode=\"2\" background.color=\"12632256\" )\r\ncolumn(band=detail id=1 alignment=\"0\" tabsequence=10 border=\"0\" color=\"0\" x=\"9\" y=\"8\" height=\"60\" width=\"576\" format=\"[general]\"  name=description  tag=\"Describes what the product is\" edit.limit=30 edit.case=any edit.focusrectangle=no edit.autoselect=yes edit.autohscroll=yes  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=2 alignment=\"0\" tabsequence=20 border=\"0\" color=\"0\" x=\"594\" y=\"8\" height=\"60\" width=\"379\" format=\"[general]\"  name=name  tag=\"Name of the product\" edit.limit=15 edit.case=any edit.focusrectangle=no edit.autoselect=yes edit.autohscroll=yes  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=3 alignment=\"1\" tabsequence=30 border=\"0\" color=\"0\" x=\"983\" y=\"8\" height=\"60\" width=\"183\" format=\"[general]\"  name=id  tag=\"Unique Identification Code of the product\" edit.limit=0 edit.case=any edit.focusrectangle=no edit.autoselect=yes edit.autohscroll=yes  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=4 alignment=\"0\" tabsequence=40 border=\"0\" color=\"0\" x=\"1175\" y=\"8\" height=\"60\" width=\"800\" format=\"[general]\"  name=prod_size  tag=\"Measurements of the product\" edit.limit=0 edit.case=any edit.focusrectangle=no edit.autoselect=no  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=5 alignment=\"0\" tabsequence=50 border=\"0\" color=\"0\" x=\"1984\" y=\"8\" height=\"60\" width=\"229\" format=\"[general]\"  name=color  tag=\"Color of the product\" edit.limit=0 edit.case=any edit.focusrectangle=no edit.autoselect=no  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=6 alignment=\"1\" tabsequence=60 border=\"0\" color=\"0\" x=\"2222\" y=\"8\" height=\"60\" width=\"183\" format=\"[general]\"  name=quantity  tag=\"Amount of the product in stock\" edit.limit=0 edit.case=any edit.focusrectangle=no edit.autoselect=yes edit.autohscroll=yes  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=7 alignment=\"1\" tabsequence=70 border=\"0\" color=\"0\" x=\"2414\" y=\"8\" height=\"60\" width=\"279\" format=\"$#,##0.00;[RED]($#,##0.00)\"  name=unit_price  tag=\"Unit price per product\" edit.limit=0 edit.case=any edit.autoselect=yes  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\ncolumn(band=detail id=8 alignment=\"0\" tabsequence=80 border=\"0\" color=\"0\" x=\"2702\" y=\"8\" height=\"68\" width=\"398\" format=\"[general]\"  name=picture_name  tag=\".bmp filename that shows what the product looks like\" bitmapname=yes edit.limit=255 edit.case=any edit.focusrectangle=no edit.autoselect=yes edit.autohscroll=yes  font.face=\"MS Sans Serif\" font.height=\"-8\" font.weight=\"400\"  font.family=\"2\" font.pitch=\"2\" font.charset=\"0\" background.mode=\"1\" background.color=\"536870912\" )\r\nline(band=detail x1=\"37\" y1=\"92\" x2=\"3401\" y2=\"92\"  name=l_1 pen.style=\"0\" pen.width=\"5\" pen.color=\"0\"  background.mode=\"2\" background.color=\"12632256\" )\r\nhtmltable(border=\"1\" )\r\nhtmlgen(clientevents=\"1\" clientvalidation=\"1\" clientcomputedfields=\"1\" clientformatting=\"0\" clientscriptable=\"0\" generatejavascript=\"1\" )\r\n  ', 'select description,name,id,prod_size,color,quantity,unit_price,picture_name from product');

-- ----------------------------
-- Table structure for t_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock` (
  `STOCK_ID` varchar(10) DEFAULT NULL,
  `STOCK_NAME` varchar(10) CHARACTER SET gbk DEFAULT NULL,
  `STOCK_PRICE` decimal(8,2) DEFAULT NULL,
  `MEMO` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stock
-- ----------------------------
INSERT INTO `t_stock` VALUES ('600028', 'abcdefg', '7.03', 'aaa');
INSERT INTO `t_stock` VALUES ('000725', 'aaa', '3.45', 'aaa');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for webdw_dw_define
-- ----------------------------
DROP TABLE IF EXISTS `webdw_dw_define`;
CREATE TABLE `webdw_dw_define` (
  `dw_name` varchar(100) NOT NULL COMMENT '数据窗口的名称',
  `dw_define` tinytext NOT NULL COMMENT '数据窗口定义的字符串',
  PRIMARY KEY (`dw_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of webdw_dw_define
-- ----------------------------

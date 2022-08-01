/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  scott
 * Created: Dec 30, 2021
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  scott
 * Created: May 22, 2021
 */

CREATE TABLE if not exists SrcVal (
  `SrcCde` int  NOT NULL,
  `SrcTxt` varchar(45) NOT NULL,
  PRIMARY KEY (`SrcCde`)
);

CREATE TABLE if not exists Quote (
  `QuoteNum` int  NOT NULL AUTO_INCREMENT,
  `SrcCde` int  NOT NULL,
  `QuoteTxt` text NOT NULL,
  `CanUse` enum('Y','N') NOT NULL
);

CREATE TABLE if not exists `QuoteOfTheDay` (
  `QotdNum` int  NOT NULL,
  `QuoteDate` date NOT NULL,
  `QuoteNum` int  NOT NULL,
  PRIMARY KEY (`QotdNum`)
);

insert into SrcVal(SrcCde, SrcTxt) values(1, 'test 1');
insert into SrcVal(SrcCde, SrcTxt) values(2, 'test 2');
insert into SrcVal(SrcCde, SrcTxt) values(3, 'I am first');


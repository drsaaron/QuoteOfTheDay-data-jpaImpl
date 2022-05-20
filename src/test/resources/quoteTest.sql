/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  scott
 * Created: May 22, 2021
 */

CREATE TABLE SrcVal (
  `SrcCde` int  NOT NULL,
  `SrcTxt` varchar(45) NOT NULL,
  PRIMARY KEY (`SrcCde`)
);

CREATE TABLE Quote (
  `QuoteNum` int  NOT NULL ,
  `SrcCde` int  NOT NULL,
  `QuoteTxt` text NOT NULL,
  `CanUse` enum('Y','N') NOT NULL,
  PRIMARY KEY (`QuoteNum`)
) ;

insert into SrcVal(SrcCde, SrcTxt) values(1, 'test 1');
insert into SrcVal(SrcCde, SrcTxt) values(2, 'test 2');

insert into Quote(QuoteNum, SrcCde, QuoteTxt, CanUse) values(1, 1, 'My first quote', 'Y');
insert into Quote(QuoteNum, SrcCde, QuoteTxt, CanUse) values(2, 1, 'My second quote', 'Y');
insert into Quote(QuoteNum, SrcCde, QuoteTxt, CanUse) values(3, 2, 'A third quote', 'N');
insert into Quote(QuoteNum, SrcCde, QuoteTxt, CanUse) values(4, 2, 'A fourth quote', 'Y');

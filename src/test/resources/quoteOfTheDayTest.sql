/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  scott
 * Created: May 22, 2021
 */


insert into SrcVal(SrcCde, SrcTxt) values(1, 'test 1');
insert into SrcVal(SrcCde, SrcTxt) values(2, 'test 2');

insert into Quote(QuoteNum, SrcCde, QuoteTxt, CanUse) values(1, 1, 'My first quote', 'Y');
insert into Quote(QuoteNum, SrcCde, QuoteTxt, CanUse) values(2, 1, 'My second quote', 'Y');
insert into Quote(QuoteNum, SrcCde, QuoteTxt, CanUse) values(3, 2, 'A third quote', 'N');
insert into Quote(QuoteNum, SrcCde, QuoteTxt, CanUse) values(4, 2, 'A fourth quote', 'Y');

insert into QuoteOfTheDay(QotdNum, QuoteDate, QuoteNum) values(1, '2020-01-01', 1);
insert into QuoteOfTheDay(QotdNum, QuoteDate, QuoteNum) values(2, '2020-01-02', 2);
insert into QuoteOfTheDay(QotdNum, QuoteDate, QuoteNum) values(3, '2020-01-03', 3);
insert into QuoteOfTheDay(QotdNum, QuoteDate, QuoteNum) values(4, '2020-01-04', 4);
insert into QuoteOfTheDay(QotdNum, QuoteDate, QuoteNum) values(5, '2020-01-05', 1);

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.data.access.impl.spring.jpa;

import com.blazartech.products.qotdp.data.Quote;
import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import com.blazartech.products.qotdp.data.QuoteOfTheDayHistory;
import com.blazartech.products.qotdp.data.QuoteSourceCode;
import com.blazartech.products.qotdp.data.access.QuoteOfTheDayDAL;
import com.blazartech.products.qotdp.data.access.impl.QuoteOfTheDayDALBaseImpl;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.QuoteData;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.QuoteOfTheDayData;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.entity.SrcValData;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.QuoteDataRepository;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.QuoteOfTheDayDataRepository;
import com.blazartech.products.qotdp.data.access.impl.spring.jpa.repos.SrcValDataRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author scott
 */
@Service
public class QuoteOfTheDayDALSpringJpaImpl extends QuoteOfTheDayDALBaseImpl implements QuoteOfTheDayDAL {

    private static final Logger logger = Logger.getLogger(QuoteOfTheDayDALSpringJpaImpl.class);

    @Autowired
    private QuoteDataRepository quoteDataRepository;

    @Autowired
    private QuoteOfTheDayDataRepository quoteOfTheDayDataRepository;

    @Autowired
    private SrcValDataRepository srcValDataRepository;

    private Quote buildQuote(QuoteData quote) {
        Quote q = new Quote();
        q.setNumber(quote.getQuoteNum());
        q.setSourceCode(quote.getSrcCde().getSrcCde());
        q.setText(quote.getQuoteTxt());
        q.setUsable(quote.getCanUse().equals("Y"));
        return q;
    }

    private void buildQuoteData(Quote quote, QuoteData q) {
        q.setCanUse(quote.isUsable() ? "Y" : "N");
        if (quote.getNumber() > 0) {
            q.setQuoteNum(quote.getNumber());
        }
        q.setQuoteTxt(quote.getText());
        q.setSrcCde(srcValDataRepository.findById(quote.getSourceCode()).get());
    }

    @Override
    @Cacheable(cacheManager = "cacheManager", key="#quoteNumber", cacheNames = "quoteCache")
    public Quote getQuote(int quoteNumber) {
        logger.info("getting quote #" + quoteNumber);

        QuoteData quote = quoteDataRepository.findById(quoteNumber).get();
        return buildQuote(quote);
    }

    @Override
    public void updateQuote(Quote q) {
        logger.info("updating quote " + q.getNumber());

        QuoteData quote = quoteDataRepository.findById(q.getNumber()).get();
        buildQuoteData(q, quote);

        quoteDataRepository.save(quote);
    }

    private QuoteSourceCode buildSourceCode(SrcValData srcVal) {
        QuoteSourceCode sc = new QuoteSourceCode();
        sc.setNumber(srcVal.getSrcCde());
        sc.setText(srcVal.getSrcTxt());
        return sc;
    }

    private void buildSrcValData(QuoteSourceCode sc, SrcValData srcVal) {
        if (sc.getNumber() > 0) {
            srcVal.setSrcCde(sc.getNumber());
        }
        srcVal.setSrcTxt(sc.getText());
    }

    @Override
    @Cacheable(cacheManager = "cacheManager", key="#sourceCode", cacheNames = "sourceCodeCache")
    public QuoteSourceCode getQuoteSourceCode(int sourceCode) {
        logger.info("getting source code " + sourceCode);

        SrcValData srcVal = srcValDataRepository.findById(sourceCode).get();
        return buildSourceCode(srcVal);
    }

    @Override
    public void updateQuoteSourceCode(QuoteSourceCode sourceCode) {
        logger.info("updating source code " + sourceCode.getNumber());

        SrcValData srcVal = srcValDataRepository.findById(sourceCode.getNumber()).get();
        buildSrcValData(sourceCode, srcVal);

        srcValDataRepository.save(srcVal);
    }

    private Collection<Quote> buildQuoteCollection(Collection<QuoteData> quoteCollection) {
        List<Quote> quotes = new ArrayList<>();
        quoteCollection.stream().map((q) -> buildQuote(q)).forEachOrdered((quote) -> {
            quotes.add(quote);
        });

        return quotes;
    }

    @Override
    public Collection<Quote> getAllQuotes() {
        logger.info("getting all quotes");

        Collection<QuoteData> allQuotes = quoteDataRepository.findAll();
        return buildQuoteCollection(allQuotes);
    }

    @Override
    public Collection<Quote> getUsableQuotes() {
        logger.info("getting usable quotes");

        Collection<QuoteData> usableQuotes = quoteDataRepository.findByCanUse("Y");
        return buildQuoteCollection(usableQuotes);
    }

    private QuoteOfTheDay buildQuoteOfTheDay(QuoteOfTheDayData qotd) {
        if (qotd != null) {
            QuoteOfTheDay q = new QuoteOfTheDay();
            q.setNumber(qotd.getQotdNum());
            q.setQuoteNumber(qotd.getQuoteNum().getQuoteNum());
            q.setRunDate(qotd.getQuoteDate());
            return q;
        } else {
            return null;
        }
    }
    
    private void buildQuoteOfTheDayData(QuoteOfTheDay qotd, QuoteOfTheDayData qotdData) {
        if (qotd.getNumber() > 0) {
            qotdData.setQotdNum(qotd.getNumber());
        }
        qotdData.setQuoteDate(qotd.getRunDate());
        qotdData.setQuoteNum(quoteDataRepository.findById(qotd.getQuoteNumber()).get());
    }

    @Override
    @Cacheable(cacheManager = "cacheManager", key="#runDate", cacheNames = "qotdCache")
    public QuoteOfTheDay getQuoteOfTheDay(Date runDate) {
        logger.info("getting quote of the day for " + runDate);

        QuoteOfTheDayData qotd = quoteOfTheDayDataRepository.findByQuoteDate(runDate);
        return buildQuoteOfTheDay(qotd);
    }

    @Override
    public void updateQuoteOfTheDay(QuoteOfTheDay qotd) {
        logger.info("updating quote of the day for " + qotd);
        
        QuoteOfTheDayData qotdData = quoteOfTheDayDataRepository.findById(qotd.getNumber()).get();
        buildQuoteOfTheDayData(qotd, qotdData);
        quoteOfTheDayDataRepository.save(qotdData);
    }

    @Override
    public Collection<QuoteSourceCode> getQuoteSourceCodes() {
        logger.info("getting all source codes");
        
        Collection<SrcValData> srcValCollection = srcValDataRepository.findAll();
        List<QuoteSourceCode> sourceCodes = new ArrayList<>();
        srcValCollection.stream().map((srcVal) -> buildSourceCode(srcVal)).forEachOrdered((srcCode) -> {
            sourceCodes.add(srcCode);
        });
        
        // sort alphabetically
        Collections.sort(sourceCodes, new SourceCodeComparator());
        
        return sourceCodes;
    }

    @Override
    public void addQuote(Quote q) {
        logger.info("adding new quote");
        
        QuoteData quote = new QuoteData();
        buildQuoteData(q, quote);
        
        quoteDataRepository.save(quote);
        
        q.setNumber(quote.getQuoteNum());
    }

    @Override
    public void addQuoteOfTheDay(QuoteOfTheDay qotd) {
        logger.info("adding quote of the day for " + qotd.getRunDate());
        
        QuoteOfTheDayData qotdData = new QuoteOfTheDayData();
        buildQuoteOfTheDayData(qotd, qotdData);
        
        quoteOfTheDayDataRepository.save(qotdData);
        qotd.setNumber(qotdData.getQotdNum());
    }

    @Override
    public void addQuoteSourceCode(QuoteSourceCode sourceCode) {
        logger.info("adding source code " + sourceCode.getText());
        
        SrcValData srcVal = new SrcValData();
        buildSrcValData(sourceCode, srcVal);
        
        srcValDataRepository.save(srcVal);
        sourceCode.setNumber(srcVal.getSrcCde());
    }

    @Override
    public Collection<QuoteOfTheDay> getQuoteOfTheDayInDateRange(int quoteNumber, Date startDate, Date endDate) {
        logger.info("looking for instances of quote #" + quoteNumber + " in date range " + startDate + " to " + endDate);
        
        Collection<QuoteOfTheDayData> qotdCollection = quoteOfTheDayDataRepository.findByDateRange(quoteNumber, startDate, endDate);
        Collection<QuoteOfTheDay> qotds = new ArrayList<>();
        qotdCollection.stream().map((qotdData) -> buildQuoteOfTheDay(qotdData)).forEachOrdered((qotd) -> {
            qotds.add(qotd);
        });
        
        return qotds;
    }

    @Override
    public Collection<Quote> getQuotesForSourceCode(int sourceCode) {
        logger.info("getting quotes for source code " + sourceCode);
        
        // retrieve the source code and use its quote collection.
        SrcValData srcCode = srcValDataRepository.findById(sourceCode).get();
        Collection<QuoteData> quotes = srcCode.getQuoteCollection();
        
        return buildQuoteCollection(quotes);
    }

    @Override
    public QuoteOfTheDayHistory getQuoteOfTheDayHistoryForQuote(int quoteNumber) {
        logger.info("getting history for quote #" + quoteNumber);
        
        // get the quotes of the day via the quote.
        QuoteData quoteData = quoteDataRepository.findById(quoteNumber).get();
        Collection<QuoteOfTheDayData> qotdCollection = quoteData.getQuoteOfTheDayCollection();
        
        // convert to a collection of application objects.
        Collection<QuoteOfTheDay> qotdList = new ArrayList<>();
        qotdCollection.stream().map((qotdData) -> buildQuoteOfTheDay(qotdData)).forEachOrdered((qotd) -> {
            qotdList.add(qotd);
        });
        
        // build the history
        return buildQuoteOfTheDayHistory(qotdList, quoteNumber);
    }

}

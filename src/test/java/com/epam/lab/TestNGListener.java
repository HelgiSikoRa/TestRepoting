package com.epam.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    private final static Logger LOG = LogManager.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOG.info("onTestStart " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOG.info("onTestSuccess " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOG.info("onTestFailure " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOG.info("onTestSkipped " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LOG.info("onTestFailedButWithinSuccessPercentage " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOG.info("onStart " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOG.info("Total Passed: " + iTestContext.getPassedTests());
        LOG.info("Total Failed: " + iTestContext.getFailedTests());
        LOG.info("Total Skipped: " + iTestContext.getSkippedTests());
    }
}
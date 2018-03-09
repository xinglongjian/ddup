package com.xingtan.monitor.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(description = "Test Statistic",
        objectName = "com.xingtan.monitor:name=test")
public class TestStatisticsImpl implements TestStatistics {
    @Override
    @ManagedAttribute(description = "Total", defaultValue = "0")
    public int getTotal() {
        return 10;
    }

    @ManagedOperation(description = "getMessage function")
    public String getMessage() {
        return "test mbean";
    }
}

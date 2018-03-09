package com.xingtan.monitor.jmx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JMXConfig {

    @Value("${monitor.profix.name}")
    private String mbeanProfix;
    @Bean
    TestStatistics testStatistics() {
        return new TestStatisticsImpl();
    }

    @Bean
    MBeanExporter jmxExporter(){
        MBeanExporter exporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put(mbeanProfix + ":name=test", testStatistics());
        exporter.setBeans(beans);
        return exporter;
    }

}

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.zxy.dashboard.turbine;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.cloud.client.actuator.HasFeatures;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.turbine.streaming.servlet.TurbineStreamServlet;

/** 
 * TurbineHttpConfiguration
 * 
 * <p>
 * <a href="TurbineHttpConfiguration.java"><i>View Source</i></a>
 * 
 * @author zhaoxunyong@qq.com
 * @version 1.0
 * @since 1.0 
*/
@Configuration
//@EnableConfigurationProperties(ConfigProperties.class)
@EnableDiscoveryClient
public class TurbineHttpConfiguration {
    
    /**
     * Feature
     * 
     * @return HasFeatures
    */
    @Bean
    public HasFeatures feature() {
        return HasFeatures.namedFeature("Turbine (HTTP)", TurbineHttpConfiguration.class);
    }
    
//    @Bean
//    public TurbineServletContextListener executorListener() {
//       return new TurbineServletContextListener();
//    }

    /**
     * turbineStreamServlet
     * 
     * @return ServletRegistrationBean
    */
    @Bean
    public ServletRegistrationBean turbineStreamServlet() {
        return new ServletRegistrationBean(new TurbineStreamServlet(), "/turbine.stream");
    }

//    @Bean
//    public TurbineProperties turbineProperties() {
//        return new TurbineProperties();
//    }
//    
//    @Bean
//    public InstanceDiscovery instanceDiscovery(TurbineProperties turbineProperties) {
//        return new ConfigPropertyDiscovery(turbineProperties);
//    }
    
    /**
     * turbineLifecycle
     * 
     * @return TurbineLifecycle
    */
    @Bean
    public TurbineLifecycle turbineLifecycle() {
        return new TurbineLifecycle();
    }
}
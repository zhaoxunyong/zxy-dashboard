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

import static org.springframework.web.bind.annotation.RequestMethod.GET;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * TurbineController
 * 
 * <p>
 * <a href="TurbineController.java"><i>View Source</i></a>
 * 
 * @author zhaoxunyong@qq.com
 * @version 1.0
 * @since 1.0 
*/
@RestController
@PropertySource(value="classpath:config.properties", ignoreResourceNotFound=true)
public class TurbineController {
    
    @Autowired
    public Environment evn;
    
    /**
     * get cluster list
     * 
     * @param cluster cluster name
     * @return cluster list
    */
    @RequestMapping(value = "/cluster/{cluster}", method = GET)
    public String list(@PathVariable("cluster") String cluster) {
        String clusters = "";
        String instances = evn.getProperty("turbine.ConfigPropertyBasedDiscovery.default.instances");
        String urlSuffix = evn.getProperty("turbine.instanceUrlSuffix");
        
        if(StringUtils.isNotBlank(cluster)){
            instances = evn.getProperty("turbine.ConfigPropertyBasedDiscovery."+cluster+".instances");
            urlSuffix = evn.getProperty("turbine.instanceUrlSuffix."+cluster);
        }
        
        if(StringUtils.isNotBlank(instances)){
            String[] ins = instances.split(",");
            StringBuilder clusterList = new StringBuilder();
            if(ins!=null && ins.length>0){
                for(String host: ins){
                    String url = "http://"+host+urlSuffix;
                    clusterList.append(url).append(",");
                }
            }
            clusters = clusterList.toString();
        }
        return clusters;
    }
}

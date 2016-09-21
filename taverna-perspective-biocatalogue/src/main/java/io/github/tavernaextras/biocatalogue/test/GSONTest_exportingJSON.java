package io.github.tavernaextras.biocatalogue.test;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.HashMap;
import java.util.Map;

import io.github.tavernaextras.biocatalogue.model.connectivity.BeanForPOSTToFilteredIndex;

import com.google.gson.Gson;

public class GSONTest_exportingJSON
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    Map<String, String[]> m = new HashMap<String, String[]>();
    m.put("a", new String[] {"b","c"});
    m.put("d", new String[] {"e","f"});
    
    BeanForPOSTToFilteredIndex b = new BeanForPOSTToFilteredIndex();
    b.filters = m;
    
    Gson gson = new Gson();
    System.out.println(gson.toJson(b));

  }

}

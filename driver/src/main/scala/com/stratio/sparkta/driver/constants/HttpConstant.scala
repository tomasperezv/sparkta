/**
 * Copyright (C) 2015 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stratio.sparkta.driver.constants

/**
 * HttpConstants used in http services mainly.
 * @author anistal
 */
object HttpConstant {

  /**
   * Endpoints of the web application.
   */
  final val FragmentPath = "fragment"

  /**
   * Http codes.
   */
  final val NotFound = 400
  final val BadRequest = 500

  /**
   * Http messages.
   */
  final val NotFoundMessage = "Not Found"
  final val BadRequestMessage = "Bad Request"
}

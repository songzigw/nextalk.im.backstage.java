/*
 * Copyright [2016] [zhangsong <songm.cn>].
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package songm.im.backstage.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;

import songm.im.backstage.entity.Result;

public class JsonUtils {

    private static Gson gson = new Gson();

    public static <T, E> String toJson(Result<E> obj, Class<T> clazz) {
        return gson.toJson(obj, clazz);
    }

    public static <T, E> byte[] toJsonBytes(Result<E> obj, Class<T> clazz) {
        String json = gson.toJson(obj, clazz);
        if (json == null) {
            return null;
        }
        return json.getBytes();
    }
    
    public static <T> T fromJson(String str, Class<T> clazz) {
        return gson.fromJson(str, clazz);
    }

    public static <T> T fromJson(byte[] json, Class<T> clazz) {
        return fromJson(new String(json), clazz);
    }
    
    public static <T> T fromJson(String str, Type type) {
        return gson.fromJson(str, type);
    }

}

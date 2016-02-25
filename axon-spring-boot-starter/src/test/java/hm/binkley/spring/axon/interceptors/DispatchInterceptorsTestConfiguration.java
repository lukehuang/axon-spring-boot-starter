/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>.
 */

package hm.binkley.spring.axon.interceptors;

import org.axonframework.commandhandling.CommandDispatchInterceptor;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.supporting.VolatileEventStore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAutoConfiguration
public class DispatchInterceptorsTestConfiguration {
    final List<Integer> handlings = new ArrayList<>();

    @Bean
    public EventStore eventStore() {
        return new VolatileEventStore();
    }

    @Bean
    @Order(2)
    public CommandDispatchInterceptor aCommandHandlerInterceptor() {
        return commandMessage -> {
            handlings.add(2);
            return commandMessage;
        };
    }

    @Bean
    @Order(1)
    public CommandDispatchInterceptor bCommandHandlerInterceptor() {
        return commandMessage -> {
            handlings.add(1);
            return commandMessage;
        };
    }
}
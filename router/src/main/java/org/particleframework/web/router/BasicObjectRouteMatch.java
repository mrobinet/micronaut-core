/*
 * Copyright 2017 original authors
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
package org.particleframework.web.router;

import org.particleframework.core.type.Argument;
import org.particleframework.core.type.ReturnType;
import org.particleframework.http.HttpRequest;
import org.particleframework.http.MediaType;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * A route match designed to return an existing object
 *
 * @author James Kleeh
 * @since 1.0
 */
public class BasicObjectRouteMatch implements RouteMatch<Object> {

    private final Object object;

    public BasicObjectRouteMatch(Object object) {
        this.object = object;
    }

    @Override
    public Map<String, Object> getVariables() {
        return Collections.emptyMap();
    }

    @Override
    public Object execute(Map<String, Object> argumentValues) {
        return object;
    }

    @Override
    public RouteMatch<Object> fulfill(Map<String, Object> argumentValues) {
        return this;
    }

    @Override
    public RouteMatch<Object> decorate(Function<RouteMatch<Object>, Object> executor) {
        return new BasicObjectRouteMatch(executor.apply(this));
    }

    @Override
    public Optional<Argument<?>> getRequiredInput(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Argument<?>> getBodyArgument() {
        return Optional.empty();
    }

    @Override
    public List<MediaType> getProduces() {
        return Collections.emptyList();
    }

    @Override
    public ReturnType<?> getReturnType() {
        return ReturnType.of(object.getClass());
    }

    @Override
    public boolean accept(@Nullable MediaType contentType) {
        return true;
    }

    @Override
    public boolean test(HttpRequest httpRequest) {
        return true;
    }
}
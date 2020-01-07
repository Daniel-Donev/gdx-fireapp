/*
 * Copyright 2017 mk
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
 */

package pl.mk5.gdx.fireapp.database;

import com.badlogic.gdx.utils.Array;

import java.util.Arrays;

import pl.mk5.gdx.fireapp.GdxFIRLogger;

public abstract class SortingFilteringProvider<T, E extends FilterResolver, K extends OrderByResolver> {

    protected E filterResolver;
    protected K orderByResolver;
    protected T query;
    protected final Array<Filter> filters;
    protected OrderByClause orderByClause;

    public SortingFilteringProvider() {
        filters = new Array<>();
        filterResolver = createFilterResolver();
        orderByResolver = createOrderByResolver();
    }

    @SuppressWarnings("unchecked")
    public T applyFiltering() {
        Filter filter;
        if (orderByClause != null) {
            GdxFIRLogger.log("Apply order by " + orderByClause.getOrderByMode() + ":" + orderByClause.getArgument());
            query = (T) orderByResolver.resolve(orderByClause, query);
        }
        while (filters.size > 0) {
            filter = filters.pop();
            GdxFIRLogger.log("Apply filter " + filter.getFilterType() + ":" + Arrays.toString(filter.getFilterArguments()));
            query = (T) filterResolver.resolve(filter.getFilterType(), query, filter.getFilterArguments());
        }
        return query;
    }

    public abstract E createFilterResolver();

    public abstract K createOrderByResolver();

    public SortingFilteringProvider setQuery(T query) {
        this.query = query;
        return this;
    }

    public SortingFilteringProvider setFilters(Array<Filter> filters) {
        if (filters == null) return this;
        this.filters.clear();
        this.filters.addAll(filters);
        filters.reverse();
        return this;
    }

    public SortingFilteringProvider setOrderByClause(OrderByClause orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    public void clear() {
        filters.clear();
        query = null;
        orderByClause = null;
    }
}

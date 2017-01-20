package ro.igpr.tickets.controller;

import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.builder.TokenBinder;
import com.strategicgains.hyperexpress.builder.TokenResolver;
import org.hibernate.criterion.Order;
import org.restexpress.Request;
import org.restexpress.Response;
import org.restexpress.common.query.QueryFilter;
import org.restexpress.common.query.QueryOrder;
import org.restexpress.common.query.QueryRange;
import org.restexpress.query.QueryFilters;
import org.restexpress.query.QueryOrders;
import org.restexpress.query.QueryRanges;
import ro.igpr.tickets.config.Constants;
import ro.igpr.tickets.domain.CountiesEntity;

import java.util.List;

public final class CountiesController extends BaseController {

    public CountiesController() {
        super();
    }

    /**
     * Lists all counties
     *
     * @param request
     * @param response
     * @return
     */
    public final List<CountiesEntity> readAll(final Request request, final Response response) {
        super.readAll(request, response);

        final List<CountiesEntity> counties = dao.getAll(CountiesEntity.class, Order.asc(Constants.Fields.ID));

        HyperExpress.tokenBinder(new TokenBinder<CountiesEntity>() {
            @Override
            public void bind(CountiesEntity entity, TokenResolver resolver) {
                resolver.bind(Constants.Url.COUNTY_ID, entity.getId().toString());
            }
        });

        return counties;
    }
}
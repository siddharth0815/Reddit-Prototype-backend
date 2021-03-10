package Reddit.Prototype.Backend.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SortService {

    private Sort.Direction getSortDirection(String direction){
        if( direction.equals("desc") )
            return Sort.Direction.DESC;
        return Sort.Direction.ASC;
    }

    public <T> List<T> get(String[] sort, int count, JpaRepository<T,Long> repo){
        List<Order> orders = new ArrayList<Order>();
        if( sort[0].contains(",") ) {
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        }
        else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }
        return repo.findAll(PageRequest.of(0, count, Sort.by(orders))).getContent();
    }
}

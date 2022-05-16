package psk.project.FileRepository.file.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import psk.project.FileRepository.file.entity.File;
import psk.project.FileRepository.file.dao.SearchCriteria;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class FileSpecification implements Specification<File> {
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<File> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
            if (criteria.getOperation().equalsIgnoreCase(">")) {
                return builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase("<")) {
                return builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            }
            else if (criteria.getOperation().equalsIgnoreCase(":")) {
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(
                            root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            }
            return null;
    }
}

package app.ebel.steadybucks.repository.base;

import app.ebel.steadybucks.exception.community.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean // Spring이 해당 인터페이스를 빈으로 등록하지 않도록 설정
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    default T findByIdOrThrow(ID id, String entityName) {
        return findById(id)
                .orElseThrow(()->new ResourceNotFoundException(entityName + "@" + id));
    }

}

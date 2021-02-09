package Transformers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import Transformers.model.*;

public interface TransformerRepository extends JpaRepository<Transformer, Long>  {

	/*List<Transformer>  findByTeamOrderByRankDesc(String Team);*/

}

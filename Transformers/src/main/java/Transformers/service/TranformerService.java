package Transformers.service;

import Transformers.model.*;
import java.util.*;

public interface TranformerService {

	List<Transformer> getAllTransformers();
	void addTransformer(Transformer transformer);
	Transformer getById(Long id);
	void deleteTransformer(Long id);
	void deleteAllTransformer();
	List<Transformer> getAllTransformersbyTeam(List<Transformer> allTransformer, String team);
	String findWinningTeam(long[] Ids);
	Transformer compareTransformers(Transformer Autobots, Transformer Decepticons);
	String findWinningTeamList(List<Transformer> allTransformer);
}
